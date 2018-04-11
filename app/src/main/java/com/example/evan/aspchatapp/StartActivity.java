package com.example.evan.aspchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(2000);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                finally
                {
                    Intent i = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        };
        thread.start();
    }
}
