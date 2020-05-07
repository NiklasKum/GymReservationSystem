package com.example.gymreservationsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Gym> mData; // Change this and rebuild to change the content of Gridlayout. (USE WHEN SORTING)

    public RecyclerViewAdapter(Context context, List<Gym> mData){
        this.mContext = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_gym,parent,false);

        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //sets the attributes to the view that is to be inflated

        holder.tv_gymname.setText(mData.get(position).getGymName());
        holder.tv_gymnumber.setText("Number: "+mData.get(position).getGymNumber());
        holder.tv_hallname.setText(mData.get(position).getParentSportHallName());
        holder.tv_availability.setText(mData.get(position).getAvailability());
        holder.image_gymimage.setImageResource(mData.get(position).getGymImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.CB.setSelectedGym(mData.get(position));
                //Open Description and reservation view
                Intent intent = new Intent(mContext, Gym_Activity.class);

                //Pass data dynamically to the Gym activity
                intent.putExtra("ingym_gymName", mData.get(position).getGymName());
                intent.putExtra("ingym_gymNum", mData.get(position).getGymNumber());
                intent.putExtra("GymThumbnail", mData.get(position).getGymImage());
                intent.putExtra("ingym_hallname", mData.get(position).getParentSportHallName());
                intent.putExtra("ingym_gymAvail", mData.get(position).getAvailability());
                intent.putExtra("ingym_gymAddress", mData.get(position).getAddress());
                intent.putExtra("ingym_gymCap", mData.get(position).getCapacitySize());
                intent.putExtra("ingym_gymDesc", mData.get(position).getDescription());
                mContext.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    //Handles the RecyclerView






    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_gymname;
        TextView tv_gymnumber;
        TextView tv_hallname;
        TextView tv_availability;
        ImageView image_gymimage;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            tv_gymname = (TextView)itemView.findViewById(R.id.GymName);
            tv_gymnumber = (TextView)itemView.findViewById(R.id.GymNumber);
            tv_hallname = (TextView)itemView.findViewById(R.id.Sporthall_name);
            tv_availability = (TextView)itemView.findViewById(R.id.Gym_Availability);
            image_gymimage = (ImageView)itemView.findViewById(R.id.GymPicture);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
