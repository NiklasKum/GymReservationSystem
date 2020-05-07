package com.example.gymreservationsystem;

import java.util.ArrayList;

public class User {

    private String sEmail;

    private String sUserName;

    private String sPassword;

    private ArrayList<Reservation> reservations = new ArrayList<>(); //User reservations


    public User(String email, String usern, String password){
        sEmail = email;
        sUserName = usern;
        sPassword = password;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsPassword() {
        return sPassword;
    }

    public String getsUserName() {
        return sUserName;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation res){
        reservations.add(res);
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
}
