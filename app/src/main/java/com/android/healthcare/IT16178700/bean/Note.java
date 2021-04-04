package com.android.healthcare.IT16178700.bean;

import com.android.healthcare.NoteHome;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note {
    private int id;
    private String title,description;
    public static final Note NoteList=new Note();
    private static int inc=1;

    private ArrayList<Note> display;
    private ArrayList<Note> full=new ArrayList<Note>();



    public ArrayList<Note> getFull() {
        return full;
    }

    public void setFull(ArrayList<Note> full) {
        this.full = full;
    }

    public ArrayList addNote(Note n){
        n.id=inc;
        inc++;
        full.add(n);
        return searchNote("");

    }

    public void removeNote(Note m){
        full.remove(m);
        display=full;
        NoteHome.search="";
    }

    public ArrayList searchNote(String title){

        if ("".equalsIgnoreCase(title)) {
            display=full;
            return display;
        }
        else{
            display=new ArrayList<Note>();
            Pattern pattern = Pattern.compile(title.toLowerCase());

            for (Note n:full) {
                Matcher matcher = pattern.matcher(n.title.toLowerCase());
                if(matcher.find()){
                    display.add(n);
                }
            }

            return display;
        }



    }

    public Note editfullNote(int id){

        for (Note note:full) {
            if(note.id==id){
                return note;
//                break;
            }

        }
        return null;
    }



    public void editfullNote(Note n){
        int i=n.id;
        for (Note note:full) {
            if(note.id==n.id){
                note=n;
                break;
            }
        }



    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
