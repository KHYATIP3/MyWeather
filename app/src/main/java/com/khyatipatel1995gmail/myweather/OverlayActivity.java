package com.khyatipatel1995gmail.myweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OverlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay);
    }

    public void startMain(View view) {

        startActivity(new Intent(this,MainActivity.class));

    }
}
