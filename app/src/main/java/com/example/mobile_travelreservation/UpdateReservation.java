package com.example.mobile_travelreservation;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mobile_travelreservation.model.Reservation;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class UpdateReservation extends AppCompatActivity {

    Reservation reservation = new Reservation();
    String[] classes = {"1st class", "2nd class", "3rd class"};
    String[] ticket_qty = {"1", "2", "3", "4", "5"};
    String[] stations = {"Pettah", "Moratuwa", "Negombo", "Hambanthota", "Gall", "Bambalapitiya", "Ragama"};

    AutoCompleteTextView autoCompleteTextView_classes;
    AutoCompleteTextView autoCompleteTextView_ticket_qty;
    AutoCompleteTextView autoCompleteTextView_dest_station;
    AutoCompleteTextView autoCompleteTextView_arrival_station;

    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems_tkt_qty;
    ArrayAdapter<String> adapterItems_dest_station;
    ArrayAdapter<String> adapterItems_arrival_station;

    //Get viewers
    TextView textViewer_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reservation);

        // Ticket class array
        autoCompleteTextView_classes = findViewById(R.id.update_autocomplete_dropdown_tkt_class);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item, classes);

        autoCompleteTextView_classes.setAdapter(adapterItems);
        autoCompleteTextView_classes.setText("Custom", false); //#####################################
        reservation.setTicketClass("Custom"); // ####################################

        autoCompleteTextView_classes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                reservation.setTicketClass(item);
                Toast.makeText(UpdateReservation.this, item, Toast.LENGTH_SHORT).show();
            }
        });

        // Render the ticket quantity array
        autoCompleteTextView_ticket_qty = findViewById(R.id.update_autocomplete_dropdown_tkt_qty);
        adapterItems_tkt_qty = new ArrayAdapter<String>(this, R.layout.list_item, ticket_qty);

        autoCompleteTextView_ticket_qty.setAdapter(adapterItems_tkt_qty);

        autoCompleteTextView_ticket_qty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                reservation.setTicketCount(Integer.parseInt(item));
                Toast.makeText(UpdateReservation.this, item + " Tickets", Toast.LENGTH_SHORT).show();
            }
        });

        // Destination station array
        autoCompleteTextView_dest_station = findViewById(R.id.update_autocomplete_dropdown_tkt_dept_station);
        adapterItems_dest_station = new ArrayAdapter<String>(this, R.layout.list_item, stations);

        autoCompleteTextView_dest_station.setAdapter(adapterItems_dest_station);

        autoCompleteTextView_dest_station.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                reservation.setDeparture(item);
                Toast.makeText(UpdateReservation.this, item + " Station", Toast.LENGTH_SHORT).show();
            }
        });

        // Arrival station array
        autoCompleteTextView_arrival_station = findViewById(R.id.update_autocomplete_dropdown_tkt_arrival_station);
        adapterItems_arrival_station = new ArrayAdapter<String>(this, R.layout.list_item, stations);

        autoCompleteTextView_arrival_station.setAdapter(adapterItems_arrival_station);

        autoCompleteTextView_arrival_station.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                reservation.setArrival(item);
                Toast.makeText(UpdateReservation.this, item + " Station", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDateTimePicker(View view) {
        textViewer_Date = findViewById(R.id.update_date_viewer);
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);

                if (date.compareTo(currentDate) < 0) {
                    // The date is in the past
                    Toast.makeText(getApplicationContext(), "Please select a valid date", Toast.LENGTH_SHORT).show();
                    return;
                }

                String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime());
                textViewer_Date.setText(selectedDate);

                DateTimeFormatter formatter = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
                }
                LocalDate formattedDate = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    formattedDate = LocalDate.parse(selectedDate, formatter);
                }
                reservation.setDate(formattedDate);

                new TimePickerDialog(UpdateReservation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        String selectedTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(date.getTime());
                        reservation.setTime(selectedTime);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void checkAvailability(View view){

        Button checkAvailability;

        checkAvailability = findViewById(R.id.update_chk_availability);
        checkAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This block of code is executed when the button is clicked
                System.out.println(reservation.getDate() + reservation.getTime() + reservation.getDeparture()+ reservation.getArrival() + reservation.getTicketCount() + reservation.getTicketClass());
            }
        });
    }
}