package com.android.healthcare.IT17018760;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.healthcare.IT17018760.bean.Appointment;
import com.android.healthcare.R;

public class ViewAppointments extends AppCompatActivity {

    private TextView patientName,date,time,age,nic,mobileNo,hospital,roomNo;
    private Appointment appointment;
    private int id;
    private boolean che2=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }

        appointment=Appointment.AppointmentList.editfullAppointment(id);

        patientName = findViewById(R.id.patientName);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        age = findViewById(R.id.age);
        nic = findViewById(R.id.nic);
        mobileNo = findViewById(R.id.mobileNo);
        hospital = findViewById(R.id.hospital);
        roomNo = findViewById(R.id.roomNo);

        patientName.setText(appointment.getPatientName());
        date.setText(appointment.getDate());
        time.setText(appointment.getTime());
        age.setText(appointment.getAge());
        nic.setText(appointment.getNic());
        mobileNo.setText(appointment.getMobileNo());
        hospital.setText(appointment.getHospital());
        roomNo.setText(appointment.getRoomNo());

    }
}
