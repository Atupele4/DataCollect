package ClientPropertyHelper;

import android.os.AsyncTask;

import CLientHelpers.ClientClass;
import DatabaseHelper.DatabaseHandler;

/**
 * Created by mboyaa on 1/4/2016.
 */
public class PropertySavingHelper extends AsyncTask<Void,Void,Boolean> {

    PropertyClass _property;
    DatabaseHandler _db = null;


    public PropertySavingHelper(PropertyClass property,DatabaseHandler db){
        _property = property;
        _db = db;
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        String propertyID = _db.savePropertyData(_property);
        _db.AddInteraction(propertyID,"PROPERTY",_property.PropertyOwnerID);

        return null;
    }
}
