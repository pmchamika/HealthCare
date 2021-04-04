package com.android.healthcare.IT17056212;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.healthcare.IT17056212.bean.Medicine;
import com.android.healthcare.IT17152938.Adapter.DatePickerAddapter;
import com.android.healthcare.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class MedicineAdd extends AppCompatActivity {

    boolean add =true;
    private EditText pe1,pe2,pe3,pe4,pe5,pe6,pe7;


    private int id=-1;
    private Medicine ma,me;
    private Button peadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }

        pe1=findViewById(R.id.pe1);
        pe2=findViewById(R.id.pe2);
        pe3=findViewById(R.id.pe3);
        pe4=findViewById(R.id.pe4);
        pe5=findViewById(R.id.pe5);
        pe6=findViewById(R.id.pe6);
        pe7=findViewById(R.id.pe7);

        peadd=findViewById(R.id.peadd);
        DatePickerAddapter dpa=new DatePickerAddapter(this,R.id.pe5);

        peadd.setText("  ADD Medicine  ");
        if(id!=0){
            add= false;
            ma=Medicine.Medicinelist.editfullMedicine(id);
            pe1.setText(ma.getDrugName());
            pe2.setText(ma.getCompany());
            pe3.setText(ma.getDosage());
            pe4.setText(ma.getCondition());
            pe5.setText(ma.getExpiryDate());
            pe6.setText(ma.getSideEffects());
            pe7.setText(ma.getPrice());


            peadd.setText("  EDIT Medicine  ");

        }






    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openPa(View v){
        boolean ch=true;
        if("".equalsIgnoreCase(pe1.getText().toString())){
            Toast.makeText(this, "Medicine Name must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe2.getText().toString())){
            Toast.makeText(this, "Company must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe3.getText().toString())){
            Toast.makeText(this, "Dosage must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe4.getText().toString())){
            Toast.makeText(this, "Medical Condition must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe5.getText().toString())){
            Toast.makeText(this, "Expiry Date must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe6.getText().toString())){
            Toast.makeText(this, "Side Effects must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe7.getText().toString())){
            Toast.makeText(this, "Price must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else{
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            String strNow=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate dateNow = LocalDate.parse( strNow , pattern );
            LocalDate dateExpiry = LocalDate.parse( pe5.getText().toString() , pattern );

            if(id!=0){
                if(dateExpiry.isBefore(dateNow)){
                    Toast.makeText(this, "Expiry Date can't be before today", Toast.LENGTH_SHORT).show();
                    ch=false;
                }
            }

        }



        if(ch) {
            if (add) {

                //add
                ma = new Medicine();
                ma.setDrugName(pe1.getText().toString());
                ma.setCompany(pe2.getText().toString());
                ma.setDosage(pe3.getText().toString());
                ma.setCondition(pe4.getText().toString());
                ma.setExpiryDate(pe5.getText().toString());
                ma.setSideEffects(pe6.getText().toString());
                ma.setPrice(pe7.getText().toString());


                Medicine.Medicinelist.addMedicine(ma);

                Toast.makeText(this, "New Medicine Added Successfully", Toast.LENGTH_SHORT).show();

            } else {
                ma.setDrugName(pe1.getText().toString());
                ma.setCompany(pe2.getText().toString());
                ma.setDosage(pe3.getText().toString());
                ma.setCondition(pe4.getText().toString());
                ma.setExpiryDate(pe5.getText().toString());
                ma.setSideEffects(pe6.getText().toString());
                ma.setPrice(pe7.getText().toString());

                Medicine.Medicinelist.editfullMedicine(ma);
                Toast.makeText(this, "Successfully Edited Medicine: " + ma.getDrugName(), Toast.LENGTH_SHORT).show();
                //edit

            }


            Intent returnIntent = new Intent(this, MedicineView.class);
            setResult(Activity.RESULT_OK, returnIntent);

            finish();
        }
    }



}
