package com.android.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.healthcare.IT16178700.Adapter.NoteAdapter;
import com.android.healthcare.IT16178700.bean.AdapterCallback;

public class NoteHome extends AppCompatActivity implements AdapterCallback {
    private ListView note_list;
    private NoteAdapter adapter;
    public static String search="";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_home);

        editText=findViewById(R.id.editText);
        editText.setHint("Search");

        note_list = findViewById(R.id.list);
        adapter = new NoteAdapter(NoteHome.this,this);
        note_list.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NoteHome.search=charSequence.toString();
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

    public void openNoEdit(int i) {
        Intent noteEdit = new Intent(NoteHome.this, AddNote.class);
        noteEdit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        noteEdit.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle b = new Bundle();
        b.putInt("id", i); //Your id
        noteEdit.putExtras(b); //Put your id to your next Intent
        startActivityForResult(noteEdit,555);
    }

    public void openNoDetails(int id) {
        Intent note = new Intent(NoteHome.this, NoteDetails.class);
        note.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        note.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Bundle b = new Bundle();

        b.putInt("id", id); //Your id
        note.putExtras(b); //Put your id to your next Intent
        startActivityForResult(note,555);
    }


    public void openAdd(View v) {
        Intent noteAdd = new Intent(this, AddNote.class);
        Bundle b = new Bundle();
        b.putInt("id", 0); //Your id
        noteAdd.putExtras(b); //Put your id to your next Intent
        startActivityForResult(noteAdd,555);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.refresh();
    }

    @Override
    public void edit(int id) {
        openNoEdit(id);
    }


    @Override
    public void refreshlist() {
        listrefrash();
    }

    @Override
    public void openDetails(int id) {
        openNoDetails(id);
    }
}

