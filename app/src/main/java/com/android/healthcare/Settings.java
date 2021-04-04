package com.android.healthcare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    Button CheckNewVersion;
    Button signOut;
    DBConnector db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        CheckNewVersion = findViewById(R.id.CheckNewVersion);
        signOut = (Button) findViewById(R.id.signOut);

        CheckNewVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/"));
                startActivity(browserIntent);
            }
        });

        db = new DBConnector(this);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent but = new Intent(Settings.this, MainActivity.class);
                int value = 1;
                String id = "0";
                boolean ischeck = db.change(value,id);
                if(ischeck == true) {
                    startActivity(but);
                }
            }
        });
    }
}
