package com.khyatipatel1995gmail.myweather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public ListView mStorageListView;
    List<String> mFiles;
    FileArrayAdapter mAdapter;

    public File currentDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorageListView = (ListView) findViewById(R.id.storage_listview);
        currentDir = Environment.getExternalStorageDirectory();
        mFiles = getPathListOfFiles(currentDir);

        refreshAdapter();

        mStorageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentDir = new File(mFiles.get(position));
                mFiles = getPathListOfFiles(currentDir);
                refreshAdapter();
            }
        });
    }

    private void refreshAdapter() {
        String arr[] = mFiles.toArray(new String[mFiles.size()]);
        try {
            long currTime = System.nanoTime();
            mqSort(arr,0,arr.length-1);

            long timeTaken = System.nanoTime() - currTime;

            timeTaken = timeTaken/1000;
            Toast.makeText(getApplicationContext(),"Took " + timeTaken + " micro seconds\n" + "To sort " + arr.length + " items" ,Toast.LENGTH_SHORT).show();


            mFiles = new ArrayList<>(Arrays.asList(arr));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mAdapter = new FileArrayAdapter(getApplicationContext(), R.layout.listview_item_row, mFiles);
        mStorageListView.setAdapter(mAdapter);
    }

    private List<String> getPathListOfFiles(File file) {
        File[] files = file.listFiles();
        List<String> pathList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            pathList.add(files[i].getAbsolutePath());
        }
        return pathList;
    }

    public void backButtonPressed(View view) {
        if (isRoot(currentDir)) {
            Toast.makeText(getApplicationContext(), "Already in root Dir", Toast.LENGTH_SHORT).show();
            return;
        }
        currentDir = currentDir.getParentFile();
        mFiles = getPathListOfFiles(currentDir);
        refreshAdapter();
    }

    private boolean isRoot(File file) {
        return file.getAbsolutePath().equals(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public static List<String> insertionSort(List<String> inputList) throws InterruptedException {
        int i, j;
        String key;
        String[] inputArray = inputList.toArray(new String[inputList.size()]);
        for (j = 1; j < inputArray.length; j++) {
            key = inputArray[j];
            i = j - 1;
            while (i >= 0) {
                if (key.compareTo(inputArray[i]) > 0) {//here too
                    break;
                }
                inputArray[i + 1] = inputArray[i];
                i--;
            }
            inputArray[i + 1] = key;
        }

        return new ArrayList<String>(Arrays.asList(inputArray));
    }

    public static List<String> initQuickSort(List<String> inputList) throws InterruptedException {
        String[] inputArray = inputList.toArray(new String[inputList.size()]);
        inputArray = quickSort(inputArray, 0, inputArray.length - 1);
        return new ArrayList<>(Arrays.asList(inputArray));
    }

    private static String[] quickSort(String[] strArr, int p, int r) throws InterruptedException {
        if (p < r) {
            int q = partition(strArr, p, r);
            quickSort(strArr, p, q);
            quickSort(strArr, q + 1, r);
        }

        return strArr;
    }

    private static int partition(String[] strArr, int p, int r) throws InterruptedException {

        String x = strArr[p];
        int i = p - 1;
        int j = r + 1;

        while (true) {
            i++;
            while (i < r && strArr[i].compareTo(x) < 0) {
                i++;
            }
            j--;
            while (j > p && strArr[j].compareTo(x) > 0) {
                j--;
            }

            if (i < j) {
                swap(strArr, i, j);
            } else {
                return j;
            }
        }
    }

    private static void swap(String[] strArr, int i, int j) {
        String temp = strArr[i];
        strArr[i] = strArr[j];
        strArr[j] = temp;
    }

    public void showGraph(View view) {
        startActivity(new Intent(this, GraphActivity.class));
    }

    public static void mqSort(String arr[],int p , int r) throws InterruptedException {
        if(p < r){
            if (r - p < 10){
//                GraphActivity.mergeSort(arr);
                Arrays.sort(arr,p,r+1);
            }else {
                int q = FileArrayAdapter.partition(arr,p,r);
                mqSort(arr,p,q-1);
                mqSort(arr,q+1,r);
            }
        }
    }

}