package com.example.mobile_travelreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomBaseAdapter extends BaseAdapter {


    Context context;
    String trainNameList[];
    String dateList[];
    String departureTimeList[];
    String arrivalTimeList[];
    String fromList[];
    String toList[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String[] trainNameList, String[] dateList,
                             String[] departureTimeList, String[] arrivalTimeList, String[] fromList, String[] toList){

        this.context = ctx;
        this.trainNameList = trainNameList;
        this.dateList = dateList;
        this.departureTimeList = departureTimeList;
        this.arrivalTimeList = arrivalTimeList;
        this.fromList = fromList;
        this.toList = toList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return trainNameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_train_list_view, null);

        TextView textView_trainName = convertView.findViewById(R.id.train_list_item_trainName);
        TextView textView_date = convertView.findViewById(R.id.train_card_date_value);
        TextView textView_deptTime = convertView.findViewById(R.id.train_list_item_dept_time_value);
        TextView textView_arrivalTime = convertView.findViewById(R.id.train_list_item_arrival_time_value);
        TextView textView_from = convertView.findViewById(R.id.train_list_item_from);
        TextView textView_to = convertView.findViewById(R.id.train_list_item_to);

        textView_trainName.setText(trainNameList[position]);
        textView_date.setText(dateList[position]);
        textView_deptTime.setText(departureTimeList[position]);
        textView_arrivalTime.setText(arrivalTimeList[position]);
        textView_from.setText("From: "+fromList[position]);
        textView_to.setText("To: "+toList[position]);

        return convertView;
    }
}
