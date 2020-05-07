package com.example.gymreservationsystem;

import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Gym {
    //Liikuntasali
    private String gymName;

    private String Specification;

    private String ParentSportHallName;

    private String Availability;

    private int gymNumber;

    private int capacitySize;

    private String Description;

    private int gymImage;

    private String Address;

    private ArrayList<Reservation> gymReservations = new ArrayList<>(); //

    //Reservations

    private LocalDate reservEnd = LocalDate.now().plusMonths(1); //Creates reservations one month forward from this date

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void CreateEmptyReservations(){
        for(LocalDate date = LocalDate.now(); date.isBefore(reservEnd); date = date.plusDays(1)){
            //Loops through all the days from now til reserve end cap
            //Create reservations so that there's two hour shifts from klo 7 to klo 21
            //set reserver to null so it will be counted as an empty reservation
            gymReservations.add(new Reservation(date, this, null, "07-09"));
            gymReservations.add(new Reservation(date, this, null, "09-11"));
            gymReservations.add(new Reservation(date, this, null, "11-13"));
            gymReservations.add(new Reservation(date, this, null, "13-15"));
            gymReservations.add(new Reservation(date, this, null, "15-17"));
            gymReservations.add(new Reservation(date, this, null, "17-19"));
            gymReservations.add(new Reservation(date, this, null, "19-21"));
        }
    }

    public Gym(String _name, String _spec, String _parentname, String _avail, int _number, int Size, String Desc, int img, String address){
        gymName = _name;
        Specification = _spec;
        ParentSportHallName = _parentname;
        Availability = _avail;
        gymNumber = _number;
        capacitySize = Size;
        Description = Desc;
        gymImage = img;
        Address = address;
        CreateEmptyReservations();
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public int getGymImage() {
        return gymImage;
    }

    public String getGymName(){
        return gymName;
    }

    public String getSpecification(){
        return Specification;
    }

    public String getParentSportHallName(){
        return ParentSportHallName;
    }

    public String getAvailability() {
        return Availability;
    }

    public int getGymNumber() {
        return gymNumber;
    }

    public int getCapacitySize() {
        return capacitySize;
    }

    public String getDescription() {
        return Description;
    }

    public String getAddress() {
        return Address;
    }

    public int getReservationsSize(){
        return gymReservations.size();
    }

    public Reservation getReservationAt(int x){
        return gymReservations.get(x);
    }


}
