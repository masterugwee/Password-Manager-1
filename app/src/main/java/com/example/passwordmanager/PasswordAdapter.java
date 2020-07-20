package com.example.passwordmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PasswordAdapter extends ArrayAdapter<Passwords> {
    private List<Passwords> passwordsList = new ArrayList<>();
    public PasswordAdapter(Context context, int textViewResourceId, ArrayList<Passwords> list){
        super(context, textViewResourceId, list);
        passwordsList = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.activity_list_layout, null);
        TextView name = (TextView)v.findViewById(R.id.name);
        TextView username = (TextView)v.findViewById(R.id.username);
        ImageView threeDots = (ImageView)v.findViewById(R.id.viewPopUp);
        name.setText(passwordsList.get(position).getName());
        username.setText(passwordsList.get(position).getUsername());
        threeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Add intent to the view page here.
                // TODO: The next page should contain the edit, delete, copy to clipboard options etc.
            }
        });
        return v;
    }
}
