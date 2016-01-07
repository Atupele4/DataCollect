package CLientHelpers;

import android.os.AsyncTask;

import java.net.URL;

import DatabaseHelper.DatabaseHandler;

/**
 * Created by mboyaa on 1/3/2016.
 */
public class CLientSavingHelper extends AsyncTask<Void,Void,Long> {

    ClientClass _client;
    DatabaseHandler _db = null;

    public CLientSavingHelper(ClientClass client,DatabaseHandler db){
        _client = client;
        _db = db;
    }


    @Override
    protected Long doInBackground(Void... params) {

        _db.saveClientData(_client);
        return null;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }
}
