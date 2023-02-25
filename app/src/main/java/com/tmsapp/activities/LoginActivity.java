package com.tmsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tmsapp.R;
import com.tmsapp.api.RetrofitClientInstance;
import com.tmsapp.model.Driver;
import com.tmsapp.model.dto.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText login;
    EditText password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginNameField);
        password = findViewById(R.id.passwordField);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(login.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Username or Password Required", Toast.LENGTH_LONG).show();
                } else {
                    loginDriver();
                }
            }
        });
    }

    public void loginDriver() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLoginName(login.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Call<Driver> driverCall = RetrofitClientInstance.getDriverServices().validateDriver(loginRequest);
        driverCall.enqueue(new Callback<Driver>() {

            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                if (response.isSuccessful()) {
                    Driver driver = response.body();
                    ViewModel vm = ViewModel.getInstance();
                    vm.setCurrentDriver(driver);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
