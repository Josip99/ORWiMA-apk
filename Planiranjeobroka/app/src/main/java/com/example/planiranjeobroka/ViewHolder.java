package com.example.planiranjeobroka;


import static android.os.SystemClock.sleep;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvname,tvcals;
    private CheckBox select_button;
    private NameClickListener clickListener;

    public ViewHolder(@NonNull View itemView, NameClickListener listener){
        super(itemView);
        this.clickListener=listener;
        tvname = (TextView) itemView.findViewById(R.id.MealName_comp);
        tvcals = (TextView) itemView.findViewById(R.id.MealCal_comp);
        select_button = (CheckBox) itemView.findViewById(R.id.selectCB);
//        select_button.setChecked(true);
        itemView.setOnClickListener(this);
    }

    public void setView(MealData data){
        tvname.setText(data.getName());
        tvcals.setText(data.getCal() + " kcal");
        select_button.setChecked(data.getSelected());
    }

    public void setButtonState(boolean m){
        Log.i("boxx", "oznacen"+m);
        Log.i("boxx", "prije toga oznacen"+getButtonState());
        Log.i("boxx", ""+getAdapterPosition());
        //select_button.setChecked(m);
        sleep(2000);
    }

    public boolean getButtonState(){
        return select_button.isChecked();
    }

    @Override
    public void onClick(View view){
        Log.i("boxx", "prvi klik");
        //setButtonState(true);
        clickListener.onBoxClick(getAdapterPosition());
    }
}
