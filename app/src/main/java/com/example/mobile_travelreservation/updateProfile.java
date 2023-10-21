package com.example.mobile_travelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobile_travelreservation.model.Traveler;
import com.example.mobile_travelreservation.remote.APIUtils;
import com.example.mobile_travelreservation.remote.TravelerService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateProfile extends AppCompatActivity {

    TravelerService travelerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        EditText nic = findViewById(R.id.nicPU);
        EditText firstName = findViewById(R.id.f_NamePU);
        EditText lastName = findViewById(R.id.l_NamePU);
        Spinner title = findViewById(R.id.titlePU);
        EditText phoneNumber = findViewById(R.id.phoneNumberPU);
        EditText email = findViewById(R.id.emailPU);
        Spinner address = findViewById(R.id.addressPU);

        Button update = findViewById(R.id.update_button);
        travelerService = APIUtils.getTravelerService();

        String userId = "1";
//        Bundle extras = getIntent().getExtras();
//        final String userId = extras.getString("user_id");

        Call<Traveler> call = travelerService.getTraveler(userId);
            call.enqueue(new Callback<Traveler>() {
                @Override
                public void onResponse(Call<Traveler> call, Response<Traveler> response) {
                    if (response.isSuccessful()) {
                        Traveler traveler = response.body();
                        nic.setText(traveler.getNIC());
                        firstName.setText(traveler.getFirstName());
                        lastName.setText(traveler.getLastName());
//                        String selectedTitle = traveler.getTitle();
//                        String selectedAddress = traveler.getAddress();
//
//                        // Set the selected values in the Spinners
//                        int titlePosition = titleAdapter.getPosition(selectedTitle);
//                        int addressPosition = addressAdapter.getPosition(selectedAddress);
//                        titleSpinner.setSelection(titlePosition);
//                        addressSpinner.setSelection(addressPosition);

                        phoneNumber.setText(traveler.getPhoneNumber());
                        email.setText(traveler.getEmail());
                    } else {
                        Toast.makeText(updateProfile.this, "Failed to load traveler data", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Traveler> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                Traveler traveler = new Traveler();
                traveler.setNIC(nic.getText().toString());;
                traveler.setFirstName(firstName.getText().toString());
                traveler.setLastName(lastName.getText().toString());

                String selectedTitle = title.getSelectedItem().toString();
                traveler.setTitle(selectedTitle);

                traveler.setPhoneNumber(phoneNumber.getText().toString());
                traveler.setEmail(email.getText().toString());

                String selectedAddress = address.getSelectedItem().toString();
                traveler.setTitle(selectedAddress);

                Log.e("traveler", traveler.getNIC().toString());
                updateTraveler(userId,traveler);
            }
                void checkDataEntered(){
                    if(isEmpty(nic)){
                        nic.setError("This field is required");
                    }
                    if(isEmpty(firstName)){
                        firstName.setError("This field is required");
                    }
                    if(isEmpty(phoneNumber)){
                        phoneNumber.setError("This field is required");
                    }
                    if(isEmail(email)){
                        email.setError("Invalid Email Address");
                    }

                }

                boolean isEmpty(EditText text){
                    CharSequence str = text.getText().toString();
                    return TextUtils.isEmpty(str);
                }
                boolean isEmail(EditText text){
                    CharSequence email = text.getText().toString();
                    return (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
                }

                public void updateTraveler(String id,Traveler traveler){
                    Call<Traveler> call = travelerService.updateTraveler(id,traveler);
                    call.enqueue(new Callback<Traveler>() {
                        @Override
                        public void onResponse(Call<Traveler> call, Response<Traveler> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(updateProfile.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(updateProfile.this, "User did not created successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Traveler> call, Throwable t) {
                            Log.e("ERROR: ", t.getMessage());
                        }
                    });
                }

            });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.search) {
                    startActivity(new Intent(getApplicationContext(), TrainList.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.add) {
                    startActivity(new Intent(getApplicationContext(), SearchTrain.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.history) {
                    // Handle the "History" item
                    return true;
                } else if (itemId == R.id.person) {
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
        }
}