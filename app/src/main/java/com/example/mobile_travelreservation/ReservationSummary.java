package com.example.mobile_travelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReservationSummary extends AppCompatActivity {

    TextView textView_from;
    TextView textView_to;
    TextView textView_trainName;
    TextView textView_date;
    TextView textView_dept_time;
    TextView textView_arrival_time;
    TextView textView_tkt_class;
    TextView textView_tkt_qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        textView_from = findViewById(R.id.from_id);
        textView_to = findViewById(R.id.to_id);
        textView_trainName = findViewById(R.id.train_name_value);
        textView_date = findViewById(R.id.date_value);
        textView_dept_time = findViewById(R.id.dept_time_value);
        textView_arrival_time = findViewById(R.id.arrival_time_value);
        textView_tkt_class = findViewById(R.id.tkt_class_value);
        textView_tkt_qty = findViewById(R.id.tkt_qty_value);

        // data from previous page and use them
        final String sample_from = "Moratuwa";
        final String sample_to = "Pettah";
        final String sample_train_name = "Udarata Manike";
        final String sample_date = "24th November, 2023";
        final String sample_dept_time = "03:43 AM";
        final String sample_arrival_time = "05: 00 AM";
        final String sample_tkt_class = "1st Class";
        final String sample_tkt_qty = "2";


        textView_from.setText("From: "+sample_from);
        textView_to.setText("To: "+ sample_to);
        textView_trainName.setText(sample_train_name);
        textView_date.setText(sample_date);
        textView_dept_time.setText(sample_dept_time);
        textView_arrival_time.setText(sample_arrival_time);
        textView_tkt_class.setText(sample_tkt_class);
        textView_tkt_qty.setText(sample_tkt_qty);
    }

    public void confirmReservation(View view){
        Button confirm_btn;
        confirm_btn = findViewById(R.id.confirm_btn);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Reservation Confirmed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void gotBackReservation(View view){
        Button go_back_btn;
        go_back_btn = findViewById(R.id.go_back_btn);

        go_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Go back clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }
}