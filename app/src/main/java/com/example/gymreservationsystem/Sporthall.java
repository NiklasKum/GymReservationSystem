package com.example.gymreservationsystem;

import java.util.ArrayList;

public class Sporthall {
    private String sporthallName;

    private String address;

    private ArrayList<Gym> Gyms = new ArrayList<>();

    public Sporthall(String name, String _address){
        sporthallName = name;
        address = _address;
    }

    public String getAddress() {
        return address;
    }

    public String getSporthallName() {
        return sporthallName;
    }

    public void addGym(Gym gym){
        Gyms.add(gym);
    }

    public Gym getGymAt(int i){
        return Gyms.get(i);
    }

    public int getGymSize(){
        return Gyms.size();
    }


}
