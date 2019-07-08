package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MyTask extends AsyncTask<String, Long, String> {

    public MyTask(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    private TextView tvTitle;

    public MyTask() {

    }


    public interface OnLoadCompletedListener {
        void onFinished(String result);
    }


    public MyTask(OnLoadCompletedListener onLoadCompletedListener) {
        this.onLoadCompletedListener = onLoadCompletedListener;
    }


    public OnLoadCompletedListener onLoadCompletedListener;


    // phuong thuc xu ly thread.
    /// luu y :
    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);

            String data = "";


            while (scanner.hasNext()) {

                data = scanner.nextLine() + data;

            }

            scanner.close();

            return data;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // ket thuc thread
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        tvTitle.setText(s);

        onLoadCompletedListener.onFinished(s);


    }
}

