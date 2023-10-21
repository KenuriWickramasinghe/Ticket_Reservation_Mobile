package com.example.mobile_travelreservation;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrainList extends AppCompatActivity {

    String[] trains = {"Udarata Manike", "Dumbara Manike", "Yaldewi", "Kotuwa Express", "Abc", "Udarata Manike", "Dumbara Manike", "Yaldewi", "Kotuwa Express", "Abc", "Udarata Manike", "Dumbara Manike", "Yaldewi", "Kotuwa Express", "Abc"};

    String[] trainNameList = {"Udarata Manike", "Dumbara Manike", "Yaldewi", "Kotuwa Express", "Abc",};
    String[] dateList = {"12 Oct, 2023", "13 Oct, 2023", "14 Oct, 2023", "15 Oct, 2023", "16 Oct, 2023"};
    String[] departureTimeList = {"11:23 AM", "12:23 AM","10:23 AM", "09:23 AM","08:23 AM"};
    String[] arrivalTimeList = {"11:23 PM", "12:23 PM","10:23 PM", "09:23 PM","08:23 PM"};
    String[] fromList = {"Moratuwa", "Bambalapitiya", "Ella", "Ragama", "Maradana"};
    String[] toList = {"Moratuwa", "Bambalapitiya", "Ella", "Ragama", "Maradana"};


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);

        listView = findViewById(R.id.train_listView_id);

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),trainNameList,dateList, departureTimeList, arrivalTimeList, fromList, toList);
        listView.setAdapter(customBaseAdapter);


        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_train_list_view, R.id.train_list_item_trainName, trains);
        //listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "ID: " + i, Toast.LENGTH_SHORT).show();

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