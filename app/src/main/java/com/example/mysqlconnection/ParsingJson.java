package com.example.mysqlconnection;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ParsingJson {
    public int ROW_LENGTH;
    public int COLUMN_LENGTH;
    public ArrayList<String> KEY_LIST;
    String [][] output;
    JSONArray root;

    public ParsingJson(String json_format) throws JSONException {
        root = new JSONArray(json_format);

        ROW_LENGTH = root.length();

        KEY_LIST = new ArrayList<String>();
        JSONObject jo = root.getJSONObject(0);
        Log.i("0910",jo.toString());
        Iterator i = jo.keys();

        while(i.hasNext())
        {
            String key = i.next().toString();
            KEY_LIST.add(key); // 키 값 저장
        }
        COLUMN_LENGTH = KEY_LIST.size();

        //Result save array
        makeArray();
        Parsing();
    }

    private void makeArray(){
        output = new String[ROW_LENGTH][COLUMN_LENGTH];
    }

    private void Parsing(){
        for(int i=0; i<ROW_LENGTH; i++){
            JSONObject jo = null;
            try {
                jo = root.getJSONObject(i);
                for(int j=0; j<COLUMN_LENGTH; j++) {
                    output[i][j] = jo.getString(KEY_LIST.get(j));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d("parsing",Arrays.deepToString(output));
    }

    /*여기 부분만 사용가능 */
    public int getRowLength(){
        return ROW_LENGTH;
    }

    public int getColumnLength(){
        return COLUMN_LENGTH;
    }

    public String[] getColumn(){
        String[] arr = new String[0];
        return KEY_LIST.toArray(arr);
    }

    public String[][] getParsingResult(){
        return output;
    }
}

