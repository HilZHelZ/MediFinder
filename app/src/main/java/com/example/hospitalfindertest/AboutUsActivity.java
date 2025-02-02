package com.example.hospitalfindertest;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Set application description
        TextView appDescriptionTextView = findViewById(R.id.appDescriptionTextView);
        appDescriptionTextView.setText("Welcome to the Hospital Finder app! This application allows users to find hospitals in Selangor, view available services, and track attendance using barcode scanners.");

        // Create a list of developers
        ArrayList<Developer> developers = new ArrayList<>();
        developers.add(new Developer("MUHAMMAD IKHWAN BIN MOHD KHAIRI", "2022838746", R.drawable.id1));
        developers.add(new Developer("MAXXWELL YANG KOUK RUNG", "2022612048", R.drawable.id2));
        developers.add(new Developer("NUR AFRINA IRDINA BINTI MOHD HAZRI", "2022622022", R.drawable.id3));
        developers.add(new Developer("MUHAMMAD ZHARIEF BIN SALAHUDDIN", "2023622924", R.drawable.id4));

        // Set up GridView with a custom adapter
        GridView developerGridView = findViewById(R.id.developerGridView);
        DeveloperAdapter adapter = new DeveloperAdapter(this, developers);
        developerGridView.setAdapter(adapter);
    }
}
