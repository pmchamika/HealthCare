package com.android.healthcare.IT17152938;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.healthcare.IT17152938.Adapter.DatePickerAddapter;
import com.android.healthcare.IT17152938.bean.Patient;
import com.android.healthcare.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class PatientsEdit extends AppCompatActivity {

    boolean add =true;
    private EditText pe1,pe2,pe3,pe4,pe5,pe6,pe7,pe8,pe9;
    private RadioButton per1,per2;
    private RadioGroup perg;
    private int id=-1;
    private Patient pa;
    private Button peadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_edit);
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
        pe8=findViewById(R.id.pe8);
        pe9=findViewById(R.id.pe9);
        per1=findViewById(R.id.per1);
        per2=findViewById(R.id.per2);
        perg=findViewById(R.id.perg);
        peadd=findViewById(R.id.peadd);
        DatePickerAddapter dpa=new DatePickerAddapter(this,R.id.pe5);
        DatePickerAddapter dpa2=new DatePickerAddapter(this,R.id.pe9);
        peadd.setText("  ADD PATIENT  ");
        if(id!=0){
            add= false;
            pa=Patient.Patientlist.editfullPatient(id);
            pe1.setText(pa.getName());
            pe2.setText(pa.getAge());
            pe3.setText(pa.getDiagnosed());
            pe4.setText(pa.getMedicine());
            pe5.setText(pa.getVisitedate());
            pe6.setText(pa.getBloodgroup());
            pe7.setText(pa.getPhone());
            pe8.setText(pa.getDoctor());
            pe9.setText(pa.getFollowup());
            if("Female".equalsIgnoreCase(pa.getGender())){
                perg.check(R.id.per2);
            }
            peadd.setText("  EDIT PATIENT  ");

        }






    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openPa(View v){
        boolean ch=true;
        if("".equalsIgnoreCase(pe1.getText().toString())){
            Toast.makeText(this, "Patient Name must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe2.getText().toString())){
            Toast.makeText(this, "Patient Age must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe3.getText().toString())){
            Toast.makeText(this, "Diagnosed must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe4.getText().toString())){
            Toast.makeText(this, "Medicine must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe5.getText().toString())){
            Toast.makeText(this, "Visited Date must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe6.getText().toString())){
            Toast.makeText(this, "Blood Group must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe7.getText().toString())){
            Toast.makeText(this, "Phone number must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe8.getText().toString())){
            Toast.makeText(this, "Doctor's Name must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(pe9.getText().toString())){
            Toast.makeText(this, "Follow up date must be Entered", Toast.LENGTH_SHORT).show();
            ch=false;
        }else{
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            String strNow=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate dateNow = LocalDate.parse( strNow , pattern );
            LocalDate dateVisit = LocalDate.parse( pe5.getText().toString() , pattern );
            LocalDate dateFollow = LocalDate.parse( pe9.getText().toString() , pattern );
            if(id!=0){
                if(dateVisit.isBefore(dateNow)){
                    Toast.makeText(this, "Visited Date can't be before today", Toast.LENGTH_SHORT).show();
                    ch=false;
                }else if(dateFollow.isBefore(dateVisit)){
                    Toast.makeText(this, "Follow up date can't be before Visited Date", Toast.LENGTH_SHORT).show();
                    ch=false;
                }
            }else{
                if(dateFollow.isBefore(dateVisit)){
                    Toast.makeText(this, "Follow up date can't be before Visited Date", Toast.LENGTH_SHORT).show();
                    ch=false;
                }
            }

        }



        if(ch) {
            if (add) {

                //add
                pa = new Patient();
                pa.setName(pe1.getText().toString());
                pa.setAge(pe2.getText().toString());
                String gen = ((RadioButton) findViewById(perg.getCheckedRadioButtonId())).getText().toString();
                if ("M".equalsIgnoreCase(gen)) {
                    gen = "Male";
                } else {
                    gen = "Female";
                }
                pa.setGender(gen);
                pa.setDiagnosed(pe3.getText().toString());
                pa.setMedicine(pe4.getText().toString());
                pa.setVisitedate(pe5.getText().toString());
                pa.setBloodgroup(pe6.getText().toString());
                pa.setPhone(pe7.getText().toString());
                pa.setDoctor(pe8.getText().toString());
                pa.setFollowup(pe9.getText().toString());
                pa.setBp("");
                pa.setHeight("");
                pa.setWeight("");
                Patient.Patientlist.addPatient(pa);
                Toast.makeText(this, "New Patient Added Successfully", Toast.LENGTH_SHORT).show();

            } else {
                pa.setName(pe1.getText().toString());
                pa.setAge(pe2.getText().toString());
                String gen = ((RadioButton) findViewById(perg.getCheckedRadioButtonId())).getText().toString();
                if ("M".equalsIgnoreCase(gen)) {
                    gen = "Male";
                } else {
                    gen = "Female";
                }
                pa.setGender(gen);
                pa.setDiagnosed(pe3.getText().toString());
                pa.setMedicine(pe4.getText().toString());
                pa.setVisitedate(pe5.getText().toString());
                pa.setBloodgroup(pe6.getText().toString());
                pa.setPhone(pe7.getText().toString());
                pa.setDoctor(pe8.getText().toString());
                pa.setFollowup(pe9.getText().toString());
                pa.setBp("");
                pa.setHeight("");
                pa.setWeight("");
                Patient.Patientlist.editfullPatient(pa);
                Toast.makeText(this, "Successfully Edited Patient: " + pa.getName(), Toast.LENGTH_SHORT).show();
                //edit

            }


            Intent returnIntent = new Intent(this, PatientsViwe.class);
            setResult(Activity.RESULT_OK, returnIntent);

            finish();
        }
    }



}
