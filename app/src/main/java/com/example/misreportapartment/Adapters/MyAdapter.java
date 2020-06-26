package com.example.misreportapartment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.misreportapartment.Model.Guest;
import com.example.misreportapartment.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Guest> arrayList;

    public MyAdapter(Context context, ArrayList<Guest> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_users_form, null);
            TextView t1_name = convertView.findViewById(R.id.listView1);
            TextView t1_phone = convertView.findViewById(R.id.listView2);
            Guest guestModel = arrayList.get(position);
            t1_name.setText(guestModel.getUserName());
            t1_phone.setText(guestModel.getPhone());

        return convertView;
    }
}
