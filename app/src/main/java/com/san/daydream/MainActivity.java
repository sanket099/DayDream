package com.san.daydream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnViewClassClick {

    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreference = new SharedPreference(this);
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        recyclerAdapter = new RecyclerAdapter(this, this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.submitList(createList());

    }

    public ArrayList<ViewClass> createList(){
        ArrayList<ViewClass> viewClasses = new ArrayList<>();
        int selectedId = sharedPreference.get_flag();
        InputStream matrixStream = this.getResources().openRawResource(R.raw.matrix);
        InputStream coffeeStream = this.getResources().openRawResource(R.raw.coffee);


        ViewClass viewClass = new ViewClass(0, matrixStream, "Matrix", selectedId == 0);
        viewClasses.add(viewClass);
        ViewClass viewClass2 = new ViewClass(1, coffeeStream, "Coffee", selectedId == 1);
        viewClasses.add(viewClass2);

        return viewClasses;
    }

    public void onSettingsButtonClick(View v) {
        Intent intent;
        intent = new Intent(Settings.ACTION_DREAM_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void click(ViewClass viewClass) {
        sharedPreference.save_flag(viewClass.getId());

        Toast.makeText(this, viewClass.getName() + " is selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickPreview(ViewClass viewClass) {
        startActivity(new Intent(this, PreviewActivity.class).putExtra("ID", viewClass.getId()));
    }
}