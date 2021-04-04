package com.android.healthcare.IT17018760;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.healthcare.R;

public class Calendar extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        button=(Button)findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd(v);
            }
        });



    }  public void openAdd(View v) {
        Intent noteAdd = new Intent(this, AddAppointment.class);
        Bundle b = new Bundle();
        b.putInt("id", 0); //Your id
        noteAdd.putExtras(b); //Put your id to your next Intent
        startActivityForResult(noteAdd,555);
    }
}
