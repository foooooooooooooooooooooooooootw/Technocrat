package com.example.technocrat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.technocrat.ui.settings.ThemeSettings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.technocrat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int theme = 0;
    int buttonTheme = 0;

    ConstraintLayout constraintLayout;
    BottomNavigationView bottomNavigationView;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        SharedPreferences settings = getSharedPreferences("theme", Context.MODE_PRIVATE);
        theme = settings.getInt("colour", 0);
        constraintLayout = findViewById(R.id.container);
        if (theme == 0) {
            constraintLayout.setBackgroundResource(R.color.white);
        } else if (theme == 1) {
            constraintLayout.setBackgroundResource(R.color.skyblue_1);
        } else if (theme == 2) {
            constraintLayout.setBackgroundResource(R.color.seafoam_1);
        } else if (theme == 3) {
            constraintLayout.setBackgroundResource(R.color.twilitnight_1);
        } else if (theme == 4) {
            constraintLayout.setBackgroundResource(R.color.rose_1);
        }
    }
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences("theme", Context.MODE_PRIVATE);
        theme = settings.getInt("colour", 0);
        constraintLayout = findViewById(R.id.container);
        if (theme == 0) {
            constraintLayout.setBackgroundResource(R.color.white);
        } else if (theme == 1) {
            constraintLayout.setBackgroundResource(R.color.skyblue_1);
        } else if (theme == 2) {
            constraintLayout.setBackgroundResource(R.color.seafoam_1);
        } else if (theme == 3) {
            constraintLayout.setBackgroundResource(R.color.twilitnight_1);
        } else if (theme == 4) {
            constraintLayout.setBackgroundResource(R.color.rose_1);
        }
    }

    public void ThemeSettingsOpen (View v){
        Intent intent = new Intent(this, ThemeSettings.class);
        startActivity(intent);
    }
}