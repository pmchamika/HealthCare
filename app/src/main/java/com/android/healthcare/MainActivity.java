package com.android.healthcare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.healthcare.IT16178700.bean.Note;
import com.android.healthcare.IT17018760.AppointmentHome;
import com.android.healthcare.IT17018760.bean.Appointment;
import com.android.healthcare.IT17056212.DatabaseHandler.DatabaseHandler;
import com.android.healthcare.IT17056212.MedicineView;
import com.android.healthcare.IT17056212.bean.Medicine;
import com.android.healthcare.IT17152938.PatientsViwe;
import com.android.healthcare.IT17152938.bean.Patient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imagePatient;
    ImageView imageView5;
    public static int val = 0;
    public static DatabaseHandler dbhadeler;

    DBConnector mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mydb = new DBConnector(this);
        dbhadeler=new DatabaseHandler(this);
        if(Patient.Patientlist.getFull().isEmpty()){
            MainActivity.dbhadeler.getPatiantdetails();
        }
        if(Medicine.Medicinelist.getFull().isEmpty()){
            MainActivity.dbhadeler.getMedicinedetails();
        }





        imagePatient=findViewById(R.id.imageView2);
        imageView5=findViewById(R.id.imageView5);

        if(Note.NoteList.getFull().size()==0){
            Note note=new Note();

            note.setTitle("Submit Lab 2");
            note.setDescription("Share the code with github");


            Note note1=new Note();

            note1.setTitle("Submit IWT Assignment 2");
            note1.setDescription("Want handle db php and validate data with javascript");

            Note.NoteList.addNote(note);
            Note.NoteList.addNote(note1);
        }

        if(Appointment.AppointmentList.getFull().size()==0){
            Appointment ap=new Appointment();

            ap.setPatientName("Pulasthi Shamil");
            ap.setAge("23");
            ap.setTime("6.00PM");
            ap.setNic("973573586V");
            ap.setMobileNo("0767488285");
            ap.setHospital("The Kings Hospital");
            ap.setRoomNo("309");
            ap.setDate("2019/09/05");

            Appointment ap1=new Appointment();

            ap1.setPatientName("Kaushalya Rajapakshe");
            ap1.setAge("24");
            ap1.setTime("4.00PM");
            ap1.setNic("953573886V");
            ap1.setMobileNo("0777488285");
            ap1.setHospital("Lesson Hospital");
            ap1.setRoomNo("279");
            ap1.setDate("2019/10/05");

            Appointment.AppointmentList.addAppointment(ap);
            Appointment.AppointmentList.addAppointment(ap1);
        }

    }

    public void openPa(View v){
        Intent patients=new Intent(MainActivity.this, PatientsViwe.class);
        startActivity(patients);

    }

    public void openMa(View v){
        Intent medicines=new Intent(MainActivity.this, MedicineView.class);
        startActivity(medicines);
    }

    public void openNote(View v){
        Intent note=new Intent(MainActivity.this, NoteHome.class);
        startActivity(note);
    }

    public void openAppointment(View v){
        Intent note=new Intent(MainActivity.this, AppointmentHome.class);
        startActivity(note);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (id == R.id.nav_account) {
            Cursor res = mydb.check();
            if(res.getCount() == 0) {
                startActivity(new Intent(this, Login.class));
            }
            if(res.getCount() == 1) {
                ///startActivity(new Intent(this, Myaccount.class));
                startActivity(new Intent(this, UserProfile.class));
            }else{
                startActivity(new Intent(this, Login.class));
            }
            return true;
        } else if (id == R.id.nav_medicine) {
            startActivity(new Intent(this, MedicineView.class));
        } else if (id == R.id.nav_patient) {
            startActivity(new Intent(this, PatientsViwe.class));
        } else if (id == R.id.nav_note) {
            startActivity(new Intent(this, NoteHome.class));
            return true;
        } else if (id == R.id.nav_appointment) {
            startActivity(new Intent(this, AppointmentHome.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

