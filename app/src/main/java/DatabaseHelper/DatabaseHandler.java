package DatabaseHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import CLientHelpers.ClientClass;
import ClientBankHelper.BankAccountClass;
import ClientPropertyHelper.PropertyClass;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "clientDataCaptureDB";

    //Contacts table name
    private static final String CLIENTS_DATA_TABLE = "clientsData";
    private static final String CLIENTS_PROPERTY_TABLE = "CompanyTable";
    private static final String INTERACTION_TABLE = "FollowTable";
    private static final String BANKDETAILS_TABLE = "bankDetailsTable";

    //Contacts Table Columns names
    public static final String CLIENT_FN = "clientFN";
    public static final String CLIENT_LN = "clientLN";
    public static final String CLIENT_DOB = "clientDOB";
    public static final String CLIENT_SEX = "clientSEX";
    public static final String CLIENT_PHONE = "clientPHONE";

    //Property Table
    public static final String CL_PROPERTY_ID = "propertyID";
    public static final String CL_PROPERTY_TYPE = "propertyTYPE";
    public static final String CL_PROPERTY_OWNER = "propertyOWNER";
    public static final String CL_PROPERTY_DESCRIBE = "propertyDESCRIBE";

    //Interaction Table
    public static final String INTERACTION_TYPE = "interactionType";
    public static final String INTERACTION_ID = "interactionID";
    public static final String INTERACTION_CLIENT_ID = "interactionClientID";

    public static final String BANK_NAME = "bankName";
    public static final String BANK_ACCOUNT_NUMBER = "bankAccountNumber";
    public static final String BANK_ACCOUNT_TYPE = "bankType";
    public static final String BANK_OWNER_ID = "bankOwner_ID";

    //General keys
    public static final String KEY_DATETIME = "DateTimeSent";
    public static final String KEY_ID = "ID";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_CLIENTS_TABLE = "CREATE TABLE " + CLIENTS_DATA_TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + CLIENT_FN + " TEXT,"
            + CLIENT_LN + " TEXT,"
            + CLIENT_DOB + " TEXT,"
            + CLIENT_SEX + " TEXT,"
            + CLIENT_PHONE + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";
    
   // Todo table create statement
    private static final String CREATE_INTERACTION_TABLE = "CREATE TABLE " + INTERACTION_TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + INTERACTION_ID + " TEXT,"
            + INTERACTION_CLIENT_ID + " TEXT,"
            + INTERACTION_TYPE + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";

            // Todo table create statement
    private static final String CREATE_PROPERTY_TABLE = "CREATE TABLE " + CLIENTS_PROPERTY_TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + CL_PROPERTY_ID + " TEXT,"
            + CL_PROPERTY_TYPE + " TEXT,"
            + CL_PROPERTY_OWNER + " TEXT,"
            + CL_PROPERTY_DESCRIBE + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";

    // Todo table create statement
    private static final String CREATE_BANK_TABLE = "CREATE TABLE " + BANKDETAILS_TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + BANK_NAME + " TEXT,"
            + BANK_ACCOUNT_NUMBER + " TEXT,"
            + BANK_ACCOUNT_TYPE + " TEXT,"
            + BANK_OWNER_ID + " TEXT,"
            + KEY_DATETIME + " DATETIME" + ")";
    


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase SqlDB;
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLIENTS_TABLE);
        db.execSQL(CREATE_PROPERTY_TABLE);
        db.execSQL(CREATE_INTERACTION_TABLE);
        db.execSQL(CREATE_BANK_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTS_DATA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTS_PROPERTY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INTERACTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BANKDETAILS_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void saveClientData(ClientClass client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLIENT_FN, client.getC_FN()); // Contact Name
        values.put(CLIENT_LN, client.getC_LN()); // Contact Phone
        values.put(CLIENT_DOB, client.getC_AGE()); // Contact Phone

        // Inserting Row
        db.insert(CLIENTS_DATA_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new contact
    public void AddInteraction(String interactionID,String interactionType,String clientID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INTERACTION_ID, interactionID); // Contact Name
        values.put(INTERACTION_TYPE, interactionType); // Contact Phone
        values.put(INTERACTION_CLIENT_ID, clientID); // Contact Phone

        // Inserting Row
        db.insert(INTERACTION_TABLE, null, values);
        db.close(); // Closing database connection
    }



     // Adding new contact
    public String savePropertyData(PropertyClass property) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CL_PROPERTY_ID, property.getPropertyID()); // Contact Name
        values.put(CL_PROPERTY_TYPE, property.getPropertyType()); // Contact Phone
        values.put(CL_PROPERTY_OWNER, property.getPropertyOwnerNrc()); // Contact Phone
        values.put(CL_PROPERTY_DESCRIBE, property.getPropertyDescription()); // Contact Phone

        // Inserting Row
        db.insert(CLIENTS_PROPERTY_TABLE, null, values);
        db.close(); // Closing database connection

        return property.getPropertyID();
    }

     // Adding new contact
    public String saveBankAccountData(BankAccountClass bank) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BANK_NAME, bank.getBantName()); // Contact Name
        values.put(BANK_ACCOUNT_NUMBER, bank.AccountNumber); // Contact Phone
        values.put(BANK_OWNER_ID,bank.getClientID()); // Contact Phone
        //values.put(BANK_ACCOUNT_TYPE, bank.getAccountType()); // Contact Phone

        // Inserting Row
        db.insert(BANKDETAILS_TABLE, null, values);
        db.close(); // Closing database connection

        return bank.getAccountNumber();
    }


    // Insert Default Company List into Company Table
