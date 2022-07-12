package com.example.mysqlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mysqlconnection.URLConnector;

import org.json.JSONException;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView text_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text_result = findViewById(R.id.text_result);

        URLConnector task = new URLConnector("sensor");
        task.start();
        try{
            task.join();
            System.out.println("waiting... for result");
        }
        catch(InterruptedException e){

        }

        String Json_result = task.getResult();

        Log.i("check_response",task.getResult());
        text_result.setText(task.getResult());

        ParsingJson sensorParsing;

        try {
            sensorParsing = new ParsingJson(Json_result);
            Log.i("check_parsing_length", String.valueOf(sensorParsing.getParsingLength()));

            for(int i=0; i<sensorParsing.getParsingLength(); i++){
                Log.i("check_parsing_value",String.valueOf(i)+ Arrays.toString(sensorParsing.getParsingResult()[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}