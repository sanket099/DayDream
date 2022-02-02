package com.san.daydream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;

public class RecyclerAdapter extends ListAdapter<ViewClass,RecyclerAdapter.ViewHolder> {

    private final OnViewClassClick onViewClassClick;
    Context context;
    int checkedPosition = 0;


    public RecyclerAdapter(Context context, OnViewClassClick onViewClassClick) {
        super(DIFF_CALLBACK);
        //*this.ViewClassArrayList = ViewClasss;*//*
        // private List<ViewClass> ViewClassArrayList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(context);
        this.onViewClassClick = onViewClassClick;
        this.context = context;


    }

    private static final DiffUtil.ItemCallback<ViewClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<ViewClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull ViewClass oldItem, @NonNull ViewClass newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ViewClass oldItem, @NonNull ViewClass newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getImg() == newItem.getImg()
                    && oldItem.isSelected() == newItem.isSelected();
        }
    };

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(itemView, onViewClassClick);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        holder.bind(getItem(position));


    }


   /* public void setViewClasss(List<ViewClass> ViewClasss) {
        this.ViewClassArrayList = ViewClasss;
        notifyDataSetChanged();
    }*/

    public ViewClass getViewClassAt(int position) {
        return getItem(position);
    }
    public int getPosition(ViewClass viewClass){
        return getCurrentList().indexOf(viewClass);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnViewClassClick onViewClassClick;

        TextView tvName;
        ImageView imgView;
        ImageView imgSelected;
        ImageView imgPreview;

        public ViewHolder(@NonNull View itemView, OnViewClassClick onViewClassClick) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            imgView = itemView.findViewById(R.id.img_view);
            imgSelected = itemView.findViewById(R.id.img_selected);
            imgPreview = itemView.findViewById(R.id.img_eye);
            this.onViewClassClick = onViewClassClick;
            itemView.setOnClickListener(this);

            imgPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onViewClassClick.clickPreview(getItem(getBindingAdapterPosition()));
                }
            });


        }

        @Override
        public void onClick(View v) {
            if(getBindingAdapterPosition() != -1) {
                onViewClassClick.click(getItem(getBindingAdapterPosition()));

                imgSelected.setVisibility(View.VISIBLE);
                if(checkedPosition != getBindingAdapterPosition()){
                    notifyItemChanged(checkedPosition);
                    checkedPosition = getBindingAdapterPosition();
                }
            }

        }

        public void bind(ViewClass item) {

            tvName.setText(item.getName());
            InputStream imageStream = item.getImg();
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            imgView.setImageBitmap(bitmap);

            if(checkedPosition == -1){
                imgSelected.setVisibility(View.INVISIBLE);

            }else{
                if(checkedPosition == getBindingAdapterPosition()){
                    imgSelected.setVisibility(View.VISIBLE);
                }
                else{
                    imgSelected.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
    public interface OnViewClassClick{
        void click(ViewClass ViewClass);
        void clickPreview(ViewClass viewClass);
    }




}