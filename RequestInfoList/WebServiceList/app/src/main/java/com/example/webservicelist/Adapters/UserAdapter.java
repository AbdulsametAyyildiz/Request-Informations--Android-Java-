package com.example.webservicelist.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.webservicelist.Models.UserList;
import com.example.webservicelist.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    Context context;
    Activity activity;
    List<UserList> list;

    public UserAdapter(Context context, Activity activity, List<UserList> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.listlayout,parent,false);
        TextView name,surname;
        LinearLayout linearLayout;
        name=convertView.findViewById(R.id.name);
        surname=convertView.findViewById(R.id.surname);
        linearLayout = convertView.findViewById(R.id.layoutlist);

        name.setText(list.get(position).getName());
        surname.setText(list.get(position).getSurname());

        return convertView;
    }
}
