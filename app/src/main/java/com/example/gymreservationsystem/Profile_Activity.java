package com.example.gymreservationsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile_Activity extends AppCompatActivity {

    private TextView usernameTE;
    private TextView passwordTE;
    private TextView emailTE;
    private ListView ReservationsLV;
    private Button ChangePassBTN;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        setProfileData();
        BuildContentForListView();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setProfileData(){
        //Set the profile view data to the current user data
        usernameTE = findViewById(R.id.Profile_Username);
        passwordTE = findViewById(R.id.Profile_Password);
        emailTE = findViewById(R.id.Profile_Email);
        ReservationsLV = findViewById(R.id.Profile_ResListView);

        Button profileButton = findViewById(R.id.profileDDMenu);
        profileButton.setText(MainActivity.CB.getCurrentUser().getsUserName());

        ReservationsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Remove Reservation: "+parent.getItemAtPosition(position).toString());
                builder.setMessage("Do you want to Remove the selected reservation?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.CB.RemoveUserReservationFromGym(MainActivity.CB.getCurrentUser().getReservations().get(position));
                                MainActivity.CB.getCurrentUser().getReservations().remove(position);
                                BuildContentForListView();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        usernameTE.setText("Username: "+MainActivity.CB.getCurrentUser().getsUserName());
        passwordTE.setText("Password: "+MainActivity.CB.getCurrentUser().getsPassword());
        emailTE.setText("email: "+MainActivity.CB.getCurrentUser().getsEmail());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void BuildContentForListView(){
        //Fills the ListView with the current users Reservations

        ArrayList<String> ReservationDescs = new ArrayList<>();
        for(int i = 0; i < MainActivity.CB.getCurrentUser().getReservations().size(); i++){
            ReservationDescs.add (MainActivity.CB.getCurrentUser().getReservations().get(i).getResGym().getParentSportHallName()+", "+MainActivity.CB.getCurrentUser().getReservations().get(i).getResGym().getGymName()+", "+MainActivity.CB.getCurrentUser().getReservations().get(i).getResDate().toString()+", "+MainActivity.CB.getCurrentUser().getReservations().get(i).getTimeID());
        }
        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<>(Profile_Activity.this, android.R.layout.simple_list_item_1, ReservationDescs);
        ReservationsLV.setAdapter(ListViewAdapter);

    }



}
