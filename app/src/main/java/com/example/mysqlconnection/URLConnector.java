package com.example.mysqlconnection;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class URLConnector extends Thread {

    //rasberry pi ip
    final private String URL_IP = "192.168.147.168:8000" ;
    //이후 url value로 변경(rest api 만들기)
    private String URL;
    private int id = -1;

    private String result;


    public URLConnector(String url){
        Log.i("funfunfunfun","const");
        URL = "http://"+URL_IP+"/"+url+"/?format=json";
        Log.d("0712_URL",URL);
    }

    @Override
    public void run() {
        Log.i("funfunfunfun","run");
        final String output = request(URL);
        result = output;
    }

    public String getResult(){
        return result;
    }


    private String request(String urlStr) {
        Log.i("funfunfunfun","request");
        StringBuilder output = new StringBuilder();
        try {
            java.net.URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                //conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    Log.i("0712",String.valueOf(resCode));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
                    String line = null;
                    while(true)
                    {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                        Log.d("output",output.toString());
                    }

                    reader.close();
                    conn.disconnect();
                }
            }
        } catch(Exception ex) {
            Log.e("SampleHTTP", "Exception in processing response.", ex);
            ex.printStackTrace();
        }

        return output.toString();
    }




}
