package com.example.mobile_travelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobile_travelreservation.model.LoginRequest;
import com.example.mobile_travelreservation.remote.APIUtils;
import com.example.mobile_travelreservation.remote.TravelerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {

    TravelerService travelerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.login_email);
        EditText password = findViewById(R.id.login_password);

        Button login = findViewById(R.id.login_btn);
        travelerService = APIUtils.getTravelerService();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
               loginTraveler(email.getText().toString(), password.getText().toString());
            }

            void checkDataEntered() {
                if (isEmpty(email)) {
                    email.setError("This field is required");
                }
                if (isEmpty(password)) {
                    password.setError("This field is required");
                }
            }

            boolean isEmpty(EditText text) {
                CharSequence str = text.getText().toString();
                return TextUtils.isEmpty(str);
            }

            private void loginTraveler(String email, String password) {
                Log.e("traveler",email);

                Call<LoginRequest> call = travelerService.login(email, password);
                call.enqueue(new Callback<LoginRequest>() {
                    @Override
                    public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                        if (response.isSuccessful()) {

                            //LoginRequest loginResponse = response.body();
                            Intent i = new Intent(getApplicationContext(), updateProfile.class);
                            startActivity(i);

                        } else {
                            // Handle the case where the login was not successful
                            Log.e("Login Error", "Login failed");
                        }
                    }

                    public void onFailure(Call<LoginRequest> call, Throwable t) {
                        // Handle the case where the network request fails
                        Log.e("Network Error", t.getMessage());
                    }
                });
            }
        });
    }
}