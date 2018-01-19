package com.vbazh.marvelcomics.data;


import android.content.SharedPreferences;

public class SharedPrefs {

    private SharedPreferences sharedPreferences;

    public SharedPrefs(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
    }

    public void putStringData(String key, String data) {

        sharedPreferences.edit().putString(key, data).apply();
    }

    public String getStringData(String key) {

        return sharedPreferences.getString(key, "");
    }

    public void putLongData(String key, long data) {

        sharedPreferences.edit().putLong(key, data).apply();
    }

    public long getLongData(String key) {

        return sharedPreferences.getLong(key, 0);
    }


}
