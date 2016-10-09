package com.khyatipatel1995gmail.myweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import im.dacer.androidcharts.LineView;

public class GraphActivity extends AppCompatActivity {

    LineView lineView;
    int randomint = 20;
    private static final String TAG = "GraphActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineView = (LineView)findViewById(R.id.line_view);

        //must*
        ArrayList<String> test = new ArrayList<String>();
        for (int i=0; i<=5; i++){
            test.add(String.valueOf(50*(i+1)*10));
        }
        assert lineView != null;

        lineView.setBottomTextList(test);
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);

    }


    private void randomSet(){
        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();
        ArrayList<Integer> insertSortPoints = new ArrayList<>();
//        insertSortPoints.add(1);
//        insertSortPoints.add(2);
//        insertSortPoints.add(3);
        ArrayList<Integer> quickSortPoints = new ArrayList<>();
//        quickSortPoints.add(5);
//        quickSortPoints.add(6);
//        quickSortPoints.add(7);
        ArrayList<Integer> mqSortPoints = new ArrayList<>();
//        mqSortPoints.add(8);
//        mqSortPoints.add(9);
//        mqSortPoints.add(10);
        for (int i=0; i<=5; i++){
            String[] randomSet = generateNRandom(50*(i+1)*10);
            for (int j = 0; j < 3; j++) {
                long currTime = System.nanoTime();
                if (j == 0){
                    try {
                        MainActivity.insertionSort(new ArrayList<String>(Arrays.asList(randomSet)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    long timeTaken = System.nanoTime() - currTime;

                    timeTaken = timeTaken/10000;

                    insertSortPoints.add(new Integer((int) timeTaken)/8);

                    Log.e(TAG, "randomSet: insertion sort"+ timeTaken/8 );
                }else if(j == 1){
                    try {
                        MainActivity.initQuickSort(new ArrayList<String>(Arrays.asList(randomSet)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    long timeTaken = System.nanoTime() - currTime;

                    timeTaken = timeTaken/10000;

                    quickSortPoints.add(new Integer((int) timeTaken)*2);
                    Log.e(TAG, "randomSet: quick sort"+ timeTaken );
                }else {

                    try {
                        MainActivity.mqSort(randomSet,0,randomSet.length - 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    long timeTaken = System.nanoTime() - currTime;

                    timeTaken = timeTaken/10000;

                    mqSortPoints.add(new Integer((int) timeTaken));
                    Log.e(TAG, "randomSet: mqsort"+ timeTaken );

                }

            }

        }

        dataLists.add(insertSortPoints);
        dataLists.add(quickSortPoints);
        dataLists.add(mqSortPoints);

        lineView.setDataList(dataLists);

        Log.d(TAG, "randomSet: "+ "Done");



    }

    public void startGraphAnalysis(View view) {
            randomSet();
    }


    private String[] generateNRandom(int n){
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            Random ran = new Random();
            int x = ran.nextInt(n*100);
            arr[i] = String.valueOf(x);
        }
        return arr;
    }

}
