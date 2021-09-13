package com.example.planiranjeobroka;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMeal extends Fragment {
    private Button btInsert;
    private EditText Etname, Etcalories;
    sucelje Sucelje;

    public NewMeal() {

    }

    public static NewMeal newInstance() {
        NewMeal fragment = new NewMeal();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btInsert = view.findViewById(R.id.btInsert);
        Etname = view.findViewById(R.id.etName);
        Etcalories = view.findViewById(R.id.etCal);


        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arg1 = Etname.getText().toString();
                float arg2 = -1;
                try {
                    arg2 = Float.parseFloat( Etcalories.getText().toString() );
                }
                catch (Exception e){
                    //Toast.makeText(getContext(), "Nije unesena ispravna kolicina kalorija", Toast.LENGTH_LONG).show();
                }

                if(arg1!="" && arg2>0){
                    Sucelje.InsertNew(arg1, arg2);
                    Etname.setText("");
                    Etcalories.setText("");
                }
                else Toast.makeText(getContext(), "Neispravni podaci", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_meal, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof sucelje) {
            this.Sucelje = (sucelje) context;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.Sucelje = null;
    }


}