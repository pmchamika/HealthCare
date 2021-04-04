package com.android.healthcare.IT17056212.bean;

import com.android.healthcare.MainActivity;
import com.android.healthcare.IT17152938.PatientsViwe;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Medicine {
    private int id;
    private String drugName,company,condition,expiryDate,dosage,sideEffects,price;
    public static final Medicine Medicinelist=new Medicine();
    private static int inc=1;

    private ArrayList<Medicine> display;
    private ArrayList<Medicine> full=new ArrayList<Medicine>();



    public ArrayList<Medicine> getFull() {
        return full;
    }

    public void setFull(ArrayList<Medicine> full) {
        this.full = full;
    }

    public ArrayList addMedicine(Medicine m){
        int idd=1;
        if(!full.isEmpty()){
            idd=full.get(full.size()-1).getId();
            idd++;
        }
        m.id=idd;
        full.add(m);
        MainActivity.dbhadeler.addMed(full);
        return searchMedicine("");

    }
    public void firstaddMedicine(Medicine m){
        full.add(m);

    }

    public void removeMedicine(Medicine m){
        full.remove(m);
        display=full;
        MainActivity.dbhadeler.addMed(full);
        PatientsViwe.search="";
    }

    public ArrayList searchMedicine(String name){

        if ("".equalsIgnoreCase(name)) {
            display=full;
            return display;
        }
        else{
            display=new ArrayList<Medicine>();
            Pattern pattern = Pattern.compile(name.toLowerCase());

            for (Medicine m:full) {
                Matcher matcher = pattern.matcher(m.drugName.toLowerCase());
                if(matcher.find()){
                    display.add(m);
                }
            }

            return display;
        }



    }

    public Medicine editfullMedicine(int id){

        for (Medicine ma:full) {
            if(ma.id==id){
                return ma;
//                break;
            }

        }
        MainActivity.dbhadeler.addMed(full);
        return null;
    }



    public void editfullMedicine(Medicine m){
        int i=m.id;
        for (Medicine ma:full) {
            if(ma.id==m.id){
                ma=m;
                break;
            }
        }
        MainActivity.dbhadeler.addMed(full);
//        PatientsViwe.search="";



    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;

    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
