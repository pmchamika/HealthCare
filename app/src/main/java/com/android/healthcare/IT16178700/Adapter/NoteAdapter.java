package com.android.healthcare.IT16178700.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.healthcare.IT16178700.Holders.NoteViewHolder;
import com.android.healthcare.IT16178700.bean.AdapterCallback;
import com.android.healthcare.IT16178700.bean.Note;
import com.android.healthcare.NoteHome;
import com.android.healthcare.R;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List<Note> noteList;
    private AdapterCallback callback;
    public NoteAdapter(Context context, AdapterCallback callback) {
        this.context = context;
        this.callback = callback;
        this.noteList = Note.NoteList.searchNote(NoteHome.search);
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Note getItem(int i) {
        return noteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Note note = getItem(i);
        NoteViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.activity_note_list_view,null);
            holder = new NoteViewHolder();
            holder.title=view.findViewById(R.id.title);
            holder.edit=view.findViewById(R.id.listbutedit);
            holder.delete=view.findViewById(R.id.listbutdel);
            view.setTag(holder);
        }else{
            holder = (NoteViewHolder)view.getTag();
        }

        holder.title.setText(note.getTitle());
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.edit(note.getId());
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Successfully Deleted Appointment Name "+note.getTitle(), Toast.LENGTH_SHORT).show();
                Note.NoteList.removeNote(note);
                callback.refreshlist();
            }
        });
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.openDetails(note.getId());

            }
        });
        return view;
    }

    public void refresh(){
        this.noteList = Note.NoteList.searchNote(NoteHome.search);
        notifyDataSetChanged();
    }
}
