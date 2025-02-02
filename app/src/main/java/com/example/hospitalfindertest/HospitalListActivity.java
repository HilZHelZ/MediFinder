package com.example.hospitalfindertest;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class HospitalListActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

    private RecyclerView hospitalRecyclerView;
    private ArrayList<String> hospitalList;
    private HospitalAdapter adapter;

    private final LatLng[] hospitalLocations = {
            new LatLng(3.0717215,101.5123841), // Medifeet
            new LatLng(3.0717215,101.520109), // Avisena
            new LatLng(3.0713773,101.4858959), // Hospital Shah Alam
            new LatLng(3.2011454,101.2955642), // Farmasi UiTM
            new LatLng(3.071785,101.5189592), // General Surgery
            new LatLng(3.0828888,101.5374074), // Hospital Umra
            new LatLng(3.0708352,101.4841971), // Klinik Pakar Hospital Shah Alam
            new LatLng(3.0622501,101.4583594)  // KPJ Klang Specialist Hospital
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        // Initialize MapView
        mapView = findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        } else {
            throw new RuntimeException("MapView is not found in layout.");
        }

        // Initialize RecyclerView
        hospitalRecyclerView = findViewById(R.id.hospitalRecyclerView);
        hospitalList = new ArrayList<>();
        hospitalList.add("Medifeet");
        hospitalList.add("Avisena");
        hospitalList.add("Hospital Shah Alam");
        hospitalList.add("Farmasi Pusat Kesihatan UiTM");
        hospitalList.add("General Surgery");
        hospitalList.add("Hospital Umra");
        hospitalList.add("Klinik Pakar Hospital Shah Alam");
        hospitalList.add("KPJ Klang Specialist Hospital");

        adapter = new HospitalAdapter(hospitalList, position -> {
            Intent intent = new Intent(HospitalListActivity.this, ServiceListActivity.class);
            intent.putExtra("hospitalName", hospitalList.get(position));
            startActivity(intent);
        });

        hospitalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hospitalRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap gMap) {
        googleMap = gMap;

        if (googleMap != null) {
            for (int i = 0; i < hospitalList.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(hospitalLocations[i])
                        .title(hospitalList.get(i)));
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hospitalLocations[0], 12));
        }
    }

    @Override
    protected void onResume() { super.onResume(); mapView.onResume(); }
    @Override
    protected void onPause() { super.onPause(); mapView.onPause(); }
    @Override
    protected void onDestroy() { super.onDestroy(); mapView.onDestroy(); }
    @Override
    public void onLowMemory() { super.onLowMemory(); mapView.onLowMemory(); }
}
