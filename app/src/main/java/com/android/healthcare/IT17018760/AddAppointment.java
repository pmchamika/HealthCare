package com.android.healthcare.IT17018760;

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

import com.android.healthcare.IT17018760.bean.Appointment;
import com.android.healthcare.R;

public class AddAppointment extends AppCompatActivity {

    boolean add =true;
    private Button add_button;
    private EditText patientName,date,time,age,nic,mobileNo,hospital,roomNo;


    private int id=-1;
    private Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }

        add_button = (Button) findViewById(R.id.addAppointment);
        patientName = (EditText) findViewById(R.id.patientName);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        age = (EditText) findViewById(R.id.age);
        nic = (EditText) findViewById(R.id.nic);
        mobileNo = (EditText) findViewById(R.id.mobileNo);
        hospital = (EditText) findViewById(R.id.hospital);
        roomNo = (EditText) findViewById(R.id.roomNo);

        add_button.setText("  ADD Appointment  ");
        if(id!=0){
            add= false;
            appointment=Appointment.AppointmentList.editfullAppointment(id);
            patientName.setText(appointment.getPatientName());
            date.setText(appointment.getDate());
            time.setText(appointment.getTime());
            age.setText(appointment.getAge());
            nic.setText(appointment.getNic());
            mobileNo.setText(appointment.getMobileNo());
            hospital.setText(appointment.getHospital());
            roomNo.setText(appointment.getRoomNo());

            add_button.setText("  EDIT Appointment  ");

        }






    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openAp(View v){
        boolean ch=true;
        if("".equalsIgnoreCase(patientName.getText().toString())){
            Toast.makeText(this, "Please enter patient name ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(time.getText().toString())){
            Toast.makeText(this, "Please enter time ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(age.getText().toString())){
            Toast.makeText(this, "Please enter age ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(nic.getText().toString())){
            Toast.makeText(this, "Please enter NIC ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(mobileNo.getText().toString())){
            Toast.makeText(this, "Please enter mobile number ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(hospital.getText().toString())){
            Toast.makeText(this, "Please enter hospital ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(roomNo.getText().toString())){
            Toast.makeText(this, "Please enter room number ", Toast.LENGTH_SHORT).show();
            ch=false;
        }



        if(ch) {
            if (add) {

                //add
                appointment = new Appointment();
                appointment.setPatientName(patientName.getText().toString());
                appointment.setAge(age.getText().toString());
                appointment.setTime(time.getText().toString());
                appointment.setNic(nic.getText().toString());
                appointment.setMobileNo(mobileNo.getText().toString());
                appointment.setHospital(hospital.getText().toString());
                appointment.setRoomNo(roomNo.getText().toString());
                appointment.setDate(date.getText().toString());
                Appointment.AppointmentList.addAppointment(appointment);
                Toast.makeText(this, "Successfully Added New Appointment", Toast.LENGTH_SHORT).show();

            } else {
                appointment.setPatientName(patientName.getText().toString());
                appointment.setAge(age.getText().toString());
                appointment.setTime(time.getText().toString());
                appointment.setNic(nic.getText().toString());
                appointment.setMobileNo(mobileNo.getText().toString());
                appointment.setHospital(hospital.getText().toString());
                appointment.setRoomNo(roomNo.getText().toString());
                appointment.setDate(date.getText().toString());
                Appointment.AppointmentList.editfullAppointment(appointment);
                Toast.makeText(this, "Edit Successful " + appointment.getPatientName(), Toast.LENGTH_SHORT).show();
                //edit

            }


            Intent returnIntent = new Intent(this, ListViewAppointment.class);
            setResult(Activity.RESULT_OK, returnIntent);

            finish();
        }
    }

}
