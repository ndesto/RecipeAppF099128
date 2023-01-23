package com.example.recipeapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpRequestTask extends AsyncTask<String,Integer,String> {
    String TAG = "HTTP_REQUEST_TASK_LOG";
    // you may separate this or combined to caller class.
    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public MyHttpRequestTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        String my_url = params[0];
        String final_response="";

        try {
            URL url = new URL(my_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // setting the  Request Method Type
            httpURLConnection.setRequestMethod("GET");
            // adding the headers for request
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            try{
                // this is used for just in case we don't know about the data size associated with our request
                httpURLConnection.setChunkedStreamingMode(0);

                // to write tha data in our request
                InputStream inputStream;
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK ||
                        httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                }else {
                    inputStream = new BufferedInputStream(httpURLConnection.getErrorStream());
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                inputStream.close();
                final_response = sb.toString();

                // to log the response code of your request
                Log.d(TAG, "MyHttpRequestTask doInBackground : " + httpURLConnection.getResponseCode());
                // to log the response message from your server after you have tried the request.
                Log.d(TAG, "MyHttpRequestTask doInBackground : " + httpURLConnection.getResponseMessage());


            }catch (Exception e){
                e.printStackTrace();
            }finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return final_response;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}