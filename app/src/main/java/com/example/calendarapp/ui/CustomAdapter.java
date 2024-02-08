package com.example.calendarapp.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<String> itemList;

    public CustomAdapter(ArrayList<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Implement your view creation logic here
        // Example:
        TextView textView = new TextView(parent.getContext());
        textView.setText(itemList.get(position));
        return textView;
    }
    public void addItem(String newItem) {
        itemList.add(newItem);
        notifyDataSetChanged();
    }
}
