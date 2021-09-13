package com.example.planiranjeobroka;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class RecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private NameClickListener clickListener;
    List<MealData> data = new ArrayList<>();


    public RecycleAdapter(NameClickListener listener){
        clickListener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mealcomponent, parent, false);
        return new ViewHolder(cellView, clickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("setHold", "" + position + " " + data.get(position).name + " " + data.get(position).selected);
        holder.setView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<MealData> mData){

        Log.i("getAll", "doso u adapter");
        data.clear();
        data.addAll(mData);
        Log.i("dataIN", "prije ");
        for(int i=0;i<data.size();i++){
            Log.i("dataIN", ""+data.get(i).name+ " "+ data.get(i).cal+  " "+ data.get(i).selected);
        }
        notifyDataSetChanged();
    }

    public List<MealData> getData(){
        return data;
    }

    public void SelectCell(int position){
        Log.i("adapter", "prije ");
        for(int i=0;i<data.size();i++){
            Log.i("adapter", ""+data.get(i).name+ " "+ data.get(i).cal+  " "+ data.get(i).selected);
        }
        MealData temp = data.remove(position);
        temp.changeSelected();

        data.add(position, temp);
        Log.i("adapter", "poslije ");
        for(int i=0;i<data.size();i++){
            Log.i("adapter", ""+data.get(i).name+ " "+ data.get(i).cal+  " "+ data.get(i).selected);
        }

        notifyDataSetChanged();

    }
}
