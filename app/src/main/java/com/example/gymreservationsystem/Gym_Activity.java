package com.example.gymreservationsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Gym_Activity extends AppCompatActivity {


    private Spinner spinnerDay;
    private Spinner spinnerMonth;
    private Spinner spinnerYear;

    private ArrayList<String> days = new ArrayList<>();
    private String selectDay;
    private ArrayList<String> Months = new ArrayList<>();
    private String selectMonth;
    private ArrayList<String> Years = new ArrayList<>();
    private String selectYear;

    private LocalDate selectDATE;
    private String selectDATEToString;
    private String selectTimeSpan;

    private TextView tv_gymname;
    private TextView tv_gymnumber;
    private TextView tv_hallname;
    private TextView tv_availability;
    private TextView tv_desc;
    private TextView tv_address;
    private TextView tv_cap;
    private ImageView image_gymimage;

    //Time res buttons
    private TextView timeresTE;
    private Button bttn1;
    private Button bttn2;
    private Button bttn3;
    private Button bttn4;
    private Button bttn5;
    private Button bttn6;
    private Button bttn7;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_);

        spinnerDay = findViewById(R.id.daySpinner);
        spinnerMonth = findViewById(R.id.monthSpinner);
        spinnerYear = findViewById(R.id.yearSpinner);

        Button profilebtn = findViewById(R.id.profileDDMenu);
        profilebtn.setText(MainActivity.CB.getCurrentUser().getsUserName());
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Gym_Activity.this, Profile_Activity.class));
            }
        });

        //Because reserved times are only a month forward, setting dd are kinda mechanical
        Years.add("2020");
        Months.add("May");
        Months.add("June");

        selectDay = "01";
        selectMonth = "05";
        selectYear = "2020";

        ArrayAdapter<String> sMonth = new ArrayAdapter<>(Gym_Activity.this, android.R.layout.simple_list_item_1, Months);
        spinnerMonth.setAdapter(sMonth);

        ArrayAdapter<String> sYear = new ArrayAdapter<>(Gym_Activity.this, android.R.layout.simple_list_item_1, Years);
        spinnerYear.setAdapter(sYear);

        fillDaysList();
        BuildDropDownDays();
        setSelectDate();


        //Listeners for on item select
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDay = parent.getItemAtPosition(position).toString();
                setSelectDate();
                UpdateReservationButtons();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectMonth = parent.getItemAtPosition(position).toString();
                fillDaysList();
                BuildDropDownDays();
                setSelectDate();
                UpdateReservationButtons();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectYear = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_gymname = (TextView)findViewById(R.id.ingym_gymName);
        tv_gymnumber = (TextView)findViewById(R.id.ingym_gymNum);
        tv_hallname = (TextView)findViewById(R.id.ingym_hallname);
        tv_availability = (TextView)findViewById(R.id.ingym_gymAvail);
        image_gymimage = (ImageView)findViewById(R.id.GymThumbnail);
        tv_desc = (TextView)findViewById(R.id.ingym_gymDesc);
        tv_address = (TextView)findViewById(R.id.ingym_gymAddress);
        tv_cap = (TextView)findViewById(R.id.ingym_gymCap);

        //Catch data

        Intent intent = getIntent();

        String gName = intent.getExtras().getString("ingym_gymName");
        String sHallname = intent.getExtras().getString("ingym_hallname");
        int gNum = intent.getExtras().getInt("ingym_gymNum");
        String gAvail = intent.getExtras().getString("ingym_gymAvail");
        String gAddress = intent.getExtras().getString("ingym_gymAddress");
        int gCap = intent.getExtras().getInt("ingym_gymCap");
        String gDesc = intent.getExtras().getString("ingym_gymDesc");
        int gImage = intent.getExtras().getInt("GymThumbnail");

        //Set values

        tv_gymname.setText("Gym: "+gName);
        tv_gymnumber.setText("Number: "+gNum);
        tv_hallname.setText("Hall: "+sHallname);
        tv_availability.setText("Availability: "+gAvail);
        image_gymimage.setImageResource(gImage);
        tv_desc.setText("Description: "+gDesc);
        tv_address.setText("Address: "+gAddress);
        tv_cap.setText("Estimated capacity: "+gCap);

        timeresTE = findViewById(R.id.selectTimeTV);
        bttn1 = findViewById(R.id.time1btn);
        bttn2 = findViewById(R.id.time2btn);
        bttn3 = findViewById(R.id.time3btn);
        bttn4 = findViewById(R.id.time4btn);
        bttn5 = findViewById(R.id.time5btn);
        bttn6 = findViewById(R.id.time6btn);
        bttn7 = findViewById(R.id.time7btn);


        //Button Click listeners

        bttn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("07-09");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        bttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("09-11");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        bttn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("11-13");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        bttn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("13-15");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        bttn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("15-17");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        bttn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("17-19");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        bttn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectReservationTime("19-21");
                timeresTE.setText("Time reservation. Select Time: "+selectDATEToString);
                UpdateReservationButtons();
            }
        });
        UpdateReservationButtons();
    }


    private void fillDaysList(){
        days.clear();
        if(selectMonth != null){
            if(selectMonth.equals("May")){
                for(int i = 0; i < 31; i++){
                    days.add(String.valueOf(i+1));
                }
            } else if(selectMonth.equals("June")){
                for(int i = 0; i < 30; i++){
                    days.add(String.valueOf(i+1));
                }
            }
        }
    }

    public void BuildDropDownDays(){
        //adapt the days of the month
        ArrayAdapter<String> sDay = new ArrayAdapter<>(Gym_Activity.this, android.R.layout.simple_list_item_1, days);
        spinnerDay.setAdapter(sDay);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void selectReservationTime(String theHours){
        //selects and parses the reservation time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String inNumbersMonth;

        if(selectMonth.equals("May")){
            inNumbersMonth = "05";
        } else {
            inNumbersMonth = "06";
        }

        LocalDate lD = LocalDate.parse(selectDay+"/"+inNumbersMonth+"/"+selectYear, formatter);
        selectTimeSpan = theHours;
        selectDATE = lD;
        selectDATEToString = selectDay+"/"+inNumbersMonth+"/"+selectYear+" "+selectTimeSpan;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setSelectDate(){
        //selects and parses the reservation time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String inNumbersMonth;

        if(selectMonth.equals("May")){
            inNumbersMonth = "05";
        } else {
            inNumbersMonth = "06";
        }

        LocalDate lD = LocalDate.parse(selectDay+"/"+inNumbersMonth+"/"+selectYear, formatter);
        selectDATE = lD;
        selectDATEToString = selectDay+"/"+inNumbersMonth+"/"+selectYear+" "+selectTimeSpan;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void DoReservation(View view){
        //when pressed Reservation button
        if(MainActivity.CB.getSelectedGym() != null){
            for(int i = 0; i < MainActivity.CB.getSelectedGym().getReservationsSize(); i++){
                if(MainActivity.CB.getSelectedGym().getReservationAt(i).Empty() && MainActivity.CB.getSelectedGym().getReservationAt(i).getResDate().equals(selectDATE)){

                    if(MainActivity.CB.getSelectedGym().getReservationAt(i).getTimeID().equals(selectTimeSpan)){
                        MainActivity.CB.getSelectedGym().getReservationAt(i).setResUser(MainActivity.CB.getCurrentUser());
                        MainActivity.CB.getCurrentUser().getReservations().add(MainActivity.CB.getSelectedGym().getReservationAt(i));
                    }

                }
            }
        }
        UpdateReservationButtons();
        System.out.println("Selected: "+selectDATE.toString());
        System.out.println("The hours: "+selectTimeSpan);
        for(int i = 0; i < MainActivity.CB.getCurrentUser().getReservations().size(); i++){
            System.out.println(MainActivity.CB.getCurrentUser().getReservations().get(i).getResDate().toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void UpdateReservationButtons(){
        //Compares CB.selectGym().Reservation times to select times.
        for(int i = 0; i < MainActivity.CB.getSelectedGym().getReservationsSize(); i++){
            if(MainActivity.CB.getSelectedGym().getReservationAt(i).getResDate().equals(selectDATE)){

                if(!MainActivity.CB.getSelectedGym().getReservationAt(i).Empty()){
                    switch (MainActivity.CB.getSelectedGym().getReservationAt(i).getTimeID()){
                        case "07-09":
                            bttn1.setText("07-09, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn1.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                        case "09-11":
                            bttn2.setText("09-11, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn2.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                        case "11-13":
                            bttn3.setText("11-13, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn3.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;

                        case "13-15":
                            bttn4.setText("13-15, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn4.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                        case "15-17":
                            bttn5.setText("15-17, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn5.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                        case "17-19":
                            bttn6.setText("17-19, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn6.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                        case "19-21":
                            bttn7.setText("19-21, Reserved by "+MainActivity.CB.getSelectedGym().getReservationAt(i).getResUser().getsUserName());
                            bttn7.setBackgroundColor(Color.parseColor("#FF0000"));
                            break;
                    }
                } else {
                    switch (MainActivity.CB.getSelectedGym().getReservationAt(i).getTimeID()){
                        case "07-09":
                            bttn1.setText("07-09");
                            bttn1.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;
                        case "09-11":
                            bttn2.setText("09-11");
                            bttn2.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;
                        case "11-13":
                            bttn3.setText("11-13");
                            bttn3.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;

                        case "13-15":
                            bttn4.setText("13-15");
                            bttn4.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;
                        case "15-17":
                            bttn5.setText("15-17");
                            bttn5.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;
                        case "17-19":
                            bttn6.setText("17-19");
                            bttn6.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;
                        case "19-21":
                            bttn7.setText("19-21");
                            bttn7.setBackgroundColor(Color.parseColor("#04FF00"));
                            break;
                    }
                }

            }
        }
    }
}
