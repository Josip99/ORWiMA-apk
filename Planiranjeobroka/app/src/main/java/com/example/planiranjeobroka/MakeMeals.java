package com.example.planiranjeobroka;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MakeMeals extends Fragment implements NameClickListener {

    private TextView total;

    private RecyclerView recycler;
    private RecycleAdapter adapter;
    private Button refresh;
    private List<MealData> data = new ArrayList<>();
    getAllMeals sucelje;

    public MakeMeals() {
        // Required empty public constructor
    }

    public static MakeMeals newInstance() {
        MakeMeals fragment = new MakeMeals();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("crash", "idk");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler = view.findViewById(R.id.recyclerView);
        total = view.findViewById(R.id.totalCalories);

        setupRecycler();
        List<MealData> list= new ArrayList<>();
        Log.i("crash", "idk");
        adapter.setData(list);


        refresh = view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sucelje.getAll();

                adapter.setData(data);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_meals, container, false);
    }

    private void setupRecycler(){

        Log.i("crash", "ajdsta");
        //recycler = getView().findViewById(R.id.recyclerView);

        Log.i("crash", "ajdsta");
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        Log.i("crash", "ajdsta");
        adapter = new RecycleAdapter(this);
        Log.i("crash", "ajdsta");
        recycler.setAdapter(adapter);
    }




    public void getAll(List<MealData> lista){
        Log.i("what", "setlist");
        adapter.setData(lista);
    }

    @Override
    public void onBoxClick(int position){
        adapter.SelectCell(position);
        data=adapter.getData();
        float totalCals=0;
        for(int i=0;i<data.size();i++){
            if(data.get(i).getSelected()){
                totalCals+=data.get(i).getCal();
            }
        }
        total.setText("Ukupno kalorija: "+ totalCals + " kcal");
        //adapter.setData(data);
//        Log.i("dataIN", "prije ");
//        for(int i=0;i<data.size();i++){
//            Log.i("dataIN", ""+data.get(i).name+ " "+ data.get(i).cal+  " "+ data.get(i).selected);
//        }
        //MealData temp=data.remove(position);
        //temp.changeSelected();

        //data.add(position, temp);

        Log.i("box", "checked in MakeMeals");
    }



    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof getAllMeals){
            this.sucelje = (getAllMeals) context;
            Toast.makeText(getContext(), "attachan interface", Toast.LENGTH_SHORT).show();
            Log.i("getAll", "ATTACH");

            //getAllMeals();

        }
    }

    public void onDetach() {
        super.onDetach();
        this.sucelje = null;
    }
}