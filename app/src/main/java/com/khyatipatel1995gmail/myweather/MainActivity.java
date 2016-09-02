package com.khyatipatel1995gmail.myweather;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), (CharSequence) Environment.getExternalStorageDirectory().getAbsolutePath(),
                Toast.LENGTH_LONG).show();
         Log.e("","Hello world");
    }


}
