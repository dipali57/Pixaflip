package com.example.pixaflip.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "user";
    private static final String KEY_INO = "id";
    private static final String KEY_FROM = "fromact1";
    private static final String KEY_TO = "toact1";
    private static final String KEY_TIMESTAMP = "createat1";


    private static final String create = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_INO + " INTEGER PRIMARY KEY NOT NULL,"
            + KEY_FROM + " TEXT, "
            + KEY_TO + " TEXT, "
            + KEY_TIMESTAMP + " TEXT" + ")";

    public UserDatabase(Context context) {
        super(context,"DbUser", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("dbPixa", "Query being run is : "+ create);

        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void adduseract(methods m){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FROM, m.getFrom());
        values.put(KEY_TO, m.getTo());
        values.put(KEY_TIMESTAMP,m.getTimestamp());

        db.insert(TABLE_NAME, null, values);
        Log.d("dbh", "Successfully inserted");
        db.close();
    }

public void Adduser(String s1, String s2, String s3) {

    // on below line we are creating a variable for
    // our sqlite database and calling writable method
    // as we are writing data in our database.
    SQLiteDatabase db = this.getWritableDatabase();

    // on below line we are creating a
    // variable for content values.
    ContentValues values = new ContentValues();

    // on below line we are passing all values
    // along with its key and value pair.
    values.put(KEY_FROM, s1);
    values.put(KEY_TO, s2);
    values.put(KEY_TIMESTAMP, s3);


    // after adding all values we are passing
    // content values to our table.
    db.insert(TABLE_NAME, null, values);
    Log.d("dbh", "Successfully inserted");
    // at last we are closing our
    // database after adding database.
    db.close();
}

}
