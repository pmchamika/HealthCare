package com.android.healthcare.IT17018760;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.healthcare.R;

public class AppointmentHome extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_home);

        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd(v);
            }
        });


        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewappointmentList();
            }
        });


        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar();
            }
        });


    }

    public void openAdd(View v) {
        Intent noteAdd = new Intent(this, AddAppointment.class);
        Bundle b = new Bundle();
        b.putInt("id", 0); //Your id
        noteAdd.putExtras(b); //Put your id to your next Intent
        startActivityForResult(noteAdd,555);
    }

    public void viewappointmentList(){
        Intent intent=new Intent(this,ListViewAppointment.class);
        startActivity(intent);
    }
    public void Calendar(){
        Intent intent=new Intent(this,Calendar.class);
        startActivity(intent);
    }

}
