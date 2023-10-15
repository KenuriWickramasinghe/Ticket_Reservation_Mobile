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

public class registration extends AppCompatActivity {

    TravelerService travelerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText nic = findViewById(R.id.nic);
        EditText firstName = findViewById(R.id.f_Name);
        EditText lastName = findViewById(R.id.l_Name);
        Spinner title = findViewById(R.id.title);
        EditText phoneNumber = findViewById(R.id.phoneNumber);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText com_password = findViewById(R.id.con_password);
        Spinner address = findViewById(R.id.address);

        Button register = findViewById(R.id.reg_button);
        travelerService = APIUtils.getTravelerService();

        Bundle extras = getIntent().getExtras();

        register.setOnClickListener(new View.OnClickListener() {
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
                traveler.setPassword(password.getText().toString());

                Log.e("traveler",traveler.getNIC().toString());
                addTraveler(traveler);


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
                if(isEmpty(password)){
                    password.setError("This field is required");
                }
                if(isEmpty(com_password)){
                    com_password.setError("This field is required");
                }
                if(!password.getText().toString().equals(com_password.getText().toString())){
                    Toast.makeText(registration.this,"Password Not matching",Toast.LENGTH_SHORT).show();
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

            public void addTraveler(Traveler traveler){
                Call<Traveler> call = travelerService.addTraveler(traveler);
                call.enqueue(new Callback<Traveler>() {
                    @Override
                    public void onResponse(Call<Traveler> call, Response<Traveler> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(registration.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(registration.this, "User did not created successfully!", Toast.LENGTH_SHORT).show();
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