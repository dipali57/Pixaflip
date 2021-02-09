package com.example.pixaflip.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pixaflip.Data.MyFav;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
                + " TEXT, " + Params.KEY_URL + " TEXT" + ")";
        Log.d("dbPixa", "Query being run is : "+ create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isExist(String name){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            MyFav contact = new MyFav();
            System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            if(cursor.getString(cursor.getColumnIndex("name")).equals(name)){
                x = true;
                break;}
        }
        return x;
    }

    public void addFavourite(MyFav pdfs){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, pdfs.getName());
        values.put(Params.KEY_URL, pdfs.getUrl());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("dbPixa", "Successfully inserted");
        db.close();
    }
    public void deleteById(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_NAME +"=?", new String[]{String.valueOf(name)});
        db.close();
    }
    public List<MyFav> getAll(){
        List<MyFav> pdfList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                MyFav pdfs = new MyFav();
                pdfs.setId(Integer.parseInt(cursor.getString(0)));
                pdfs.setName(cursor.getString(1));
                pdfs.setUrl(cursor.getString(2));
                pdfList.add(pdfs);
            }while(cursor.moveToNext());
        }
        return pdfList;
    }
}
