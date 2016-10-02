package com.khyatipatel1995gmail.myweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;

public class GraphActivity extends AppCompatActivity {

    int randomint = 9;

    LineView lineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineView = (LineView)findViewById(R.id.line_view);

        //must*
        ArrayList<String> test = new ArrayList<String>();
        for (int i=0; i<randomint; i++){
            test.add(String.valueOf(i+1));
        }
        assert lineView != null;
        lineView.setBottomTextList(test);
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);

    }


    private void randomSet(){
        ArrayList<Integer> dataList = new ArrayList<Integer>();
        int random = (int)(Math.random()*9+1);
        for (int i=0; i<randomint; i++){
            dataList.add((int)(Math.random()*random));
        }

        ArrayList<Integer> dataList2 = new ArrayList<Integer>();
        random = (int)(Math.random()*9+1);
        for (int i=0; i<randomint; i++){
            dataList2.add((int)(Math.random()*random));
        }

        ArrayList<Integer> dataList3 = new ArrayList<Integer>();
        random = (int)(Math.random()*9+1);
        for (int i=0; i<randomint; i++){
            dataList3.add((int)(Math.random()*random));
        }

        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<ArrayList<Integer>>();
        dataLists.add(dataList);
        dataLists.add(dataList2);
//        dataLists.add(dataList3);

        lineView.setDataList(dataLists);
    }

    public void startGraphAnalysis(View view) {
        randomSet();
    }

    public static void mergeSort(String[] names) {
        if (names.length >= 2) {
            String[] left = new String[names.length / 2];
            String[] right = new String[names.length - names.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = names[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = names[i + names.length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(names, left, right);
        }
    }

    public static void merge(String[] names, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < names.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareToIgnoreCase(right[b]) < 0)) {
                names[i] = left[a];
                a++;
            } else {
                names[i] = right[b];
                b++;
            }
        }
    }

}
