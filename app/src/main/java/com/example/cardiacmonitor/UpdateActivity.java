package com.example.cardiacmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cardiacmonitor.R;
import com.example.cardiacmonitor.model.CardiacModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //ArrayList<CardiacModel> dataArrayList;
    Gson gson;
    EditText date_input,time_input,systolic_input,diastolic_input,heartrate_input,comment_input;
    String date, time, systolic, diastolic, heartrate, comment;
    Button update_button;
    CardiacModel cardiacModel;

    /**
     * Update the data from this page.
     * Users can update their data by
     * inserting new data in this page.
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        readData();
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        cardiacModel = DataList.array.get(index);





        date_input = findViewById(R.id.dateValue2);
        time_input = findViewById(R.id.timeValue2);
        systolic_input = findViewById(R.id.systolicValue2);
        diastolic_input = findViewById(R.id.diastolicValue2);
        heartrate_input = findViewById(R.id.heartRateValue2);
        comment_input = findViewById(R.id.commentValue2);
        update_button = findViewById(R.id.updateButton);

        date_input.setText(cardiacModel.getDate().toString());
        time_input.setText(cardiacModel.getTime().toString());
        systolic_input.setText(cardiacModel.getSystolic().toString());
        diastolic_input.setText(cardiacModel.getDiastolic().toString());
        heartrate_input.setText(cardiacModel.getHeartRate().toString());
        comment_input.setText(cardiacModel.getComment().toString());
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = date_input.getText().toString();
                time = time_input.getText().toString();
                systolic = systolic_input.getText().toString();
                diastolic = diastolic_input.getText().toString();
                heartrate = heartrate_input.getText().toString();
                comment = comment_input.getText().toString();
                cardiacModel = new CardiacModel(date,time,systolic,diastolic,heartrate,comment);
                //dataArrayList.set(index,cardiacModel);
                DataList.array.set(index,cardiacModel);
                MainActivity.customAdapter.notifyItemChanged(index,cardiacModel);
                // MainActivity.customAdapter.notifyDataSetChanged();
                PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this).edit().clear().commit();
                writeData();
                finish();

            }
        });

    }

    /**
     * retrieve data in the shared preference.
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

    /**
     * save data in the shared preference.
     */

    private void writeData()
    {
        sharedPreferences = getSharedPreferences("kazi",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(DataList.array);
        editor.putString("shujoy",jsonString);
        editor.apply();
    }
}