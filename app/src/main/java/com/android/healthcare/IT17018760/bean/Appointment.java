package com.android.healthcare.IT17018760.bean;

import com.android.healthcare.IT17018760.ListViewAppointment;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Appointment {
    private int id;

    private String patientName,date,time,age,nic,mobileNo,hospital,roomNo;
    public static final Appointment AppointmentList=new Appointment();
    private static int inc=1;

    private ArrayList<Appointment> display;
    private ArrayList<Appointment> full=new ArrayList<Appointment>();



    public ArrayList<Appointment> getFull() {
        return full;
    }

    public void setFull(ArrayList<Appointment> full) {
        this.full = full;
    }

    public ArrayList addAppointment(Appointment n){
        n.id=inc;
        inc++;
        full.add(n);
        return searchAppointment("");

    }

    public void removeAppointment(Appointment m){
        full.remove(m);
        display=full;
        ListViewAppointment.search="";
    }

    public ArrayList searchAppointment(String title){

        if ("".equalsIgnoreCase(title)) {
            display=full;
            return display;
        }
        else{
            display=new ArrayList<Appointment>();
            Pattern pattern = Pattern.compile(title.toLowerCase());

            for (Appointment n:full) {
                Matcher matcher = pattern.matcher(n.patientName.toLowerCase());
                if(matcher.find()){
                    display.add(n);
                }
            }

            return display;
        }



    }

    public Appointment editfullAppointment(int id){

        for (Appointment note:full) {
            if(note.id==id){
                return note;
//                break;
            }

        }
        return null;
    }



    public void editfullAppointment(Appointment n){
        int i=n.id;
        for (Appointment note:full) {
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

    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientName() { return patientName; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getNic() { return nic; }

    public void setNic(String nic) { this.nic = nic; }

    public String getMobileNo() { return mobileNo; }

    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getHospital() { return hospital; }

    public void setHospital(String hospital) { this.hospital = hospital; }

    public String getRoomNo() { return roomNo; }

    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

}
