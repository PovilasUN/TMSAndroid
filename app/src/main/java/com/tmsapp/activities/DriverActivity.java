package com.tmsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tmsapp.R;
import com.tmsapp.api.RetrofitClientInstance;
import com.tmsapp.model.Checkpoint;
import com.tmsapp.model.Destination;
import com.tmsapp.model.Driver;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverActivity extends AppCompatActivity {

    ListView driverView;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        driverView = findViewById(R.id.driverView);
        goBack = findViewById(R.id.goBackButton);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Call<List<Driver>> driverCall = RetrofitClientInstance.getDriverServices().getDrivers();
        driverCall.enqueue(new Callback<List<Driver>>() {
            @Override
            public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {
                List<Driver> drivers = response.body();
                ArrayAdapter<Driver> adapter = new ArrayAdapter<>(DriverActivity.this, android.R.layout.simple_list_item_1, drivers);
                driverView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Driver>> call, Throwable t) {

            }
        });
        driverView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Driver driver = (Driver) adapterView.getItemAtPosition(position);
                new AlertDialog.Builder(DriverActivity.this)
                        .setTitle("Delete Driver")
                        .setMessage("Are you sure you want to delete this driver?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Call<Void> deleteCall = RetrofitClientInstance.getDriverServices().deleteDriver(driver.getId());
                                deleteCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {
                                            finish();
                                            startActivity(new Intent(DriverActivity.this, DriverActivity.class));
                                        } else {
                                            Toast.makeText(DriverActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(DriverActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }
}