package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvTitle);

        MyTask myTask = new MyTask(tvTitle);

        myTask.execute("http://docbao.vn");


        MyTask.OnLoadCompletedListener onLoadCompletedListener = new MyTask.OnLoadCompletedListener() {
            @Override
            public void onFinished(String result) {
                tvTitle.setText(result);
            }
        };
        MyTask myTask1 = new MyTask(onLoadCompletedListener);

        myTask1.execute("http://docbao.vn");

        // loadData();
    }


    public void loadData() {


        try {
            URL url = new URL("http://docbao.vn");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);

            String data = "";


            while (scanner.hasNext()) {

                data = scanner.nextLine() + data;

            }

            scanner.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
