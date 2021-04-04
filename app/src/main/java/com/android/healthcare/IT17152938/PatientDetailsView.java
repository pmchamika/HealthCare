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
import android.widget.TextView;
import android.widget.Toast;

import com.android.healthcare.IT17152938.Adapter.DatePickerAddapter;
import com.android.healthcare.IT17152938.bean.Patient;
import com.android.healthcare.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class PatientDetailsView extends AppCompatActivity {


    private EditText pve1,pve2,pve3,pve4;
    private TextView pvdt1,pvdt2,pvdt3,pvdt4,pvdt5,pvdt6,pvdt7,pvdt8,pvdt9;
    private Button pvfollow,pvsave;
    private int id;
    private Patient pa;
    private boolean che2=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_view);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }


        pve1=findViewById(R.id.pve1);
        pve2=findViewById(R.id.pve2);
        pve3=findViewById(R.id.pve3);
        pve4=findViewById(R.id.pve4);
        pvdt1=findViewById(R.id.pvdt1);
        pvdt2=findViewById(R.id.pvdt2);
        pvdt3=findViewById(R.id.pvdt3);
        pvdt4=findViewById(R.id.pvdt4);
        pvdt5=findViewById(R.id.pvdt5);
        pvdt6=findViewById(R.id.pvdt6);
        pvdt7=findViewById(R.id.pvdt7);
        pvdt8=findViewById(R.id.pvdt8);
        pvdt9=findViewById(R.id.pvdt9);
        pvfollow=findViewById(R.id.pvfollow);
        pvsave=findViewById(R.id.pvsave);

        pa=Patient.Patientlist.editfullPatient(id);
        pve1.setText(pa.getBp());
        pve2.setText(pa.getHeight());
        pve3.setText(pa.getWeight());
        pve4.setText(pa.getFollowup());
        DatePickerAddapter dpa=new DatePickerAddapter(this,R.id.pve4);
        pve1.setEnabled(false);
        pve2.setEnabled(false);
        pve3.setEnabled(false);
        pve4.setEnabled(false);
//        pve1.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pve2.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pve3.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pve4.setBackgroundColor(getResources().getColor(R.color.notedit));
        pvsave.setVisibility(View.INVISIBLE);
        pvdt1.setText(pa.getName());
        pvdt2.setText(pa.getAge());
        pvdt9.setText(pa.getGender());
        pvdt3.setText(pa.getDiagnosed());
        pvdt4.setText(pa.getMedicine());
        pvdt5.setText(pa.getVisitedate());
        pvdt6.setText(pa.getBloodgroup());
        pvdt7.setText(pa.getPhone());
        pvdt8.setText(pa.getDoctor());


    }

    public void createenabale(View v){
        pve1.setEnabled(true);
        pve2.setEnabled(true);
        pve3.setEnabled(true);
        pve4.setEnabled(true);
//        pve1.setBackgroundColor(getResources().getColor(R.color.tx));
//        pve2.setBackgroundColor(getResources().getColor(R.color.tx));
//        pve3.setBackgroundColor(getResources().getColor(R.color.tx));
//        pve4.setBackgroundColor(getResources().getColor(R.color.tx));
        pvsave.setVisibility(View.VISIBLE);
        che2=false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openPa(View v){
        boolean ch =true;
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        String strNow=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dateNow = LocalDate.parse( strNow , pattern );
        LocalDate dateFollow;

        if(!che2){
            if("".equalsIgnoreCase(pve1.getText().toString())){
                Toast.makeText(this, "Patient BP must be Entered", Toast.LENGTH_SHORT).show();
                ch=false;
            }else if("".equalsIgnoreCase(pve2.getText().toString())){
                Toast.makeText(this, "Patient Height must be Entered", Toast.LENGTH_SHORT).show();
                ch=false;
            }else if("".equalsIgnoreCase(pve3.getText().toString())){
                Toast.makeText(this, "Patient Weight must be Entered", Toast.LENGTH_SHORT).show();
                ch=false;
            }
            else if("".equalsIgnoreCase(pve4.getText().toString())){
                Toast.makeText(this, "Patient Follow up date must be Entered", Toast.LENGTH_SHORT).show();
                ch=false;
            }else{
                dateFollow = LocalDate.parse( pve4.getText().toString() , pattern );
                if(dateFollow.isBefore(dateNow)){
                    Toast.makeText(this, "Follow up date can't be before today", Toast.LENGTH_SHORT).show();
                    ch=false;
                }
            }
        }

        if(ch) {
            pa.setBp(pve1.getText().toString());
            pa.setHeight(pve2.getText().toString());
            pa.setWeight(pve3.getText().toString());
            pa.setFollowup(pve4.getText().toString());
            pa.setVisitedate(strNow);
            Patient.Patientlist.editfullPatient(pa);
            Toast.makeText(this, "Successfully Updated Patient Name " + pa.getName(), Toast.LENGTH_SHORT).show();
            Intent patients = new Intent(PatientDetailsView.this, PatientsViwe.class);
            setResult(Activity.RESULT_OK, patients);
            finish();
        }
    }
}
