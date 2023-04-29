package com.example.technocrat.ui.dashboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.technocrat.R;

public class SpoofFilenames extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int theme = 0;
    int buttonTheme = 0;

    ConstraintLayout constraintLayout;
    ActionBar actionBar;

    Spinner spinner;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoof_filenames);

        constraintLayout = findViewById(R.id.cl);
        actionBar = getSupportActionBar();
        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        SharedPreferences settings = getSharedPreferences("theme", Context.MODE_PRIVATE);
        theme = settings.getInt("colour", 0);
        buttonTheme = settings.getInt("buttonColour", 0);

        if (theme == 0) {
            constraintLayout.setBackgroundResource(R.color.white);
            getWindow().setNavigationBarColor(getColor(R.color.purple_200));
            getWindow().setStatusBarColor(getResources().getColor(R.color.purple_700));
            ColorDrawable colorDrawable
                    = new ColorDrawable(getResources().getColor(R.color.purple_500));
            actionBar.setBackgroundDrawable(colorDrawable);
        } else if (theme == 1) {
            constraintLayout.setBackgroundResource(R.color.skyblue_1);
            getWindow().setNavigationBarColor(getColor(R.color.skyblue_3));
            getWindow().setStatusBarColor(getResources().getColor(R.color.skyblue_3));
            ColorDrawable colorDrawable
                    = new ColorDrawable(getResources().getColor(R.color.skyblue_2));
            actionBar.setBackgroundDrawable(colorDrawable);
        } else if (theme == 2) {
            constraintLayout.setBackgroundResource(R.color.seafoam_1);
            getWindow().setNavigationBarColor(getColor(R.color.seafoam_3));
            getWindow().setStatusBarColor(getResources().getColor(R.color.seafoam_3));
            ColorDrawable colorDrawable
                    = new ColorDrawable(getResources().getColor(R.color.seafoam_2));
            actionBar.setBackgroundDrawable(colorDrawable);
        } else if (theme == 3) {
            constraintLayout.setBackgroundResource(R.color.twilitnight_1);
            getWindow().setNavigationBarColor(getColor(R.color.twilitnight_3));
            getWindow().setStatusBarColor(getResources().getColor(R.color.twilitnight_3));
            ColorDrawable colorDrawable
                    = new ColorDrawable(getResources().getColor(R.color.twilitnight_2));
            actionBar.setBackgroundDrawable(colorDrawable);
        } else if (theme == 4) {
            constraintLayout.setBackgroundResource(R.color.rose_1);
            getWindow().setNavigationBarColor(getColor(R.color.rose_3));
            getWindow().setStatusBarColor(getResources().getColor(R.color.rose_3));
            ColorDrawable colorDrawable
                    = new ColorDrawable(getResources().getColor(R.color.rose_2));
            actionBar.setBackgroundDrawable(colorDrawable);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = spinner.getSelectedItem().toString();
        if(selected.equals("Fake iPhone Camera Filename")){
            textView.setText("Example: IMG_0001");
        } else if(selected.equals("Fake Android Camera Filename")){
            textView.setText("IMG_20230302_114348");
        } else if(selected.equals("Fake UUID v1")){
            textView.setText("37e9316e-e637-11ed-a05b-0242ac120003");
        } else if(selected.equals("Fake UUID v4")){
            textView.setText("c453eab1-ad34-4e8e-8590-8a63ed030f8a");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
