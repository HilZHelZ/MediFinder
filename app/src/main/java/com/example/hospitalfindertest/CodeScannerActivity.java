package com.example.hospitalfindertest;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final String TAG = "CodeScannerActivity";
    private static final String CHANNEL_ID = "qr_scan_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scanner);

        // Check for camera permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            initializeScannerView();
        }

        // Create notification channel for QR scan notifications
        createNotificationChannel();
    }

    // Initialize the ZXingScannerView
    private void initializeScannerView() {
        mScannerView = new ZXingScannerView(this);
        mScannerView.setResultHandler(this); // Set the handler for scan results

        // Attach the scanner view to the FrameLayout
        findViewById(R.id.frame_layout_camera).post(() -> {
            ((android.widget.FrameLayout) findViewById(R.id.frame_layout_camera)).addView(mScannerView);
            mScannerView.startCamera(); // Start the camera
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeScannerView();
            } else {
                Toast.makeText(this, "Camera permission is required to scan QR codes.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScannerView != null) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        String scannedData = rawResult.getText(); // Scanned QR code content
        Log.v(TAG, "Scan Result: " + scannedData);

        // List of hospitals
        List<String> hospitalList = new ArrayList<>();
        hospitalList.add("Medifeet");
        hospitalList.add("Avisena");
        hospitalList.add("Hospital Shah Alam");
        hospitalList.add("Farmasi Pusat Kesihatan UiTM");
        hospitalList.add("General Surgery");
        hospitalList.add("Hospital Umra");
        hospitalList.add("Hospital Pakar Pesona Temerloh");
        hospitalList.add("Hi-Tech Specialist Hospital");

        // Check if the scanned data matches any hospital name
        if (hospitalList.contains(scannedData)) {
            // Match found: Show a dialog and send notification
            new AlertDialog.Builder(this)
                    .setTitle("Check-In Successful")
                    .setMessage("You have successfully checked into " + scannedData + ".")
                    .setPositiveButton("Proceed", (dialog, which) -> {
                        sendNotification(scannedData); // Send notification
                        Intent intent = new Intent(CodeScannerActivity.this, ServiceListActivity.class);
                        intent.putExtra("hospitalName", scannedData); // Pass the hospital name
                        startActivity(intent); // Navigate to the service list activity
                        finish(); // Finish the current activity
                    })
                    .show();
        } else {
            // No match found: Notify the user
            new AlertDialog.Builder(this)
                    .setTitle("Hospital Not Found")
                    .setMessage("The scanned QR code does not match any hospital in the list.\n\nScanned Data: " + scannedData)
                    .setPositiveButton("Try Again", (dialog, which) -> mScannerView.resumeCameraPreview(CodeScannerActivity.this))
                    .show();
        }
    }

    private void sendNotification(String hospitalName) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Build the notification
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification) // Use your custom notification icon
                        .setContentTitle("Hospital Check-In")
                        .setContentText("You successfully checked into " + hospitalName)
                        .setAutoCancel(true);

        // Show the notification
        if (notificationManager != null) {
            notificationManager.notify(1, notificationBuilder.build());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "QR Scan Notifications";
            String description = "Notifications for successful QR code scans";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
