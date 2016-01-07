package com.mboyaa.datacollect;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CLientHelpers.ClientClass;
import DatabaseHelper.DatabaseHandler;

public class DataCollectedActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collected);


        Spinner spinner = (Spinner) findViewById(R.id.FilterSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<ClientClass> clientDetails = new ArrayList<ClientClass>();
        Cursor cx = db.getCompCount();

        if (cx.moveToFirst()){
            Toast.makeText(getApplicationContext(), "Total : " + cx.getCount(), Toast.LENGTH_LONG).show();
            do{
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(DatabaseHandler.KEY_ID, cx.getString((cx.getColumnIndex(DatabaseHandler.KEY_ID))));
                map.put(DatabaseHandler.CLIENT_FN, cx.getString((cx.getColumnIndex(DatabaseHandler.CLIENT_FN))));
                map.put(DatabaseHandler.CLIENT_LN,cx.getString((cx.getColumnIndex(DatabaseHandler.CLIENT_LN))));

                songsList.add(map);
                // do what ever you want here
            }while(cx.moveToNext());

        }
        list = (ListView)findViewById(R.id.listz);
        ClientsAdapter adapterZ = new ClientsAdapter(this,songsList);
        list.setAdapter(adapterZ);


        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(),tempArray[position],Toast.LENGTH_SHORT).show();

                TextView clientID = (TextView)view.findViewById(R.id.client_id); // title

                String client_id = clientID.getText().toString();

                Intent i = new Intent(getApplicationContext(), ClientMainActivity.class);
                i.putExtra(DatabaseHandler.KEY_ID, client_id);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_collected_data, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.addNewRecord:
                Intent intent = new Intent(getApplicationContext(), NewRecordActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
