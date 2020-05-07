package com.example.gymreservationsystem;

import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CommonDatabase {

    private ArrayList<Sporthall> Sporthalls = new ArrayList<>();

    private ArrayList<User> Users = new ArrayList<>();

    private User currentUser;

    private File userdataFolder;

    private ArrayList<Gym> Gyms = new ArrayList<>(); //Recylerview accesses this list to create views of the elements

    private Gym selectedGym; //Currently selected gym, Used when creating a reservation

    private LocalDate currentDate;




    public User getUsersAt(int x){
        return Users.get(x);
    }

    public User getCurrentUser() {
        return currentUser;
    }



    /* SPORTHALL AND GYM HANDLING */

    public void addSporthall(Sporthall sh){
        Sporthalls.add(sh);
    }

    public ArrayList<Sporthall> getSporthalls() {
        return Sporthalls;
    }

    public void addGyms(){
        //Called only once at MAINACTIVITY. adds all the sporthalls gyms to a lis
        if(Gyms.size() > 0){
            Gyms.clear();
        }
        for(int i = 0; i < Sporthalls.size(); i++) {
            for (int j = 0; j < Sporthalls.get(i).getGymSize(); j++) {
                Gyms.add(Sporthalls.get(i).getGymAt(j));
            }
        }
    }

    public void clearSporthalls(){
        Sporthalls.clear();
    }

    public Gym getGymAt(int i){
        return Gyms.get(i);
    }

    public ArrayList<Gym> getGymsList(){
        return Gyms;
    }

    public void setSelectedGym(Gym selectedGym) {
        this.selectedGym = selectedGym;
    }

    public Gym getSelectedGym() {
        return selectedGym;
    }

    /* \/ USER DATA HANDLING \/ */

    public void addUsers(AppCompatActivity activity , User u){
        Users.add(u);
        WriteFile(activity, u);
    }

    public void RemoveUserReservationFromGym(Reservation rese){
        Gym thegym = rese.getResGym();
        for(int i = 0; i < Gyms.size(); i++){
            if(Gyms.get(i).equals(thegym)){

                for(int j = 0; j < Gyms.get(i).getReservationsSize(); j++){

                    if(Gyms.get(i).getReservationAt(j).getResDate().equals(rese.getResDate()) && Gyms.get(i).getReservationAt(j).getTimeID().equals(rese.getTimeID()) && !Gyms.get(i).getReservationAt(j).Empty()){
                        Gyms.get(i).getReservationAt(j).setResUser(null);
                    }

                }


            }
        }
    }

    public boolean SignUp(EditText usernameET, EditText passwordET, EditText emailET){
        //Test if Username, password and email inputs have something
        if((usernameET.getText() != null && !usernameET.getText().toString().isEmpty()) && (passwordET.getText() != null && !passwordET.getText().toString().isEmpty()) && (emailET.getText() != null && !emailET.getText().toString().isEmpty())){
            for(int i = 0; i < Users.size(); i++){
                //check if account with same info exist, if so, cancel signup
                if(Users.get(i).getsUserName().equals(usernameET.getText().toString()) && Users.get(i).getsEmail().equals(emailET.getText().toString())){
                    return false;
                }
            }
            User madeu = new User(emailET.getText().toString(), usernameET.getText().toString(), passwordET.getText().toString());
            NULLWRITEFILEaddUser(madeu);
            currentUser = madeu;
            return true;
        }
        return false;
    }

    public void Logout(){
        if(currentUser != null){
            currentUser = null;
        }
    }

    public void NULLWRITEFILEaddUser(User u){
        //Add user without creating textfile
        Users.add(u);
    }

    public boolean Login(EditText usernameET, EditText psswordET){
        //checks if EditText elements equals to the Users Arraylist, sets the Current user as the match and returns true.
        if(usernameET.getText() != null  && psswordET.getText() != null){


            for(int i = 0; i < Users.size(); i++){
                System.out.println("Do loop");
                if(Users.get(i).getsUserName().equals(usernameET.getText().toString()) && Users.get(i).getsPassword().equals(psswordET.getText().toString())){
                    currentUser = Users.get(i);

                    return true;
                }
            }
        }
        return false;
    }

    public void WriteFile(AppCompatActivity activity, User u){

        File folder = new File(activity.getFilesDir(), "UserData");
        if(!folder.exists()){
            folder.mkdir();
        }
        userdataFolder = folder;
        try{
            File thefile = new File(folder, "Udata");
            FileWriter writer = new FileWriter(thefile,true);
            writer.append(u.getsEmail()+";"+u.getsUserName()+";"+u.getsPassword()); //if problem, add "\n" eli null string to the end
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFromFile(){

    }



}

