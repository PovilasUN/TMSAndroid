package com.tmsapp.api;

import com.tmsapp.model.Driver;
import com.tmsapp.model.dto.LoginRequest;
import com.tmsapp.model.dto.UpdateRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DriverService {

    @POST("/validateDriver")
    Call<Driver> validateDriver(
            @Body LoginRequest loginRequest
    );

    @GET("/allDrivers")
    Call<List<Driver>> getDrivers();

    @PUT("/updateDriver")
    Call<Driver> updateDriver(
            @Body UpdateRequest updateRequest
    );

    @DELETE("/removeDriver/{id}")
    Call<Void> deleteDriver(
            @Path("id") int id
    );
}
