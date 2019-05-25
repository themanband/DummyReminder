package com.example.dummyreminder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private ArrayList<Item> itemList = new ArrayList<>();
    private RecyclerView items;
    private RecyclerView.Adapter adapter;
    private DatabaseSQLite myDatabase;

    // define the SharedPreferences Object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets Shared Preferences to save state of Reminders (if any)... then builds required itemList
//        getPrefs
        myDatabase = new DatabaseSQLite(this);
//        addItemsToList();
//        myDatabase.getData();


        this.items = findViewById(R.id.items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.items.setLayoutManager(mLayoutManager);

        adapter = new ItemAdapter(itemList);
        this.items.setAdapter(adapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemAdd.class);
                startActivity(intent);
            }
        });


    }

    //creates and populates the list with strings(TEMPORARY AND NOT RESULTIVE OF FINAL APP)
    //TODO: Make List Population DYNAMIC!!!
    private ArrayList<Item> addItemsToList() {
//        itemList.add(reminder);
        Cursor res = myDatabase.returnAllData();
        Item item = new Item();

        while(res.moveToNext()){
            res.getString(0);
            res.getString(1);
            res.getString(2);
            res.getString(3);
            item = new Item(res.getString(0), res.getString(1), res.getString(2));
        }
//        list.add(new Item(item.getName(), item.getTime(), item.getDate(), "http://www.losethefatweightloss.com/wp-content/uploads/2014/01/44337981cf53.jpg", true));
        return item;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }









//
//    public void getPrefs() {
//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String restoredText = prefs.getString("name", null);
//        if (restoredText != null) {
//
//            String reminder1 = prefs.getString("reminder1", "No reminders saved yet"); //"No name defined" is the DEFAULT value
//            Log.d(TAG, "Reached getPrefs with the following REMINDER Item: '" + reminder1 + "'");
//            String name = reminder1.substring(0, reminder1.indexOf("|"));
//            String date = reminder1.substring(reminder1.indexOf("|"), reminder1.lastIndexOf("|"));
//            String time = reminder1.substring(reminder1.lastIndexOf("|"));
//
//
//            Map<String, ?> allEntries = prefs.getAll();
//            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//                Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
//            }
//
//            Item reminder = new Item(name, date, time);
//            addItemsToList(reminder);
////            }
//
//        }
//
//    }
}
