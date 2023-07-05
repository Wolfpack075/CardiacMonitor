package com.example.cardiacmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardiacmonitor.R;
import com.example.cardiacmonitor.model.CardiacModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //ArrayList<CardiacModel> dataArrayList;
    Gson gson;
    static String CARDIAC_MODEL = "CardiacModel";
    TextView date_view,time_view,systolic_view,diastolic_view,heartrate_view,comment_view;
    CardiacModel cardiacModel;
    Button edit_button;

    /**
     * creating the form where details of the
     * given data will show
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        date_view = findViewById(R.id.DateValue_view);
        time_view = findViewById(R.id.TimeValue_view);
        systolic_view = findViewById(R.id.SystolicValue_view);
        diastolic_view = findViewById(R.id.DiastolicValue_view);
        heartrate_view = findViewById(R.id.HeartRateValue_view);
        comment_view = findViewById(R.id.CommentValue_view);
        edit_button = findViewById(R.id.editButton);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        readData();

        cardiacModel = DataList.array.get(index);


        date_view.setText(cardiacModel.getDate().toString());
        time_view.setText(cardiacModel.getTime().toString());
        systolic_view.setText(cardiacModel.getSystolic().toString());
        diastolic_view.setText(cardiacModel.getDiastolic().toString());
        heartrate_view.setText(cardiacModel.getHeartRate().toString());
        comment_view.setText(cardiacModel.getComment().toString());

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, UpdateActivity.class);
                intent.putExtra("index",index);
                startActivity(intent);
                finish();
            }
        });
    }



    void getAndSetIntentData(){
        if(getIntent().hasExtra(CARDIAC_MODEL)){

        }
        else
        {
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Data retrieve from shared preference
     */

    private void readData()
    {
        sharedPreferences = getSharedPreferences("kazi",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("shujoy",null);
        Type type = new TypeToken<ArrayList<CardiacModel>>(){}.getType();
        DataList.array = gson.fromJson(jsonString,type);
        if(DataList.array ==null)
        {
            DataList.array = new ArrayList<>();
        }
    }


}




