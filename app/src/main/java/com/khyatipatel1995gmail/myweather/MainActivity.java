package com.khyatipatel1995gmail.myweather;

import android.content.ClipData;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView lv;
    private FileArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.storage_listview);
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File currentdir = new File(baseDir);
        File[] allfiles = currentdir.listFiles();
        List<String> dirs = new ArrayList<String>();
        List<String> files = new ArrayList<String>();
         for (File file: allfiles)
         {
             if (file.isDirectory()) {
                 dirs.add(file.getName());
             } else {
                 files.add(file.getName());
             }
         }
        Log.e("", "Hello world");
    }


}
