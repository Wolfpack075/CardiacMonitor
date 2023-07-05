package com.example.cardiacmonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacmonitor.adapter.CustomAdapter;
import com.example.cardiacmonitor.model.CardiacModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    //MyDatabaseHelper myDB;
    // public static ArrayList<CardiacModel> dataArrayList;
    public static CustomAdapter customAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;


    /**
     * Data Entry for the users and have
     * a highlights in main page
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readData();

        recyclerView = findViewById(R.id.recycleview);
        add_button = findViewById(R.id.add_button);
        customAdapter = new CustomAdapter(MainActivity.this,DataList.array);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        add_button.setOnClickListener(view -> {
            Intent intent  = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });




        customAdapter.setCustomClickListener(new CustomAdapter.CustomClickListener() {
            @Override
            public void customOnClick(int position, View v) {
                Intent i = new Intent(MainActivity.this,ViewActivity.class);
                i.putExtra("index",position);
                startActivity(i);
            }

            @Override
            public void customOnLongClick(int position, View v) {

            }

            /**
             * Delete button to delete data
             * from main page list.
             * @param position
             */

            @Override
            public void onDeleteClick(int position) {
                // dataArrayList.remove(position);
                if(position != RecyclerView.NO_POSITION)
                {
                    // DataList.array.remove(position);
                    new DataList().deleteRecord(position);
                    writeData();
                    customAdapter.notifyItemRemoved(position);
                }

            }
        });




    }

    /**
     * Retrieve from Shared preference.
     */

    private void readData()
    {
        sharedPreferences = getSharedPreferences("faija",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("eimu",null);
        Type type = new TypeToken<ArrayList<CardiacModel>>(){}.getType();
        DataList.array = gson.fromJson(jsonString,type);
        if(DataList.array ==null)
        {
            DataList.array = new ArrayList<>();
        }
    }

    /**
     *save the data
     */

    private void writeData()
    {
        sharedPreferences = getSharedPreferences("faija",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(DataList.array);
        editor.putString("eimu",jsonString);
        editor.apply();
    }

}