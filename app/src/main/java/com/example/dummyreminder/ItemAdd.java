package com.example.dummyreminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import static java.util.Calendar.AM_PM;

public class ItemAdd extends Activity {
    private Item item = new Item("", "", "", "", true);
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private DatabaseSQLite myDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the view from item_add.xml
        setContentView(R.layout.item_add);
        final EditText itemName = findViewById(R.id.item_name);
        final Button timePick = findViewById(R.id.time_pick);
        final Button datePick = findViewById(R.id.date_pick);
        final Switch dailyBoolean = findViewById(R.id.daily_boolean);
        final Button confirmButton = findViewById(R.id.confirm_button);
        final String timeVal = "";
        final String TAG = "ItemAdd";
        myDatabase = new DatabaseSQLite(this);
        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDialogShow();
            }
        });

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialogShow();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //name
                String name = String.valueOf(itemName.getText());
                item.setName(name);
                Log.d(TAG, "Item Name : " + name);
                item.getName();
                ////
                //time
                String timeVal = String.valueOf(timePick.getText());
                item.setTime(timeVal);
                Log.d(TAG, "Item Time : " + timeVal);
                item.getTime();
                ////
                //date
                String dateVal = String.valueOf(datePick.getText());
                item.setDate(dateVal);
                Log.d(TAG, "Item Date : " + dateVal);
                item.getDate();

                ////
//                setPrefs();
                updateReminder();
                myDatabase.insertData(name, timeVal, dateVal, 1);
                goToMain();


            }
        });
    }

    public void timeDialogShow() {
        Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR);
        final int minute = c.get(Calendar.MINUTE);
        final int amp = c.get(AM_PM);
        final TimePickerDialog timePickerDialog = new TimePickerDialog(ItemAdd.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                Button timePick = (Button) findViewById(R.id.time_pick);
                String format;
                if (hourOfDay == 0) {
                    hourOfDay += 12;

                    format = "AM";
                } else if (hourOfDay == 12) {
                    format = "PM";
                } else if (hourOfDay > 12) {
                    hourOfDay -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }

                timePick.setText(String.valueOf(hourOfDay + " : " + minutes + " " + format));
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    public void dateDialogShow() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(ItemAdd.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Button datePick = (Button) findViewById(R.id.date_pick);
                datePick.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

    private void goToMain() {
        Intent intent = new Intent(ItemAdd.this, MainActivity.class);

        startActivity(intent);
    }


    public void updateReminder(){
        int daily = item.getDailyCheck() ? 1:0;
        Boolean databaseUpdated = myDatabase.insertData(item.getName(),item.getTime(), item.getDate(), daily);
        if(databaseUpdated) {
            Toast.makeText(ItemAdd.this, "Database update Succeeded", Toast.LENGTH_LONG).show();


        }else{
            Toast.makeText(ItemAdd.this, "Database update FAILED", Toast.LENGTH_LONG).show();
        }
    }












//    setting values in prefrence
//  public void setPrefs() {
//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
////        String reminderID = "reminder";
////        int remindIntID = (int) Math.random() * 100;
//
////        editor.putString(idGen(), item.getName() + "|" + item.getDate() + "|" + item.getTime());
//        editor.putString("reminder1", item.getName() + "|" + item.getDate() + "|" + item.getTime());
//        editor.apply();
//    }
}


