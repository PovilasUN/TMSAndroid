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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckpointActivity extends AppCompatActivity {

    ListView checkpointView;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkpoint);
        checkpointView = findViewById(R.id.checkpointView);
        goBack = findViewById(R.id.goBackButton);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Call<List<Checkpoint>> checkpointCall = RetrofitClientInstance.getCheckpointServices().getCheckpoints();
        checkpointCall.enqueue(new Callback<List<Checkpoint>>() {
            @Override
            public void onResponse(Call<List<Checkpoint>> call, Response<List<Checkpoint>> response) {
                List<Checkpoint> checkpoints = response.body();
                ArrayAdapter<Checkpoint> adapter = new ArrayAdapter<>(CheckpointActivity.this, android.R.layout.simple_list_item_1, checkpoints);
                checkpointView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Checkpoint>> call, Throwable t) {
                Toast.makeText(CheckpointActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        checkpointView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Checkpoint checkpoint = (Checkpoint) adapterView.getItemAtPosition(position);
                new AlertDialog.Builder(CheckpointActivity.this)
                        .setTitle("Delete Checkpoint")
                        .setMessage("Are you sure you want to delete this checkpoint?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Call<Void> deleteCall = RetrofitClientInstance.getCheckpointServices().deleteCheckpoint(checkpoint.getId());
                                deleteCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {
                                            finish();
                                            startActivity(new Intent(CheckpointActivity.this, CheckpointActivity.class));
                                        } else {
                                            Toast.makeText(CheckpointActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(CheckpointActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
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