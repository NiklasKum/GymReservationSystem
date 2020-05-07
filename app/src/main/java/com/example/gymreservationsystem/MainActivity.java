package com.example.gymreservationsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private CardView cardItemPrefab;
    private GridLayout theGridLayout;
    private GridLayoutUIHandler GridUIhandler = new GridLayoutUIHandler();
    private List<Gym> shiplst;

    public static CommonDatabase CB = new CommonDatabase();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Var declares
        //theGridLayout = findViewById(R.id.TheGrid);
        //LayoutInflater LI = getLayoutInflater();

        Button profileBtn = findViewById(R.id.profileDDMenu);
        profileBtn.setText(CB.getCurrentUser().getsUserName());

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OnClick listener for the profile button. Drops down "popup_menu.xml" view with its items
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) MainActivity.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });

        //On start methods

        /* Create some sportshalls and gyms to emulate the app */

        if(CB.getSporthalls().size() > 0){

        }else {
            CB.clearSporthalls();

            Sporthall hall1 = new Sporthall("CUNTO liikuntahalli", "Liikunnankatu 3 A 1");
            hall1.addGym(new Gym("Parkettisali", "Woodfloor", hall1.getSporthallName(), "Vapaa", 1, 40, "Sali soveltuu kaikkiin pallopeleihin", R.drawable.images, hall1.getAddress()));
            hall1.addGym(new Gym("Koripallosali", "Woodfloor", hall1.getSporthallName(), "Vapaa", 2, 36, "Sali on pääasiassa koripallo sali", R.drawable.lataus, hall1.getAddress()));
            Sporthall hall2 = new Sporthall("Monitoimihalli", "Valtakunnantie 2");
            hall2.addGym(new Gym("Voimistelusali", "Woodfloor", hall2.getSporthallName(), "Vapaa", 1, 40, "Voimisteluun tarkoitettu sali", R.drawable.muovilattia, hall2.getAddress()));
            hall2.addGym(new Gym("liikuntasali", "Woodfloor", hall2.getSporthallName(), "Vapaa", 2, 20, "Liikunta sali tarvikkeilla", R.drawable.yleisurheiluhalli, hall2.getAddress()));
            hall2.addGym(new Gym("Lentopallosali", "VolleyBall", hall2.getSporthallName(), "Vapaa", 3, 35, "Lentopallo sali, jossa on 3 lentopallo verkkoa. Salissa on myös katsomo.", R.drawable.lentopallo, hall2.getAddress()));
            hall2.addGym(new Gym("Sählysali", "VolleyBall", hall2.getSporthallName(), "Vapaa", 4, 28, "Sählysalissa on 8 salibandy maalia, ylimääräisiä tarvikkeita sekä kaksi erillistä pukuhuonetta. Salissa on myös katsomo.", R.drawable.sahlyhalli, hall2.getAddress()));

            CB.addSporthall(hall1);
            CB.addSporthall(hall2);
            CB.addGyms();


        }



        shiplst = new ArrayList<>();
        for(int i = 0; i < CB.getGymsList().size() ; i++){
            shiplst.add(CB.getGymAt(i));
        }

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, shiplst);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.Profile:
                //Change activity to profile
                startActivity(new Intent(MainActivity.this, Profile_Activity.class));
                return true;

            case R.id.Log_out:
                //Change activity to Log out
                CB.Logout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;

            default:
                return false;
        }
    }
}
