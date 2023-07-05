package com.example.cardiacmonitor;

import com.example.cardiacmonitor.model.CardiacModel;

import java.util.ArrayList;

public class DataList {
    public static ArrayList<CardiacModel> array = new ArrayList<>();
    /**
     * Add records for the main page list
     * @param modelClass
     */
    public void addRecord(CardiacModel modelClass){
        if(array.contains(modelClass)){
            throw new IllegalArgumentException();
        }
        array.add(modelClass);


    }

    /**
     * delete records from the main page list.
     * @param position
     */
    public void deleteRecord(int position){


        if (position>=0 && position<array.size()) {
            array.remove(position);
        }
        else {
            throw new IllegalArgumentException() ;
        }


    }

    /**
     * return array size
     * @return array.size()
     */
    public int  count(){
        return  array.size();
    }
}
