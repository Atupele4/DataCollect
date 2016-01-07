package com.mboyaa.datacollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ClientBankHelper.BankAccountClass;
import ClientBankHelper.ClientBankSavingHelper;
import DatabaseHelper.DatabaseHandler;

public class ClientsBankActivity extends AppCompatActivity {

    private Button saveBankBtn;
    private String clientID;
    private ClientBankSavingHelper savingAccountTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_bank);

        Intent iA = getIntent();
        clientID = iA.getStringExtra(DatabaseHandler.KEY_ID);

        saveBankBtn = (Button)findViewById(R.id.saveBankDetailsBtn);

        saveBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(savingAccountTask != null){
                    return;
                }

                EditText bankName = (EditText)findViewById(R.id.bankNameTxt);
                String bank_name = String.valueOf(bankName.getText());

                EditText accountNumber = (EditText)findViewById(R.id.accountNumberTxt);
                String account_number = String.valueOf(accountNumber.getText());

                BankAccountClass bank_acc = new BankAccountClass();
                bank_acc.BankName = bank_name;
                bank_acc.AccountNumber = account_number;
                bank_acc.ClientID = clientID;

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                savingAccountTask = new ClientBankSavingHelper(bank_acc,db);
                savingAccountTask.execute((Void) null);
            }
        });
    }
}
