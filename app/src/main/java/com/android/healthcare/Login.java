package com.android.healthcare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText Username,Password;
    public TextView textView;
    DBConnector myDb;

    public void init(){
        textView=(TextView) findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log=new Intent(Login.this,Profile.class);
                startActivity(log);
            }
        });


    }



    public Button button;

    public void init1(){
        button=(Button)findViewById(R.id.sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Username.getText().toString().equals("") || Password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Please Enter All Details", Toast.LENGTH_LONG).show();
                }else {
                    Boolean chk = myDb.Login(Username.getText().toString(), Password.getText().toString());
                    if (chk == true) {
                        MainActivity.val = 1;
                        Toast.makeText(Login.this, "Welcome to Micro Electronics", Toast.LENGTH_LONG).show();
                        Intent log = new Intent(Login.this, MainActivity.class);
                        startActivity(log);

                    } else
                        Toast.makeText(Login.this, "Enter Correct Username and Password", Toast.LENGTH_LONG).show();


                }

            }
        });


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myDb=new DBConnector(this);

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);

        init();
        init1();
    }


    public void browser1(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
        startActivity(browserIntent);
    }



    public void browser2(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
        startActivity(browserIntent);
    }

    public void browser3(View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
        startActivity(browserIntent);
    }
}
