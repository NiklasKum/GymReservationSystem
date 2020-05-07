package com.example.gymreservationsystem;

import java.time.LocalDate;

public class Reservation {

    private LocalDate resDate;

    private Gym resGym;

    private User resUser; // reserver

    private String timeID; // "07-09", "09-11", "11-13", "13-15", "15-17", "17-19", "19-21",

    public void setTimeID(String timeID) {
        this.timeID = timeID;
    }

    public Reservation(LocalDate date, Gym theGym, User user, String id){
        resDate = date;
        resGym = theGym;
        resUser = user;
        timeID = id;
    }

    public boolean Empty(){
        if(resUser == null){
            return true;
        } else {
            return false;
        }
    }

    public Gym getResGym() {
        return resGym;
    }

    public void setResUser(User resUser) {
        this.resUser = resUser;
    }

    public User getResUser(){
        return resUser;
    }

    public LocalDate getResDate(){
        return resDate;
    }

    public void setResDate(LocalDate Ldate){
        resDate = Ldate;
    }

    public String getTimeID() {
        return timeID;
    }
}
