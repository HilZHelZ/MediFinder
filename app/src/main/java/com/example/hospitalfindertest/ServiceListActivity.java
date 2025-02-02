package com.example.hospitalfindertest;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ServiceListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        String hospitalName = getIntent().getStringExtra("hospitalName");
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(hospitalName + " Services");

        // Sample services with names and images
        ArrayList<Service> serviceList = new ArrayList<>();
        serviceList.add(new Service("Blood Donation", R.drawable.blood_donation));
        serviceList.add(new Service("Dengue Fogging", R.drawable.dengue_fogging));
        serviceList.add(new Service("Free Vaccination", R.drawable.vaccine));
        serviceList.add(new Service("Health Checkups", R.drawable.health_checkup));
        serviceList.add(new Service("Emergency Services", R.drawable.emergency_services));

        // Set up GridView and adapter
        GridView serviceGridView = findViewById(R.id.serviceGridView);
        ServiceAdapter adapter = new ServiceAdapter(this, serviceList);
        serviceGridView.setAdapter(adapter);
    }
}
