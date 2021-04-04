package com.android.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    EditText Username,Email,Address,Password,Mobile;


    public TextView textView15;
    DBConnector myDb;




    public void init(){
        textView15=(TextView) findViewById(R.id.sign_in);
        textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent log=new Intent(Profile.this,Login.class);
                startActivity(log);
            }
        });


    }



    public Button button3;

    public void init1(){
        button3=(Button)findViewById(R.id.sign_up);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Username.getText().toString().equals("") || Email.getText().toString().equals("") || Address.getText().toString().equals("") || Mobile.getText().toString().equals("") || Password.getText().toString().equals("")) {
                    Toast.makeText(Profile.this, "Please Enter All Details", Toast.LENGTH_LONG).show();
                }else{

                    boolean isInserted = myDb.insertData(Username.getText().toString(), Email.getText().toString(), Address.getText().toString(), Mobile.getText().toString(), Password.getText().toString());
                    if (isInserted = true) {

                        Toast.makeText(Profile.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent log = new Intent(Profile.this, Login.class);
                        startActivity(log);
                    } else {
                        Toast.makeText(Profile.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }

                }

            }

        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDb=new DBConnector(this);

        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Address = findViewById(R.id.address);
        Mobile = findViewById(R.id.mobile);
        Password = findViewById(R.id.password);
        init();
        init1();
    }
}
