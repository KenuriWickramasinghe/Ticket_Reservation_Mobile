package com.example.mobile_travelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobile_travelreservation.model.Traveler;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.login_email);
        EditText password = findViewById(R.id.login_password);

        Button login = findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                Traveler traveler = new Traveler();
                traveler.setEmail(email.getText().toString());
                traveler.setAddress(password.getText().toString());

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
        });
    }
}