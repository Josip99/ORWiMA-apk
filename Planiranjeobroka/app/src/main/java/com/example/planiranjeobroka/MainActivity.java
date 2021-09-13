package com.example.planiranjeobroka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements sucelje, getAllMeals{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setUpPager();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tab);
    }
    private void setUpPager() {
        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void InsertNew(String name, float cal){
        Log.i("tipka", "tocka2.05");

        int broj=getMealCount();
        if(broj>=999999){
            Toast.makeText(getApplicationContext(),"Previse jela, nije uneseno", Toast.LENGTH_LONG).show();
            return;
        }
        Log.i("tipka", "tocka2.1");

        String keyNumber=Integer.toString(broj);
        while(keyNumber.length()<6){
            keyNumber="0"+keyNumber;
        }
        String keyName = "Naziv"+keyNumber;
        String keyCal = "Cal"+keyNumber;
        Log.i("shared", keyName);
        Log.i("shared", keyCal);
        Log.i("tipka", "tocka2.2");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString(keyName, name);
            editor.putFloat(keyCal, cal);
            editor.putInt("brojjela", broj+1);
            editor.apply();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Neuspješno spremanje jela", Toast.LENGTH_LONG).show();

        }
        Log.i("tipka", "tocka2.3");

        Log.i("getAll", "check1");
        getAllMeals();
        Log.i("getAll", "checkLAST");

        return;
    }

//    public void incrementMealCount(){
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        int broj=0;
//
//        try {
//            broj = sharedPreferences.getInt("brojjela", -1);
//        }
//        catch (Exception e){
//            Toast.makeText(getApplicationContext(), "cant get number of meals", Toast.LENGTH_SHORT).show();
//        }
//        if(broj == -1){
//            editor.putInt("brojjela", 0);
//            editor.apply();
//            broj=0;
//            Toast.makeText(getApplicationContext(), "THIS SHOULDNT HAPPEN", Toast.LENGTH_SHORT).show();
//        }
//
//        broj = broj + 1;
//        editor.putInt("brojjela", broj);
//        editor.apply();
//    }

    public int getMealCount(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int broj=0;

        try {
            broj = sharedPreferences.getInt("brojjela", -1);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "cant get number of meals", Toast.LENGTH_SHORT).show();
        }
        if(broj == -1){
            editor.putInt("brojjela", 0);
            editor.apply();
            broj=0;
        }

        return broj;
    }

    public List<MealData> getAllMeals(){
        List<MealData> data = new ArrayList<>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int broj = getMealCount();
        try {
            for(int i=0;i<broj;i++){
                String keyNumber=Integer.toString(i);

                while(keyNumber.length()<6) keyNumber="0"+keyNumber;

                String keyName = "Naziv"+keyNumber;
                String keyCal = "Cal"+keyNumber;
                Log.i("preference", keyName);
                Log.i("preference", keyCal);

                String name = sharedPreferences.getString(keyName, "");
                Float cal = sharedPreferences.getFloat(keyCal, -1);

                Log.i("dohvacanje", "" + i + " " + name);
                Log.i("dohvacanje", "" + i + " " + cal);
                data.add(new MealData(name, cal));

            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Neuspješan pokušaj dohvaćanja svih podataka", Toast.LENGTH_SHORT).show();
        }

        return data;
    }

    @Override
    public List<MealData> getAll(){
        List<MealData> temp= getAllMeals();
        Log.i("getAll", "pokupio podatke");
        return temp;
    }
}