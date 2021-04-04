package com.android.healthcare.IT17018760.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.healthcare.IT17018760.Holders.AppointmentViewHolder;
import com.android.healthcare.IT17018760.ListViewAppointment;
import com.android.healthcare.IT17018760.bean.AdapterCallback;
import com.android.healthcare.IT17018760.bean.Appointment;
import com.android.healthcare.R;

import java.util.List;

public class AppointmentAdapter extends BaseAdapter {

    private Context context;
    private List<Appointment> appointmentList;
    private AdapterCallback callback;
    public AppointmentAdapter(Context context, AdapterCallback callback) {
        this.context = context;
        this.callback = callback;
        this.appointmentList = Appointment.AppointmentList.searchAppointment(ListViewAppointment.search);
    }

    @Override
    public int getCount() {
        return appointmentList.size();
    }

    @Override
    public Appointment getItem(int i) {
        return appointmentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Appointment appointment = getItem(i);
        AppointmentViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.activity_view_appointment_list,null);
            holder = new AppointmentViewHolder();
            holder.patientName=view.findViewById(R.id.patientName);
            holder.edit=view.findViewById(R.id.listbutedit);
            holder.delete=view.findViewById(R.id.listbutdel);
            view.setTag(holder);
        }else{
            holder = (AppointmentViewHolder)view.getTag();
        }
        holder.patientName.setText(appointment.getPatientName());
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.edit(appointment.getId());
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Successfully Deleted Patient Name "+appointment.getPatientName(), Toast.LENGTH_SHORT).show();
                Appointment.AppointmentList.removeAppointment(appointment);
                callback.refreshlist();
            }
        });
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.openDetails(appointment.getId());

            }
        });
        return view;
    }

    public void refresh(){
        this.appointmentList = Appointment.AppointmentList.searchAppointment(ListViewAppointment.search);
        notifyDataSetChanged();

    }
}
