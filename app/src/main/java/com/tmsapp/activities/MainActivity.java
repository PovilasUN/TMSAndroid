package com.tmsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tmsapp.R;

public class MainActivity extends AppCompatActivity {
    Button profileUpdateButton;
    Button viewDriversButton;
    Button viewDestinationsButton;
    Button viewCheckpointsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileUpdateButton = findViewById(R.id.profileButton);
        viewDriversButton = findViewById(R.id.activeDriversButton);
        viewDestinationsButton = findViewById(R.id.destinationsButton);
        viewCheckpointsButton = findViewById(R.id.checkpointsButton);
        profileUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        viewDriversButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DriverActivity.class));
            }
        });
        viewDestinationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DestinationActivity.class));
            }
        });
        viewCheckpointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CheckpointActivity.class));
            }
        });
    }
}