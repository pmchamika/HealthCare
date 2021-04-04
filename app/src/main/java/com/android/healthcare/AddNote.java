package com.android.healthcare;

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

import com.android.healthcare.IT16178700.bean.Note;

public class AddNote extends AppCompatActivity {

    boolean add =true;
    private Button add_button;
    private EditText titleT;
    private EditText descriptionD;


    private int id=-1;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }

        add_button = (Button) findViewById(R.id.add_note);
        titleT = (EditText) findViewById(R.id.title);
        descriptionD = (EditText) findViewById(R.id.description);

        add_button.setText("  ADD Appointment  ");
        if(id!=0){
            add= false;
            note=Note.NoteList.editfullNote(id);
            titleT.setText(note.getTitle());
            descriptionD.setText(note.getDescription());

            add_button.setText("  EDIT Appointment  ");

        }






    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openNo(View v){
        boolean ch=true;
        if("".equalsIgnoreCase(titleT.getText().toString())){
            Toast.makeText(this, "Please enter note title ", Toast.LENGTH_SHORT).show();
            ch=false;
        }else if("".equalsIgnoreCase(descriptionD.getText().toString())){
            Toast.makeText(this, "Please enter note description ", Toast.LENGTH_SHORT).show();
            ch=false;
        }



        if(ch) {
            if (add) {

                //add
                note = new Note();
                note.setTitle(titleT.getText().toString());
                note.setDescription(descriptionD.getText().toString());
                Note.NoteList.addNote(note);
                Toast.makeText(this, "Successfully Added New Appointment", Toast.LENGTH_SHORT).show();

            } else {
                note.setTitle(titleT.getText().toString());
                note.setDescription(descriptionD.getText().toString());

                Note.NoteList.editfullNote(note);
                Toast.makeText(this, "Edit Successful " + note.getTitle(), Toast.LENGTH_SHORT).show();
                //edit

            }


            Intent returnIntent = new Intent(this, NoteHome.class);
            setResult(Activity.RESULT_OK, returnIntent);

            finish();
        }
    }

}
