package com.android.healthcare.IT17152938.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.healthcare.IT17152938.Holders.PatientViewHolder;
import com.android.healthcare.IT17152938.PatientsViwe;
import com.android.healthcare.IT17152938.bean.AdapterCallback;
import com.android.healthcare.IT17152938.bean.Patient;
import com.android.healthcare.R;

import java.util.List;

public class PatientAdapter extends BaseAdapter {
    private Context context;
    private List<Patient> patientList;
    private AdapterCallback callback;
    public PatientAdapter(Context context,AdapterCallback callback) {
        this.context = context;
        this.callback = callback;
        this.patientList = Patient.Patientlist.searchPatient(PatientsViwe.search);
    }

    @Override
    public int getCount() {
        return patientList.size();
    }

    @Override
    public Patient getItem(int i) {
        return patientList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Patient patient = getItem(i);
        PatientViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.activity_patients_list_view,null);
            holder = new PatientViewHolder();
            holder.name=view.findViewById(R.id.textname);
            holder.date=view.findViewById(R.id.textdate);
            holder.edit=view.findViewById(R.id.listbutedit);
            holder.delete=view.findViewById(R.id.listbutdel);
            view.setTag(holder);
        }else{
            holder = (PatientViewHolder)view.getTag();
        }

        holder.name.setText(patient.getName());
        holder.date.setText(patient.getFollowup());
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.edit(patient.getId());
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Successfully Deleted Patient Name "+patient.getName(),Toast.LENGTH_SHORT).show();
                Patient.Patientlist.removePatient(patient);
                callback.refreshlist();
            }
        });
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.openDetails(patient.getId());

            }
        });
        return view;
    }

    public void refresh(){
        this.patientList = Patient.Patientlist.searchPatient(PatientsViwe.search);
        notifyDataSetChanged();
    }
}
