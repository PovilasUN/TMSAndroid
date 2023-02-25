package com.tmsapp.api;

import com.tmsapp.model.Checkpoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CheckpointService {

    @GET("/allCheckpoints")
    Call<List<Checkpoint>> getCheckpoints();

    @DELETE("/removeCheckpoint/{id}")
    Call<Void> deleteCheckpoint(
            @Path("id") int id
    );
}
