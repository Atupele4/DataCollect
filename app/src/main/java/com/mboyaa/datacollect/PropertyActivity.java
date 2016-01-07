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
import ClientPropertyHelper.PropertyClass;
import ClientPropertyHelper.PropertySavingHelper;
import DatabaseHelper.DatabaseHandler;

public class PropertyActivity extends AppCompatActivity {


    private Button saveProperty;
    private PropertySavingHelper SavePropertyTask = null;
    private String clientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        Intent iA = getIntent();
        clientID = iA.getStringExtra(DatabaseHandler.KEY_ID);

        saveProperty = (Button)findViewById(R.id.savePropertyBtn);

        saveProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText PropertyID = (EditText)findViewById(R.id.propertyID);
                String propertyID = String.valueOf(PropertyID.getText());

                Spinner  propertyType = (Spinner)findViewById(R.id.PropertyTypeSpinner);
                //String Lname = String.valueOf(propertyType.getText());

                EditText  propertyOwnerNrc = (EditText)findViewById(R.id.propertyOwnerNrc);
                String PropertyOwnerNrc = String.valueOf(propertyOwnerNrc.getText());

                EditText  propertDescription = (EditText)findViewById(R.id.propertDescription);
                String PropertDescription = String.valueOf(propertDescription.getText());


                PropertyClass property = new PropertyClass();
                property.PropertyID = propertyID;
                property.PropertyType = "";
                property.PropertyOwnerNrc = PropertyOwnerNrc;
                property.PropertyDescription = PropertDescription;
                property.PropertyOwnerID = clientID;

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                SavePropertyTask = new PropertySavingHelper(property,db);
                SavePropertyTask.execute((Void) null);

//                Intent intent = new Intent(getApplicationContext(), DataCollectedActivity.class);
//                startActivity(intent);
            }
        });





        Spinner spinner = (Spinner) findViewById(R.id.PropertyTypeSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.property_type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
