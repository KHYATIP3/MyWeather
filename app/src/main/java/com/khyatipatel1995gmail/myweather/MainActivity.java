package com.khyatipatel1995gmail.myweather;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public ListView explorerListView;
    Button backButton;
    List<String> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explorerListView = (ListView) findViewById(R.id.storage_listview);
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File currentDir = new File(baseDir);
        File[] allFiles = currentDir.listFiles();
        files = new ArrayList<String>();
        for (File file : allFiles) {
            files.add(file.getPath());
        }
        Collections.sort(files);
        final FileArrayAdapter filesAdapter = new FileArrayAdapter(getApplicationContext(), R.layout.listview_item_row, files);
        View header = (View) getLayoutInflater().inflate(R.layout.listview_header, null);
        backButton=(Button)header.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        explorerListView.addHeaderView(header);
        explorerListView.setAdapter(filesAdapter);
        explorerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                File clickedFile = new File(files.get(position));
                if (clickedFile.isDirectory()) {
                    File[] tempAllFiles = clickedFile.listFiles();
                    files.clear();
                    for (File file : tempAllFiles) {
                        files.add(file.getPath());
                    }
                    FileArrayAdapter filesAdapter = new FileArrayAdapter(getApplicationContext(), R.layout.listview_item_row, files);
                    explorerListView.setAdapter(filesAdapter);

                } else {

                }
                Log.d(TAG, "on create:" + clickedFile.getPath());
//                Log.d(TAG, "onItemClick:" + files);
            }
        });


    }

}
