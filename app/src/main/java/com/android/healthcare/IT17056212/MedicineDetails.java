package com.android.healthcare.IT17056212;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.android.healthcare.IT17056212.bean.Medicine;
import com.android.healthcare.R;

public class MedicineDetails extends AppCompatActivity {


    private TextView pvdt1,pvdt2,pvdt3,pvdt4,pvdt5,pvdt6,pvdt7;
    private Button pvfollow,pvsave;
    private int id;
    private Medicine ma;
    private boolean che2=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }

        ma=Medicine.Medicinelist.editfullMedicine(id);

        pvdt1=findViewById(R.id.pvdt1);
        pvdt2=findViewById(R.id.pvdt2);
        pvdt3=findViewById(R.id.pvdt3);
        pvdt4=findViewById(R.id.pvdt4);
        pvdt5=findViewById(R.id.pvdt5);
        pvdt6=findViewById(R.id.pvdt6);
        pvdt7=findViewById(R.id.pvdt7);

//        pvfollow=findViewById(R.id.pvfollow);
//        pvsave=findViewByI
//        d(R.id.pvsave);

      /*  ma=Appointment.Medicinelist.editfullMedicine(id);
        pve1.setText(ma.getBp());
        pve2.setText(ma.getHeight());
        pve3.setText(ma.getWeight());
        pve4.setText(ma.getFollowup());
        DatePickerAddapter dpa=new DatePickerAddapter(this,R.id.pve4);
        pve1.setEnabled(false);
        pve2.setEnabled(false);
        pve3.setEnabled(false);
        pve4.setEnabled(false);*/
//        pve1.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pve2.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pve3.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pve4.setBackgroundColor(getResources().getColor(R.color.notedit));
//        pvsave.setVisibility(View.INVISIBLE);
        pvdt1.setText(ma.getDrugName());
        pvdt2.setText(ma.getCompany());
        pvdt3.setText(ma.getDosage());
        pvdt4.setText(ma.getCondition());
        pvdt5.setText(ma.getExpiryDate());
        pvdt6.setText(ma.getSideEffects());
        pvdt7.setText(ma.getPrice());


    }




}
