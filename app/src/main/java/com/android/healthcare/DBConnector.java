package com.android.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class DBConnector extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MicroElectronics.db";

    public static final String TABL_NAME="Registration";
    public static final String CO_1="Username";
    public static final String CO_2="Email";
    public static final String CO_3="Address";
    public static final String CO_4="Mobile";
    public static final String CO_5="Password";
    public static final String CO_6="Value";

    public static final String TABLE_NAME3 = "Address_table";
    public static final String C_1="ADDID";
    public static final String C_2="FName";
    public static final String C_3="LName";
    public static final String C_4="Address";
    public static final String C_5="Zip";
    public static final String C_6="ContactNo";

    public DBConnector(Context context)  {

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "Updating table from " + oldVersion + " to " + newVersion);
        /*db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        onCreate(db);*/
    }



    public boolean insertData(String Username,String Email,String Address,String Mobile,String Password){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CO_1,Username);
        contentValues.put(CO_2,Email);
        contentValues.put(CO_3,Address);
        contentValues.put(CO_4,Mobile);
        contentValues.put(CO_5,Password);
        contentValues.put(CO_6,1);
        long result=db.insert("Registration",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }

    public Boolean change(int value,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CO_6,id);
        db.update(TABL_NAME,contentValues,"Value= 1",null);
        return true;
    }


    public Boolean Login(String Username,String Password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select Username,Password from Registration where Username=? and Password=?",new String[]{Username,Password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("Select*from Registration",null);
        return data;
    }

    public boolean updateData(String Username,String Email,String Address,String Mobile,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CO_1,Username);
        contentValues.put(CO_2,Email);
        contentValues.put(CO_3,Address);
        contentValues.put(CO_4,Mobile);
        contentValues.put(CO_5,Password);
        db.update(TABL_NAME,contentValues,"Username=?",new String[] {Username});
        return true;

    }


    public Integer deleteData(String Username){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABL_NAME,"Username=?",new String[]{Username});
    }


    public Cursor check(){
        SQLiteDatabase db  = this.getWritableDatabase();
        //Select Username, Email, Address, Mobile, Password
        Cursor res = db.rawQuery("Select Value from Registration WHERE Value = 1", null);
        return res;
    }



}
