package com.mboyaa.datacollect;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CLientHelpers.ClientClass;
import DatabaseHelper.DatabaseHandler;
import InteractionHelper.InteractionAdapter;

public class ClientMainActivity extends AppCompatActivity {


    private ListView list;
    String clientID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Intent iA = getIntent();
        clientID = iA.getStringExtra(DatabaseHandler.KEY_ID);
        Cursor cx = db.getClientRecord(clientID);

        if (cx.moveToFirst()){

            do{
                TextView clientName = (TextView) findViewById(R.id.clientNameMain);
                TextView clientDob = (TextView) findViewById(R.id.clientDOB);

                String clientNameString = cx.getString((cx.getColumnIndex(DatabaseHandler.CLIENT_FN))) + " " + cx.getString((cx.getColumnIndex(DatabaseHandler.CLIENT_LN)));
                clientName.setText(clientNameString);
                clientDob.setText(cx.getString((cx.getColumnIndex(DatabaseHandler.CLIENT_DOB))));

                // do what ever you want here
            }while(cx.moveToNext());

        }



        ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

        List<ClientClass> clientDetails = new ArrayList<ClientClass>();
        cx = db.getInteractionData(clientID);

        if (cx.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<>();

                map.put(DatabaseHandler.KEY_ID, cx.getString((cx.getColumnIndex(DatabaseHandler.KEY_ID))));
                map.put(DatabaseHandler.INTERACTION_TYPE, cx.getString((cx.getColumnIndex(DatabaseHandler.INTERACTION_TYPE))));

                songsList.add(map);
                // do what ever you want here
            }while(cx.moveToNext());

        }
        list = (ListView)findViewById(R.id.ClientInteractions);
        InteractionAdapter adapterZ = new InteractionAdapter(this,songsList);
        list.setAdapter(adapterZ);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.AddProperty:
                Intent intent = new Intent(getApplicationContext(), PropertyActivity.class);
                intent.putExtra(DatabaseHandler.KEY_ID, clientID);
                startActivity(intent);
                return true;
            case R.id.AddBankAccount:
                Intent intent2 = new Intent(getApplicationContext(), ClientsBankActivity.class);
                intent2.putExtra(DatabaseHandler.KEY_ID, clientID);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
