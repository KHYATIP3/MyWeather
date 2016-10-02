package com.khyatipatel1995gmail.myweather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;


public class FileArrayAdapter extends ArrayAdapter<String> {

    List<String> filesList;


    private static final String TAG = "FileArrayAdapter";

    public FileArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        filesList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if(v==null) {
            LayoutInflater inflator=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflator.inflate(R.layout.listview_item_row,null);
        }
        TextView fileName  = (TextView) v.findViewById(R.id.file_name);
        ImageView image = (ImageView) v.findViewById(R.id.device_storage_image);
        File tempFile = new File(filesList.get(position));
        if (!tempFile.isDirectory()){
            image.setImageResource(R.drawable.files);
        }else {
            image.setImageResource(R.drawable.folder);
        }
        fileName.setText(tempFile.getName());
        return v;
    }

    public static int partition(String[] arr , int l, int h){
        String x = arr[h];
        int small = l - 1;
        for (int j = l; j <= h - 1; j++) {
            if (arr[j].compareTo(x) < 0){
                small++;
                swap(arr,small,j);
            }
        }
        swap(arr,small + 1, h);
        return small + 1;
    }



    private static void swap(String[] strArr, int i, int j) {
//        Log.e(TAG, "partition: "+ i + " " + j );
        String temp = strArr[i];
        strArr[i] = strArr[j];
        strArr[j] = temp;
    }


}
