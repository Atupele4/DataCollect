package com.mboyaa.datacollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import CLientHelpers.CLientSavingHelper;
import CLientHelpers.ClientClass;
import DatabaseHelper.DatabaseHandler;

public class NewRecordActivity extends AppCompatActivity {

    private Button SaveNewRecordBtn;
    private CLientSavingHelper SaveClientTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);

        SaveNewRecordBtn = (Button)findViewById(R.id.saveRecordBtn);

        SaveNewRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SaveClientTask != null){
                    return;
                }


                EditText  FistName = (EditText)findViewById(R.id.firstNameTxt);
                String Fname = String.valueOf(FistName.getText());

                EditText  LastName = (EditText)findViewById(R.id.lastNameTxt);
                String Lname = String.valueOf(LastName.getText());

                EditText  CLientDOB = (EditText)findViewById(R.id.clientDOBTxt);
                String clientDOB = String.valueOf(CLientDOB.getText());

                EditText  ClientNRC = (EditText)findViewById(R.id.clientNRCTxt);
                String clientNRC = String.valueOf(ClientNRC.getText());

                Spinner  ClientGender = (Spinner)findViewById(R.id.genderSpinner);
                String clientGender = String.valueOf(ClientGender);


                ClientClass client = new ClientClass();
                client.C_FN = Fname;
                client.C_LN = Lname;
                client.C_AGE = clientDOB;
                client.C_NRC = clientNRC;

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                SaveClientTask = new CLientSavingHelper(client,db);
                SaveClientTask.execute((Void) null);
               /* Intent intent = new Intent(getApplicationContext(), ClientMainActivity.class);
                startActivity(intent);*/
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.genderSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
