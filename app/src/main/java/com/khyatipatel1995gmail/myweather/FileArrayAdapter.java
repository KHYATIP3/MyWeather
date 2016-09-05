package com.khyatipatel1995gmail.myweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by KHYATI PATEL on 9/2/2016.
 */
public class FileArrayAdapter extends ArrayAdapter<String> {
    List<String> filesList;
    public FileArrayAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        filesList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if(v==null)
        {
            LayoutInflater inflator=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflator.inflate(R.layout.listview_item_row,null);

        }
        TextView fileName  = (TextView) v.findViewById(R.id.file_name);
        File tempFile = new File(filesList.get(position));
        fileName.setText(tempFile.getName());
        return v;
    }
}
