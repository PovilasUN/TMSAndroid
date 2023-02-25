package com.tmsapp.api;

import com.tmsapp.model.Destination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DestinationService {

    @GET("/allDestinations")
    Call<List<Destination>> getDestinations();

    @DELETE("/removeDestination/{id}")
    Call<Void> deleteDestination(
            @Path("id") int id
    );
}
