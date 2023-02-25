package com.tmsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tmsapp.R;
import com.tmsapp.api.RetrofitClientInstance;
import com.tmsapp.model.Checkpoint;
import com.tmsapp.model.Destination;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationActivity extends AppCompatActivity {

    ListView destinationView;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        destinationView = findViewById(R.id.destinationView);
        goBack = findViewById(R.id.goBackButton);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Call<List<Destination>> destinationCall = RetrofitClientInstance.getDestinationServices().getDestinations();
        destinationCall.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                if (response.isSuccessful()) {
                    List<Destination> destinations = response.body();
                    ArrayAdapter<Destination> adapter = new ArrayAdapter<>(DestinationActivity.this, android.R.layout.simple_list_item_1, destinations);
                    destinationView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                Toast.makeText(DestinationActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        destinationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Destination destination = (Destination) adapterView.getItemAtPosition(position);
                new AlertDialog.Builder(DestinationActivity.this)
                        .setTitle("Delete Destination")
                        .setMessage("Are you sure you want to delete this destination?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Call<Void> deleteCall = RetrofitClientInstance.getDestinationServices().deleteDestination(destination.getId());
                                deleteCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {
                                            finish();
                                            startActivity(new Intent(DestinationActivity.this, DestinationActivity.class));
                                        } else {
                                            Toast.makeText(DestinationActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(DestinationActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
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