package com.example.mobile_travelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class TrainListView extends AppCompatActivity {

    String[] trains = {"Udarata Manike", "Dumbara Manike", "Yaldewi", "Kotuwa Express", "Abc"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list_view);
    }
}