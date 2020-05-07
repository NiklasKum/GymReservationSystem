package com.example.gymreservationsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class GridLayoutUIHandler  {



    public void BuildGridLayoutContent(AppCompatActivity act, GridLayout GL, CardView prefab){ // , ArrayList<Gym> gyms
        //Builds all the GridLayout views from the arraylist gyms
        //Should be called when coming to the view



        if(GL.getChildCount() > 0){
            //GL.removeAllViews();
            System.out.println(GL.getChildCount());
        } else {
            System.out.println(GL.getChildCount());
        }

        for(int i = 0; i < 7; i++){
            GL.addView(createCard(act, GL, prefab));
        }

    }

    private CardView createCard(AppCompatActivity act, GridLayout GL, CardView prefab){
        CardView toship = new CardView(act);
        toship.setLayoutParams(new CardView.LayoutParams(prefab.getLayoutParams()));
        return toship;
    }

}
