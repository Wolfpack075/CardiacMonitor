package com.example.cardiacmonitor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacmonitor.R;

import com.example.cardiacmonitor.DataList;
import com.example.cardiacmonitor.model.CardiacModel;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CardiacViewHolder> {
    private static CustomClickListener customClickListener;
    private ArrayList<CardiacModel> cardiacModelsArrayList;
    private CardiacModel cardiacModel;
    private final Context context;


    //constructor
    public CustomAdapter(Context context, ArrayList<CardiacModel> rList) {
        this.cardiacModelsArrayList =rList;
        this.context = context;

    }

    public CustomAdapter(ArrayList<CardiacModel> cardiacModelsArrayList, Context context) {
        this.cardiacModelsArrayList = cardiacModelsArrayList;
        this.context = context;
    }

    public void setCustomClickListener(CustomClickListener customClickListener) //called from mainactivity
    {
        this.customClickListener = customClickListener; //setting data
    }

    @NonNull
    @Override
    public CardiacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //an object of roomview holder which contain itemview
        View view = LayoutInflater.from(context).inflate(R.layout.patientsdata_row, parent, false);
        return new CardiacViewHolder(view); //passed in itemview

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CardiacViewHolder holder,@SuppressLint("RecyclerView") int position) {
        if (!cardiacModelsArrayList.isEmpty()) cardiacModel = DataList.array.get(position);
        // Toast.makeText(context, ""+cardiacModel.getDate(), Toast.LENGTH_LONG).show();
        holder.dateTextView.setText("Date: "+cardiacModel.getDate());
        holder.systolicTextView.setText("Systolic: "+cardiacModel.getSystolic());
        holder.diastolicTextView.setText("Diastolic: "+cardiacModel.getDiastolic());
        holder.heartTextView.setText("Heart Rate: "+cardiacModel.getHeartRate());
        holder.deleterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customClickListener != null)
                {
                    int position = holder.getAbsoluteAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        customClickListener.onDeleteClick(position);
                    }
                }
            }
        });

        cardiacModel = cardiacModelsArrayList.get(position);
        if (Integer.parseInt(cardiacModel.getDiastolic())>60 && (Integer.parseInt(cardiacModel.getDiastolic())<90))holder.diastolicTextView.setTextColor(Color.parseColor("#FF018786"));
            // else if(Integer.parseInt(modelClass.getDiastolic())<89)
            // holder.tx3.setTextColor(Color.parseColor("#3C96DD"));
        else holder.diastolicTextView.setTextColor(Color.parseColor("#C3473E"));


        if (Integer.parseInt(cardiacModel.getSystolic())>90 &&( Integer.parseInt(cardiacModel.getSystolic())<140)) holder.systolicTextView.setTextColor(Color.parseColor("#FF018786"));
            //else if(Integer.parseInt(modelClass.getSystolic())<=140) holder.tx2.setTextColor(Color.parseColor("#3C96DD"));
        else holder.systolicTextView.setTextColor(Color.parseColor("#C3473E"));


        if (Integer.parseInt(cardiacModel.getHeartRate())>60 && Integer.parseInt(cardiacModel.getHeartRate())<100) holder.heartTextView.setTextColor(Color.parseColor("#FF018786"));
        else if(Integer.parseInt(cardiacModel.getHeartRate())>=40) holder.heartTextView.setTextColor(Color.parseColor("#3C96DD"));
        else holder.heartTextView.setTextColor(Color.parseColor("#C3473E"));


    }

    @Override
    public int getItemCount() {
        return cardiacModelsArrayList.size();
    }

    public interface CustomClickListener {
        void customOnClick(int position, View v);

        void customOnLongClick(int position, View v);

        void onDeleteClick(int position);
        //declaring method which will provide to main activity //position and view will also be provided
    }

    public class CardiacViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView systolicTextView;
        TextView heartTextView;
        TextView diastolicTextView;
        TextView dateTextView;
        CardView containerCardView;
        ImageView deleterButton;

        public CardiacViewHolder(@NonNull View itemView) {
            super(itemView);
            systolicTextView = itemView.findViewById(R.id.data_systolic_txt);
            diastolicTextView = itemView.findViewById(R.id.data_diastolic_txt);
            dateTextView = itemView.findViewById(R.id.data_date_txt);
            heartTextView = itemView.findViewById(R.id.data_heartrate_txt);
            containerCardView = itemView.findViewById(R.id.llContainerCardView);
            deleterButton = itemView.findViewById(R.id.deleteButton);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        } //to create view of every list item

        @Override
        public void onClick(View view) {
            customClickListener.customOnClick(getAdapterPosition(), view);  //position and view setting to provide to mainactivity
        }

        public boolean onLongClick(View view) {

            customClickListener.customOnLongClick(getAdapterPosition(), view);  //position and view setting to provide to mainactivity
            return true;

        }
    }


}