package com.example.hospitalfindertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DeveloperAdapter extends BaseAdapter {

    private Context context;
    private List<Developer> developerList;

    public DeveloperAdapter(Context context, List<Developer> developerList) {
        this.context = context;
        this.developerList = developerList;
    }

    @Override
    public int getCount() {
        return developerList.size();
    }

    @Override
    public Object getItem(int position) {
        return developerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_developer, parent, false);
        }

        // Get references to the views
        ImageView developerImage = convertView.findViewById(R.id.developerImage);
        TextView developerName = convertView.findViewById(R.id.developerName);
        TextView developerStudentId = convertView.findViewById(R.id.developerStudentId);

        // Get the developer data for this position
        Developer developer = developerList.get(position);

        // Set the data into the views
        developerImage.setImageResource(developer.getImageResourceId());
        developerName.setText(developer.getName());
        developerStudentId.setText("ID: " + developer.getStudentId());

        return convertView;
    }
}
