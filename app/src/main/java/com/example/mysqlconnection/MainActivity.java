package com.example.mysqlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mysqlconnection.URLConnector;

import org.json.JSONException;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView text_sensor_row_length;
    TextView text_sensor_column_length;
    TextView text_sensor_column;
    TextView text_sensor_result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* sensor database */
        //text view
        text_sensor_row_length = findViewById(R.id.text_sensor_row_length);
        text_sensor_column_length = findViewById(R.id.text_sensor_column_length);
        text_sensor_column = findViewById(R.id.text_sensor_column);
        text_sensor_result = findViewById(R.id.text_sensor_result);


        //url connect
        URLConnector task = new URLConnector("sensor");       //"tram" , "sensor"
        task.start();
        try{
            task.join();
            System.out.println("waiting... for result");
        }
        catch(InterruptedException e){

        }
        //http response
        String Json_result = task.getResult();


        //json parsing
        ParsingJson sensorParsing;

        try {
            sensorParsing = new ParsingJson(Json_result);

            //행 개수 갖고오기
            int row_length = sensorParsing.getRowLength();
            //column 개수 갖고오기
            int column_length = sensorParsing.getColumnLength();
            //column 가져오기
            String[] column = sensorParsing.getColumn();

            text_sensor_row_length.setText(String.valueOf(row_length));
            text_sensor_column_length.setText(String.valueOf(column_length));
            text_sensor_column.setText(Arrays.toString(column));


            //database 값 parsing 결과 가져오기
            String[][] parsingResult = sensorParsing.getParsingResult();
            for(int i=0; i<parsingResult.length; i++){
                Log.i("check_parsing_value",String.valueOf(i)+ Arrays.toString(parsingResult[i]));
                text_sensor_result.append(Arrays.toString(parsingResult[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }






    }
}