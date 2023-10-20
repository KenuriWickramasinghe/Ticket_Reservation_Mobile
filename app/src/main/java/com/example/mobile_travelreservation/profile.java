package com.example.mobile_travelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mobile_travelreservation.model.Traveler;
import com.example.mobile_travelreservation.remote.APIUtils;
import com.example.mobile_travelreservation.remote.TravelerService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {

    TravelerService travelerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String travelerId = "1";

        travelerService = APIUtils.getTravelerService();

        Call<Traveler> call = travelerService.getTraveler(travelerId);

        call.enqueue(new Callback<Traveler>() {
            @Override
            public void onResponse(Call<Traveler> call, Response<Traveler> response) {
                if (response.isSuccessful()) {
                    Traveler traveler = response.body();
                    updateProfileUI(traveler);
                } else {
                    // Handle the case where the request was not successful
                    Log.e("Profile Error", "Failed to retrieve user details");
                }
            }
            @Override
            public void onFailure(Call<Traveler> call, Throwable t) {
                // Handle the case where the network request fails
                Log.e("Network Error", t.getMessage());
            }
        });
    }

    private void updateProfileUI(Traveler traveler) {
        TextView firstName = findViewById(R.id.firstName);
        TextView lastName = findViewById(R.id.lastName);
        TextView nic = findViewById(R.id.nic);
        TextView emailP = findViewById(R.id.emailP);
        TextView phoneNumberP = findViewById(R.id.phoneNumberP);
        TextView addressP = findViewById(R.id.addressP);

        firstName.setText(traveler.getFirstName());
        lastName.setText(traveler.getLastName());
        nic.setText(traveler.getNIC());
        emailP.setText(traveler.getEmail());
        phoneNumberP.setText(traveler.getPhoneNumber());
        addressP.setText(traveler.getAddress());
    }

}