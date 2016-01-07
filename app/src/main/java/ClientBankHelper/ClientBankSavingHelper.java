package ClientBankHelper;

import android.os.AsyncTask;

import DatabaseHelper.DatabaseHandler;

/**
 * Created by mboyaa on 1/7/2016.
 */
public class ClientBankSavingHelper extends AsyncTask<Void,Void,Boolean> {

    BankAccountClass _bank;
    DatabaseHandler _db = null;

    public ClientBankSavingHelper(BankAccountClass bank_acc,DatabaseHandler db){
        _bank = bank_acc;
        _db = db;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String propertyID = _db.saveBankAccountData(_bank);
        _db.AddInteraction(propertyID,"BANK DETAILS",_bank.ClientID);
        return null;
    }
}
