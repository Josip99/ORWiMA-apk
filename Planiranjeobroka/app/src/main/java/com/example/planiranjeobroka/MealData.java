package com.example.planiranjeobroka;

import android.util.Log;

public class MealData {
    String name;
    float cal;
    boolean selected;

    MealData(String mName, float mCal){
        name=mName;
        cal=mCal;
        selected=false;
    }
    MealData(MealData x){
        this.name=x.name;
        this.cal=x.cal;
        this.selected=x.selected;
    }

    public float getCal() {
        return cal;
    }

    public String getName() {
        return name;
    }

    public void setCal(float cal) {
        this.cal = cal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSelected(){
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void changeSelected(){
        Log.i("selected", ""+selected);
        selected=!selected;
        Log.i("selected", ""+selected);

    }
}
