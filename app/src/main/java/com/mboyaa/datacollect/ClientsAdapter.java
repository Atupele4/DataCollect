package com.mboyaa.datacollect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import DatabaseHelper.DatabaseHandler;

public class ClientsAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

    public ClientsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.client_row, null);

        TextView title = (TextView)vi.findViewById(R.id.client_name); // title
        TextView clientID = (TextView)vi.findViewById(R.id.client_id); // title

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        
        // Setting all values in listview
        title.setText(song.get(DatabaseHandler.CLIENT_FN) + " " + song.get(DatabaseHandler.CLIENT_LN));
        clientID.setText(song.get(DatabaseHandler.KEY_ID));
        return vi;
    }


    @Override
    public void onClick(View v) {
        TextView clientID = (TextView)v.findViewById(R.id.client_id); // title

        String client_id = clientID.getText().toString();

        int sx = 0;
    }
}