package com.example.mysqlconnection;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ParsingJson {
    private int len;
    String [][] output;
    JSONArray root;

    public ParsingJson(String json_format) throws JSONException {
        root = new JSONArray(json_format);

        len = root.length();
        //Result save array
        makeArray();
        Parsing();
    }

    private void makeArray(){
        output = new String[len][5];
    }

    private void Parsing(){
        for(int i=0; i<len; i++){
            JSONObject jo = null;
            try {
                jo = root.getJSONObject(i);
                output[i][0] = jo.getString("date");
                output[i][1] = jo.getString("time");
                output[i][2] = jo.getString("fire");
                output[i][3] = jo.getString("temperature");
                output[i][4] = jo.getString("air");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d("parsing",Arrays.deepToString(output));
    }
    public int getParsingLength(){
        return len;
    }

    public String[][] getParsingResult(){
        return output;
    }
}

