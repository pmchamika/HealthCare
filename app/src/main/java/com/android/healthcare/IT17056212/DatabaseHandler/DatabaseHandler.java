package com.android.healthcare.IT17056212.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.healthcare.IT17056212.bean.Medicine;
import com.android.healthcare.IT17152938.bean.Patient;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;

    private static final String DATABASE_NAME = "HealthCare";

    private static final String TABLE_MEDICINE = "medicine";
    private static final String TABLE_PATIENTS = "patients";

    private static final String KEY_ID = "id";
    private static final String KEY_DRUG = "drug";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_DOSAGE = "dose";
    private static final String KEY_CODITION = "cond";
    private static final String KEY_EXPIRY = "exp";
    private static final String KEY_SIDEEFFECTS = "side";
    private static final String KEY_PRICE = "price";
    private static final String p_id="id",p_name="name",p_age="age",p_gender="gender",p_diagnosed="diagnosed",p_medicine="medicine",p_visitedate="visitedate",p_bloodgroup="bloodgroup",p_phone="phone",p_doctor="doctor",p_followup="followup",p_bp="bp",p_height="height",p_weight="weight";

    private HashMap hp;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_MEDICINE +
                "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DRUG + " TEXT, "
                + KEY_COMPANY + " TEXT, "
                + KEY_DOSAGE + " TEXT, "
                + KEY_CODITION + " TEXT,"
                + KEY_EXPIRY + " TEXT,"
                + KEY_SIDEEFFECTS + " TEXT,"
                + KEY_PRICE + " TEXT)";


        String p_sql = "CREATE TABLE " + TABLE_PATIENTS +
                "(" + p_id + " INTEGER PRIMARY KEY,"
                + p_name + " TEXT, "
                + p_age + " TEXT, "
                + p_gender + " TEXT, "
                + p_diagnosed + " TEXT,"
                + p_medicine + " TEXT,"
                + p_visitedate + " TEXT,"
                + p_bloodgroup + " TEXT, "
                + p_phone + " TEXT, "
                + p_doctor + " TEXT, "
                + p_followup + " TEXT,"
                + p_bp + " TEXT,"
                + p_height + " TEXT,"
                + p_weight + " TEXT)";
        // db.execSQL(sql);
        db.execSQL(p_sql);
        db.execSQL(sql);


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE );
        //db.execSQL("DROP TABLE IF EXISTS medicine");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS );
        this.onCreate(db);
    }

    public void addMed(ArrayList<Medicine> med) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE );
        String sql = "CREATE TABLE " + TABLE_MEDICINE +
                "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DRUG + " TEXT, "
                + KEY_COMPANY + " TEXT, "
                + KEY_DOSAGE + " TEXT, "
                + KEY_CODITION + " TEXT,"
                + KEY_EXPIRY + " TEXT,"
                + KEY_SIDEEFFECTS + " TEXT,"
                + KEY_PRICE + " TEXT)";
        db2.execSQL(sql);
        db2.close();

        for (Medicine ma:med) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID, ma.getId());
            values.put(KEY_DRUG, ma.getDrugName());
            values.put(KEY_COMPANY, ma.getCompany());
            values.put(KEY_DOSAGE, ma.getDosage());
            values.put(KEY_CODITION, ma.getCondition());
            values.put(KEY_EXPIRY, ma.getExpiryDate());
            values.put(KEY_SIDEEFFECTS, ma.getSideEffects());
            values.put(KEY_PRICE, ma.getPrice());
            db.insert(TABLE_MEDICINE, null, values);
            db.close();
        }
    }


    public void getMedicinedetails(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor x = sqLiteDatabase.rawQuery("select * from "+TABLE_MEDICINE, null);
        while (x.moveToNext()){
            Medicine ma=new Medicine();
            ma.setId(x.getInt(x.getColumnIndex(KEY_ID)));
            ma.setDrugName(x.getString(x.getColumnIndex(KEY_DRUG)));
            ma.setCompany(x.getString(x.getColumnIndex(KEY_COMPANY)));
            ma.setDosage(x.getString(x.getColumnIndex(KEY_DOSAGE)));
            ma.setCondition(x.getString(x.getColumnIndex(KEY_CODITION)));
            ma.setExpiryDate(x.getString(x.getColumnIndex(KEY_EXPIRY)));
            ma.setSideEffects(x.getString(x.getColumnIndex(KEY_SIDEEFFECTS)));
            ma.setPrice(x.getString(x.getColumnIndex(KEY_PRICE)));


            Medicine.Medicinelist.firstaddMedicine(ma);

        }
    }

    public void addPatient(ArrayList<Patient> pat) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        db2.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS );
        String p_sql = "CREATE TABLE " + TABLE_PATIENTS +
                "(" + p_id + " INTEGER PRIMARY KEY,"
                + p_name + " TEXT, "
                + p_age + " TEXT, "
                + p_gender + " TEXT, "
                + p_diagnosed + " TEXT,"
                + p_medicine + " TEXT,"
                + p_visitedate + " TEXT,"
                + p_bloodgroup + " TEXT, "
                + p_phone + " TEXT, "
                + p_doctor + " TEXT, "
                + p_followup + " TEXT,"
                + p_bp + " TEXT,"
                + p_height + " TEXT,"
                + p_weight + " TEXT)";
        db2.execSQL(p_sql);
        db2.close();

        for (Patient pa:pat) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(p_id, pa.getId());
            values.put(p_name, pa.getName());
            values.put(p_age, pa.getAge());
            values.put(p_gender,pa.getGender());
            values.put(p_diagnosed, pa.getDiagnosed());
            values.put(p_medicine, pa.getMedicine());
            values.put(p_visitedate, pa.getVisitedate());
            values.put(p_bloodgroup, pa.getBloodgroup());
            values.put(p_phone, pa.getPhone());
            values.put(p_doctor, pa.getDoctor());
            values.put(p_followup,pa.getFollowup());
            values.put(p_bp, pa.getBp());
            values.put(p_height, pa.getHeight());
            values.put(p_weight, pa.getWeight());
            db.insert(TABLE_PATIENTS, null, values);
            db.close();
        }

    }

    public void getPatiantdetails(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor x = sqLiteDatabase.rawQuery("select * from "+TABLE_PATIENTS, null);
        while (x.moveToNext()){
            Patient pa=new Patient();
            pa.setId(x.getInt(x.getColumnIndex(p_id)));
            pa.setName(x.getString(x.getColumnIndex(p_name)));
            pa.setAge(x.getString(x.getColumnIndex(p_age)));
            pa.setGender(x.getString(x.getColumnIndex(p_gender)));
            pa.setDiagnosed(x.getString(x.getColumnIndex(p_diagnosed)));
            pa.setMedicine(x.getString(x.getColumnIndex(p_medicine)));
            pa.setVisitedate(x.getString(x.getColumnIndex(p_visitedate)));
            pa.setBloodgroup(x.getString(x.getColumnIndex(p_bloodgroup)));
            pa.setPhone(x.getString(x.getColumnIndex(p_phone)));
            pa.setDoctor(x.getString(x.getColumnIndex(p_doctor)));
            pa.setFollowup(x.getString(x.getColumnIndex(p_followup)));
            pa.setBp(x.getString(x.getColumnIndex(p_bp)));
            pa.setHeight(x.getString(x.getColumnIndex(p_height)));
            pa.setWeight(x.getString(x.getColumnIndex(p_weight)));
            Patient.Patientlist.fristaddPatient(pa);

        }
    }


}