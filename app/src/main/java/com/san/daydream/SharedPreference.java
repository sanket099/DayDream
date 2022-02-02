package com.san.daydream;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private static final String SharedPreference_name = "my_SharedPreference";
    private final Context context;

    SharedPreference(Context context) {
        this.context = context;
    }


    public void save_flag(int flag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("flag", flag);
        editor.apply();

    }
    public int get_flag(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);

        return sharedPreferences.getInt("flag", 0);
    }

    public void del_flag(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreference_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("flag");
        editor.apply();


    }

}