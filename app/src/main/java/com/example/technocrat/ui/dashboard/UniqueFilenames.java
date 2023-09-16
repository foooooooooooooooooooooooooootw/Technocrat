package com.example.technocrat.ui.dashboard;

import static android.view.View.GONE;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.technocrat.R;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;


public class UniqueFilenames extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int theme = 0;
    int buttonTheme = 0;

    ConstraintLayout constraintLayout;
    ActionBar actionBar;

    Spinner spinner3;

    ImageView imageView4;

    ImageView imageView5;

    Button button13;

    Button button15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_filenames);

        constraintLayout = findViewById(R.id.cl);
        actionBar = getSupportActionBar();
        spinner3 = findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        button13 = findViewById(R.id.button13);
        button15 = findViewById(R.id.button15);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                System.out.println("perm granted");
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {

        }

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
        imageView4.setImageResource(R.drawable.technocratv2);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = spinner3.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void ImgSelect(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                System.out.println(filePath);
                Bitmap img = null;
                try {
                    img = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                if (img != null) {
                    imageView4.setImageBitmap(img);
                    String filename = FilenameUtils.getName(String.valueOf(filePath));
                    System.out.println(filename);
                    imageView4.setTag(filePath.toString());
                    imageView5.setImageDrawable(imageView4.getDrawable());

                }
            }
        }

    }
}