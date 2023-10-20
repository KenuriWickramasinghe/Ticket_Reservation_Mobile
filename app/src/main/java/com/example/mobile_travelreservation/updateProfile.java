package com.example.mobile_travelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobile_travelreservation.model.Traveler;
import com.example.mobile_travelreservation.remote.APIUtils;
import com.example.mobile_travelreservation.remote.TravelerService;

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

        Bundle extras = getIntent().getExtras();
        String userId = ""; // Declare userId outside the if block

        if (extras != null) {
            userId = extras.getString("user_id"); // Assign value to userId
        }

        String finalUserId = userId;
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                Traveler traveler = new Traveler();
                traveler.setNIC(nic.getText().toString());;
                traveler.setFirstName(firstName.getText().toString());
                traveler.setLastName(lastName.getText().toString());
                // traveler.setTitle(title.toString());
                traveler.setPhoneNumber(phoneNumber.getText().toString());
                traveler.setEmail(email.getText().toString());
                // traveler.setAddress(address.toString());

                Log.e("traveler", traveler.getNIC().toString());
                updateTraveler(finalUserId,traveler);
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
    }
}