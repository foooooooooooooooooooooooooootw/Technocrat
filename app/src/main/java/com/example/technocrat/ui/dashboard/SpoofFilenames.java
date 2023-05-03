package com.example.technocrat.ui.dashboard;

import static android.view.View.GONE;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.technocrat.R;
import com.example.technocrat.ui.settings.ThemeSettings;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpoofFilenames extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int theme = 0;
    int buttonTheme = 0;

    ConstraintLayout constraintLayout;
    ActionBar actionBar;

    Spinner spinner;

    Spinner spinner2;

    TextView textView;

    TextView textView2;

    TextView textView3;

    EditText editText;

    EditText editText2;

    Button button10;

    Button button11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoof_filenames);

        constraintLayout = findViewById(R.id.cl);
        actionBar = getSupportActionBar();
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
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
            button10.setVisibility(GONE);
            button11.setVisibility(GONE);
            textView2.setVisibility(GONE);
            textView3.setVisibility(GONE);
            spinner2.setVisibility(GONE);
            editText.setVisibility(View.VISIBLE);
            editText.setText("If empty number is random");
            editText2.setVisibility(GONE);
        } else if(selected.equals("Fake Android Camera Filename")){
            textView.setText("IMG_20230302_114348");
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            editText.setVisibility(GONE);
            editText2.setVisibility(GONE);
        } else if(selected.equals("Fake UUID v1")){
            button10.setVisibility(GONE);
            button11.setVisibility(GONE);
            textView2.setVisibility(GONE);
            textView3.setVisibility(GONE);
            spinner2.setVisibility(GONE);
            textView.setText("37e9316e-e637-11ed-a05b-0242ac120003");
            editText.setVisibility(View.VISIBLE);
            editText.setText("If empty MAC address is random");
            editText2.setVisibility(View.VISIBLE);
            editText2.setText("If empty date is random");
        } else if(selected.equals("Fake UUID v4")){
            button10.setVisibility(GONE);
            button11.setVisibility(GONE);
            textView2.setVisibility(GONE);
            textView3.setVisibility(GONE);
            spinner2.setVisibility(GONE);
            textView.setText("c453eab1-ad34-4e8e-8590-8a63ed030f8a");
            editText.setText("If empty seed is random");
            editText2.setText("Not used for this option");
        }
        String selectedseconds = spinner2.getSelectedItem().toString();
        String pickedtime = textView3.getText().toString().substring(0,textView3.length()-2) + selectedseconds;
        textView3.setText(pickedtime);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setDate (View v){
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(

                SpoofFilenames.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String stringyear = String.valueOf(year);

                        monthOfYear+=1;
                        if(monthOfYear<=9){
                            String stringmonth = 0+String.valueOf(monthOfYear);
                            if (dayOfMonth<=9){
                                String stringday = 0+String.valueOf(dayOfMonth);
                                String chosendate = stringyear+stringmonth+stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            } else {
                                String stringday = String.valueOf(dayOfMonth);
                                String chosendate = stringyear+stringmonth+stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            }
                        } else {
                            String stringmonth = String.valueOf(monthOfYear);
                            if (dayOfMonth<=9){
                                String stringday = 0+String.valueOf(dayOfMonth);
                                String chosendate = stringyear+stringmonth+stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            } else {
                                String stringday = String.valueOf(dayOfMonth);
                                String chosendate = stringyear+stringmonth+stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            }
                        }

                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void setTime1 (View v){
        final Calendar c = Calendar.getInstance();

        // on below line we are getting our hour, minute.
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // on below line we are initializing our Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(SpoofFilenames.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // on below line we are setting selected time
                        // in our text view.
                        String stringhour = String.valueOf(hourOfDay);
                        String stringminute = String.valueOf(minute);
                        String stringsecond = "00";
                        textView3.setText(stringhour + stringminute + stringsecond);
                    }
                }, hour, minute, false);
        // at last we are calling show to
        // display our time picker dialog.
        timePickerDialog.show();
    }


    public void Generate (View v){

        Pattern p = Pattern.compile("\\d{8}");
        Pattern p2 = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(textView2.getText().toString());
        Matcher m2 = p2.matcher(textView3.getText().toString());
        if (m.matches() && m2.matches()) {
            String androidimgname = "IMG_" + textView2.getText().toString() + "_" + textView3.getText().toString();
        }
        //iphone 0001 to 9999
        Random random = new Random();
        int randomiphone = random.nextInt(10000 - 1) + 1;
        System.out.println(randomiphone);

        //unix time
        long currentunixTime = System.currentTimeMillis()/1000L;
        long minunixTime = 1451577600;
        long unixtimediff = currentunixTime - minunixTime;
        int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(randomunix*1000L);
        String date = DateFormat.format("yyyyMMdd", cal).toString();
        System.out.println(date);

        //24h
        LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formatted24h = random24h.format(formatter);
        System.out.println(formatted24h);

        //android date_time
        String androidimgname = "IMG_" + date + "_" + formatted24h;
        System.out.println(androidimgname);


    }
}
