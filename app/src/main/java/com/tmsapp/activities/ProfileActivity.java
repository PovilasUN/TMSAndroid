package com.tmsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tmsapp.R;
import com.tmsapp.api.RetrofitClientInstance;
import com.tmsapp.model.Driver;
import com.tmsapp.model.dto.UpdateRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    EditText updateLogin;
    EditText updatePassword;
    EditText updateEmail;
    EditText updatePhone;
    EditText updateLicence;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        updateLogin = findViewById(R.id.updateLoginField);
        updatePassword = findViewById(R.id.updatePasswordField);
        updateEmail = findViewById(R.id.updateEmailField);
        updatePhone = findViewById(R.id.updatePhoneField);
        updateLicence = findViewById(R.id.updateLicenceField);
        update = findViewById(R.id.updateButton);
        ViewModel viewModel = ViewModel.getInstance();
        updateLogin.setText(viewModel.getCurrentDriver().getLoginName());
        updatePassword.setText(viewModel.getCurrentDriver().getPassword());
        updateEmail.setText(viewModel.getCurrentDriver().getEmail());
        updatePhone.setText(viewModel.getCurrentDriver().getPhoneNumber());
        updateLicence.setText(viewModel.getCurrentDriver().getDriverLicenceNumber());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(updateLogin.getText().toString()) || TextUtils.isEmpty(updatePassword.getText().toString()) ||
                TextUtils.isEmpty(updateEmail.getText().toString()) || TextUtils.isEmpty(updatePhone.getText().toString()) ||
                TextUtils.isEmpty(updateLicence.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "All fields must have a value!", Toast.LENGTH_LONG).show();
                } else {
                    updateDriver(viewModel.getCurrentDriver().getId(), viewModel.getCurrentDriver().getName(),
                            viewModel.getCurrentDriver().getSurname(), viewModel.getCurrentDriver().getDateOfBirth());
                }
            }
        });
    }

    public void updateDriver(int id, String name, String surname, String dateOfBirth) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setId(id);
        updateRequest.setName(name);
        updateRequest.setSurname(surname);
        updateRequest.setLoginName(updateLogin.getText().toString());
        updateRequest.setPassword(updatePassword.getText().toString());
        updateRequest.setEmail(updateEmail.getText().toString());
        updateRequest.setPhoneNumber(updatePhone.getText().toString());
        updateRequest.setDateOfBirth(dateOfBirth);
        updateRequest.setDriverLicenceNumber(updateLicence.getText().toString());
        Call<Driver> driverCall = RetrofitClientInstance.getDriverServices().updateDriver(updateRequest);
        driverCall.enqueue(new Callback<Driver>() {

            @Override
            public void onResponse(Call<Driver> call, Response<Driver> response) {
                if (response.isSuccessful()) {
                    Driver driver = response.body();
                    ViewModel vm = ViewModel.getInstance();
                    vm.setCurrentDriver(driver);
                    Toast.makeText(ProfileActivity.this, "Update Successful", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ProfileActivity.this, "Update Failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Driver> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}