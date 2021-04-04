package com.android.healthcare.IT17056212;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.healthcare.IT17056212.Adapter.MedicineAdapter;
import com.android.healthcare.IT17056212.bean.AdapterCallback;
import com.android.healthcare.R;

public class MedicineView extends AppCompatActivity implements AdapterCallback {

    private ListView pa_list;
    private MedicineAdapter adapter;
    public static String search="";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines_view);

        editText=findViewById(R.id.editText);
        editText.setHint("Search");

        pa_list = findViewById(R.id.list);
        adapter = new MedicineAdapter(MedicineView.this,this);
        pa_list.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MedicineView.search=charSequence.toString();
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
        Intent medicineEdit = new Intent(MedicineView.this, MedicineAdd.class);
        medicineEdit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        medicineEdit.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle b = new Bundle();
        b.putInt("id", i); //Your id
        medicineEdit.putExtras(b); //Put your id to your next Intent
        startActivityForResult(medicineEdit,555);
    }

    public void openPaDetails(int id) {
        Intent medicines = new Intent(MedicineView.this, MedicineDetails.class);
        medicines.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        medicines.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle b = new Bundle();
        b.putInt("id", id); //Your id
        medicines.putExtras(b); //Put your id to your next Intent
        startActivityForResult(medicines,555);
    }


    public void openAdd(View v) {
        Intent medicineAdd = new Intent(this, MedicineAdd.class);
        Bundle b = new Bundle();
        b.putInt("id", 0); //Your id
        medicineAdd.putExtras(b); //Put your id to your next Intent
        startActivityForResult(medicineAdd,555);
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

