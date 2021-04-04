package com.android.healthcare.IT17152938.bean;

import com.android.healthcare.IT17152938.PatientsViwe;
import com.android.healthcare.MainActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient {
    private int id;
    private String name,age,gender,diagnosed,medicine,visitedate,bloodgroup,phone,doctor,followup,bp,height,weight;
    public static final Patient Patientlist=new Patient();
    private static int inc=1;

    private ArrayList<Patient> display;
    private ArrayList<Patient> full=new ArrayList<Patient>();



    public ArrayList<Patient> getFull() {
        return full;
    }

    public void setFull(ArrayList<Patient> full) {
        this.full = full;
    }

    public ArrayList addPatient(Patient p){
        int idd=1;
        if(!full.isEmpty()){
            idd=full.get(full.size()-1).getId();
            idd++;
        }
        p.id=idd;
        full.add(p);
        MainActivity.dbhadeler.addPatient(full);
        return searchPatient("");

    }

    public void fristaddPatient(Patient p){
        full.add(p);

    }

    public void removePatient(Patient p){
        full.remove(p);
        display=full;
        MainActivity.dbhadeler.addPatient(full);
        PatientsViwe.search="";
    }

    public ArrayList searchPatient(String name){

        if ("".equalsIgnoreCase(name)) {
            display=full;
            return display;
        }
        else{
            display=new ArrayList<Patient>();
            Pattern pattern = Pattern.compile(name.toLowerCase());

            for (Patient p:full) {
                Matcher matcher = pattern.matcher(p.name.toLowerCase());
                if(matcher.find()){
                    display.add(p);
                }
            }

            return display;
        }



    }

    public Patient editfullPatient(int id){

        for (Patient pa:full) {
            if(pa.id==id){
                return pa;
//                break;
            }

        }
        MainActivity.dbhadeler.addPatient(full);
        return null;
    }



    public void editfullPatient(Patient p){
        int i=p.id;
        for (Patient pa:full) {
            if(pa.id==p.id){
                pa=p;
                break;
            }
        }
        MainActivity.dbhadeler.addPatient(full);
//        PatientsViwe.search="";



    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setVisitedate(String visitedate) {
        this.visitedate = visitedate;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }





    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDiagnosed() {
        return diagnosed;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getVisitedate() {
        return visitedate;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getPhone() {
        return phone;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getFollowup() {
        return followup;
    }

    public String getBp() {
        return bp;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

//    public Patient getPatient(int dispos){
//        full
//    }
}