/*   public void InsertCompanyToDB(CompanyDetails companyDetails,boolean flag) {
       SqlDB = this.getWritableDatabase();
       if(flag){
           List<String> companyNames = new ArrayList<String>();

           companyNames.add("ZCAS");
           companyNames.add("POLICE");
           companyNames.add("ZRA");
           companyNames.add("LWSC");
           companyNames.add("LCC");
           companyNames.add("UNZA");
           companyNames.add("FAZ");
           companyNames.add("BONGOHIVE");
           companyNames.add("CBU");

           for (int i = 0; i <= companyNames.size(); i++ ){

              ContentValues values = new ContentValues();
               try{
                  values.put(CLIENT_LN, companyNames.get(i));
                  values.put(KEY_COMPANY_NAME, companyNames.get(i));
                  values.put(KEY_COMPANY_IMAGE, companyNames.get(i));
                  values.put(KEY_COMPANY_DATE_ADDED, System.currentTimeMillis());
              }catch (Exception x){
              }
              SqlDB.insert(COMPANYS_TABLE, null, values);
           }
       }

//        SqlDB.close(); // Closing database connection
    }*/


   public List<ClientClass> getAllClients() {
        List<ClientClass> clientDetails_ = new ArrayList<ClientClass>();
        String Query = "SELECT * FROM " + CLIENTS_DATA_TABLE +";";
        SqlDB = this.getReadableDatabase();
        Cursor cursor = SqlDB.rawQuery(Query, null);
        if (cursor != null)


                do {
                    ClientClass companyDetails = new ClientClass();
                   // companyDetails.setC_ID(cursor.getString(1));
                    companyDetails.C_FN = (cursor.getString(cursor.getColumnIndex(CLIENT_FN)));
                    companyDetails.C_LN = (cursor.getString(cursor.getColumnIndex(CLIENT_LN)));

                    // adding to todo list
                    clientDetails_.add(companyDetails);
                } while (cursor.moveToNext());



        return clientDetails_;
    }

/*    // Getting single contact
    CompanyDetails getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CLIENTS_DATA_TABLE, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

    // Getting All Contacts
/*    public Cursor getAllContacts() {
        List<Messages> contactList = new ArrayList<Messages>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CLIENTS_DATA_TABLE + " ORDER BY datetimesent ASC;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Messages contact = new Messages();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.set_com_id(cursor.getString(1));
                contact.set_message(cursor.getString(2));
                contact.set_datetime(cursor.getString(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
            //return cursor;
        }
        // return contact list
        return cursor;
    }*/

    // Updating single contact
/*    public int updateMessageViewed(String msgID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE_STATUS, 2);

        // updating row
        return db.update(CLIENTS_DATA_TABLE, values, KEY_ID + " = ? ",
                new String[] { String.valueOf(msgID) });
    }*/

    // Deleting single contact
/*    public void deleteContact(Messages contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CLIENTS_DATA_TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }*/

    // Deleting single contact
    public void deleteMessage(String message_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CLIENTS_DATA_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(message_id) });
        db.close();
    }

    // Deleting single contact
    public void ClearNotificationsMessage(String message_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CLIENTS_DATA_TABLE, KEY_ID + " != ?",
                new String[] { String.valueOf(message_id) });
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + CLIENTS_DATA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting contacts Count
    public Cursor getCompCount() {
        String countQuery = "SELECT * FROM " + CLIENTS_DATA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor;
    }

    // Getting contacts Count
    public Cursor getInteractionData(String clientID) {
        String countQuery = "SELECT * FROM " + INTERACTION_TABLE + " WHERE " + INTERACTION_CLIENT_ID + " = '" + clientID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor;
    }

    // Getting contacts Count
    public Cursor getClientRecord(String clientID) {
        String countQuery = "SELECT * FROM " + CLIENTS_DATA_TABLE + " WHERE " + KEY_ID + " = '" + clientID + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor;
    }


/*    public Cursor getCompUnreadMessages(String com_id) {
        String countQuery = "SELECT " + KEY_MESSAGE_STATUS + " FROM " + CLIENTS_DATA_TABLE + " WHERE com_id ='" + com_id + "' and " + KEY_MESSAGE_STATUS + " = '1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor;
    }*/

}