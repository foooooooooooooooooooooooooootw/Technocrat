package com.example.technocrat.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.technocrat.R;

public class ThemeSettings extends AppCompatActivity {

    int theme = 0;
    int buttonTheme = 0;

    ConstraintLayout constraintLayout;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_settings);
        constraintLayout = findViewById(R.id.cl);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button5);
        button4 = findViewById(R.id.button6);
        button5 = findViewById(R.id.button7);
        SharedPreferences settings = getSharedPreferences("theme", Context.MODE_PRIVATE);
        theme = settings.getInt("colour", 0);
        buttonTheme = settings.getInt("buttonColour", 0);

        if (theme == 0) {
            constraintLayout.setBackgroundResource(R.color.white);
            button1.setBackgroundColor(getResources().getColor(R.color.purple_500));
            button2.setBackgroundColor(getResources().getColor(R.color.purple_500));
            button3.setBackgroundColor(getResources().getColor(R.color.purple_500));
            button4.setBackgroundColor(getResources().getColor(R.color.purple_500));
            button5.setBackgroundColor(getResources().getColor(R.color.purple_500));
        } else if (theme == 1) {
            constraintLayout.setBackgroundResource(R.color.skyblue_1);
            button1.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
            button2.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
            button3.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
            button4.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
            button5.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
        } else if (theme == 2) {
            constraintLayout.setBackgroundResource(R.color.seafoam_1);
            button1.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
            button2.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
            button3.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
            button4.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
            button5.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
        } else if (theme == 3) {
            constraintLayout.setBackgroundResource(R.color.twilitnight_1);
            button1.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
            button2.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
            button3.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
            button4.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
            button5.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
        } else if (theme == 4) {
            constraintLayout.setBackgroundResource(R.color.rose_1);
            button1.setBackgroundColor(getResources().getColor(R.color.rose_2));
            button2.setBackgroundColor(getResources().getColor(R.color.rose_2));
            button3.setBackgroundColor(getResources().getColor(R.color.rose_2));
            button4.setBackgroundColor(getResources().getColor(R.color.rose_2));
            button5.setBackgroundColor(getResources().getColor(R.color.rose_2));
        }
    }
    public void seaFoam(View view) {
        constraintLayout.setBackgroundResource(R.color.seafoam_1);
        button1.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
        button2.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
        button3.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
        button4.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
        button5.setBackgroundColor(getResources().getColor(R.color.seafoam_2));
        SharedPreferences.Editor editor = getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("colour", 2);
        editor.apply();
    }


    public void skyBlue(View view) {
        constraintLayout.setBackgroundResource(R.color.skyblue_1);
        button1.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
        button3.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
        button4.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
        button5.setBackgroundColor(getResources().getColor(R.color.skyblue_2));
        SharedPreferences.Editor editor = getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("colour", 1);
        editor.apply();
    }

    public void twilitNight(View view) {
        constraintLayout.setBackgroundResource(R.color.twilitnight_1);
        button1.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
        button2.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
        button3.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
        button4.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
        button5.setBackgroundColor(getResources().getColor(R.color.twilitnight_2));
        SharedPreferences.Editor editor = getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("colour", 3);
        editor.apply();
    }

    public void defaultColour(View view) {
        constraintLayout.setBackgroundResource(R.color.white);
        button1.setBackgroundColor(getResources().getColor(R.color.purple_500));
        button2.setBackgroundColor(getResources().getColor(R.color.purple_500));
        button3.setBackgroundColor(getResources().getColor(R.color.purple_500));
        button4.setBackgroundColor(getResources().getColor(R.color.purple_500));
        button5.setBackgroundColor(getResources().getColor(R.color.purple_500));
        SharedPreferences.Editor editor = getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("colour", 0);
        editor.apply();
    }

    public void rose(View view) {
        constraintLayout.setBackgroundResource(R.color.rose_1);
        button1.setBackgroundColor(getResources().getColor(R.color.rose_2));
        button2.setBackgroundColor(getResources().getColor(R.color.rose_2));
        button3.setBackgroundColor(getResources().getColor(R.color.rose_2));
        button4.setBackgroundColor(getResources().getColor(R.color.rose_2));
        button5.setBackgroundColor(getResources().getColor(R.color.rose_2));
        SharedPreferences.Editor editor = getSharedPreferences("theme", Context.MODE_PRIVATE).edit().putInt("colour", 4);
        editor.apply();
    }
}