package com.android.healthcare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.healthcare.IT16178700.bean.Note;

public class NoteDetails extends AppCompatActivity {

    private TextView title,description;
    private int id;
    private Note note;
    private boolean che2=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);
        Bundle b = getIntent().getExtras();


        if(b != null){
            id = b.getInt("id");
        }

        note=Note.NoteList.editfullNote(id);

        title=findViewById(R.id.title);
        description=findViewById(R.id.description);

        title.setText(note.getTitle());
        description.setText(note.getDescription());

    }


}
