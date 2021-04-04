package com.android.healthcare.IT17152938;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.healthcare.IT17152938.Adapter.PatientAdapter;
import com.android.healthcare.IT17152938.bean.AdapterCallback;
import com.android.healthcare.R;


public class PatientsViwe extends AppCompatActivity implements AdapterCallback {


    private ListView pa_list;
    private PatientAdapter adapter;
    public static String search="";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_viwe);

        editText=findViewById(R.id.editText);
        editText.setHint("Search");

        pa_list = findViewById(R.id.list);
        adapter = new PatientAdapter(PatientsViwe.this,this);
        pa_list.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                PatientsViwe.search=charSequence.toString();
                listrefrash();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    public void listrefrash() {
        adapter.refresh();
    }

    public void openPaEdit(int i) {
        Intent patientsedit = new Intent(PatientsViwe.this, PatientsEdit.class);
        patientsedit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        patientsedit.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle b = new Bundle();
        b.putInt("id", i); //Your id
        patientsedit.putExtras(b); //Put your id to your next Intent
        startActivityForResult(patientsedit,555);
    }

    public void openPaDetails(int id) {
        Intent patients = new Intent(PatientsViwe.this, PatientDetailsView.class);
        patients.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        patients.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle b = new Bundle();
        b.putInt("id", id); //Your id
        patients.putExtras(b); //Put your id to your next Intent
        startActivityForResult(patients,555);
        startActivityForResult(patients,555);
    }


    public void openAdd(View v) {
        Intent patientsedit = new Intent(this, PatientsEdit.class);
        Bundle b = new Bundle();
        b.putInt("id", 0); //Your id
        patientsedit.putExtras(b); //Put your id to your next Intent
        startActivityForResult(patientsedit,555);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.refresh();
    }

    @Override
    public void edit(int id) {
        openPaEdit(id);
    }


    @Override
    public void refreshlist() {
        listrefrash();
    }

    @Override
    public void openDetails(int id) {
        openPaDetails(id);
    }
}
