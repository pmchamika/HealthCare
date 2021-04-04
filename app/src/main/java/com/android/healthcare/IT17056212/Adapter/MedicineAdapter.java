package com.android.healthcare.IT17056212.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.healthcare.IT17056212.Holders.MedicineViewHolder;
import com.android.healthcare.IT17056212.MedicineView;
import com.android.healthcare.IT17056212.bean.AdapterCallback;
import com.android.healthcare.IT17056212.bean.Medicine;
import com.android.healthcare.R;

import java.util.List;

public class MedicineAdapter extends BaseAdapter {
    private Context context;
    private List<Medicine> medicineList;
    private AdapterCallback callback;
    public MedicineAdapter(Context context, AdapterCallback callback) {
        this.context = context;
        this.callback = callback;
        this.medicineList = Medicine.Medicinelist.searchMedicine(MedicineView.search);
    }

    @Override
    public int getCount() {
        return medicineList.size();
    }

    @Override
    public Medicine getItem(int i) {
        return medicineList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Medicine medicine = getItem(i);
        MedicineViewHolder holder=null;
        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.activity_medicine_list_view,null);
            holder = new MedicineViewHolder();
            holder.drugName=view.findViewById(R.id.textname);
            holder.expiryDate=view.findViewById(R.id.textdate);
            holder.edit=view.findViewById(R.id.listbutedit);
            holder.delete=view.findViewById(R.id.listbutdel);
            view.setTag(holder);
        }else{
            holder = (MedicineViewHolder)view.getTag();
        }

        holder.drugName.setText(medicine.getDrugName());
        holder.expiryDate.setText(medicine.getExpiryDate());
        holder.edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.edit(medicine.getId());
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Successfully Deleted Medicine Name "+medicine.getDrugName(), Toast.LENGTH_SHORT).show();
                Medicine.Medicinelist.removeMedicine(medicine);
                callback.refreshlist();
            }
        });
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callback.openDetails(medicine.getId());

            }
        });
        return view;
    }

    public void refresh(){
        this.medicineList = Medicine.Medicinelist.searchMedicine(MedicineView.search);
        notifyDataSetChanged();
    }
}
