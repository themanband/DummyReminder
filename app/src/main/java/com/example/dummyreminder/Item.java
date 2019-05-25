package com.example.dummyreminder;

import android.util.Log;

import java.util.ArrayList;

public class Item extends ArrayList<Item> {
    public static String name;
    public static String time;
    public static String date;
    public static String ImageURL;
    public static Boolean dailyCheck;
    public static String TAG = "Item Model";
//Sets value to the variables

    public Item() {

    }

    public Item(String name, String time, String date) {
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public Item(String name, String time, String date, String imageURL, boolean dailyCheck) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.ImageURL = imageURL;
        this.dailyCheck = dailyCheck;
    }

    public Item(int id, String name, String time, String date) {
    }


    //Name (GET & SET)
    public String getName() {
        Log.d(TAG, "Item MODEL Name : " + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Time (GET & SET)
    public String getTime() {
        Log.d(TAG, "Item MODEL Time : " + time);
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //Date (GET & SET)
    public String getDate() {
        Log.d(TAG, "Item MODEL Date : " + date);
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //Image (GET & SET)
    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public Boolean getDailyCheck() {
        return dailyCheck;
    }

    public void setDailyCheck(Boolean dailyCheck) {
        this.dailyCheck = dailyCheck;
    }
}
