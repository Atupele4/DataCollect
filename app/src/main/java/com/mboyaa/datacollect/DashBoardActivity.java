package com.mboyaa.datacollect;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CLientHelpers.ClientClass;
import DatabaseHelper.DatabaseHandler;

public class DashBoardActivity extends AppCompatActivity {
    private Button NewRecordBtn;
    private Button CasesBtn;
    private Button AddNamesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);




        NewRecordBtn = (Button)findViewById(R.id.ViewDataBtn);
        CasesBtn = (Button)findViewById(R.id.CasesBtn);
        AddNamesBtn = (Button)findViewById(R.id.addNamesBtn);

        NewRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataCollectedActivity.class);
                startActivity(intent);
            }
        });

        CasesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CasesActivity.class);
                startActivity(intent);
            }
        });

        AddNamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNewName.class);
                startActivity(intent);
            }
        });


    }
}
