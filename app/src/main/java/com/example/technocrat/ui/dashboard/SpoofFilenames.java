package com.example.technocrat.ui.dashboard;

import static android.view.View.GONE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.technocrat.R;
import com.example.technocrat.ui.settings.ThemeSettings;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
import java.util.UUID;
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

    EditText editText3;

    Button button8;

    Button button10;

    Button button11;

    Button button14;

    ImageView imageView;

    ImageView imageView2;

    CheckBox checkBox1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoof_filenames);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                System.out.println("perm granted");
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {

        }


        constraintLayout = findViewById(R.id.cl);
        actionBar = getSupportActionBar();
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);
        editText3 = findViewById(R.id.editTextText3);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        button8 = findViewById(R.id.button8);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button14 = findViewById(R.id.button14);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        spinner2.setSelection(0, true);
        checkBox1 = findViewById(R.id.checkBox);
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
        imageView.setImageResource(R.drawable.technocratv1);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = spinner.getSelectedItem().toString();
        if (selected.equals("Fake iPhone Camera Filename")) {
            textView.setText("Example: IMG_0001");
            button8.setVisibility(View.VISIBLE);
            button10.setVisibility(GONE);
            button11.setVisibility(GONE);
            button14.setVisibility(GONE);
            textView2.setVisibility(GONE);
            textView3.setVisibility(GONE);
            spinner2.setVisibility(GONE);
            editText.setVisibility(View.VISIBLE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(GONE);
            checkBox1.setVisibility(GONE);
        } else if (selected.equals("Fake iPhone Screenshot Filename")) {
            textView.setText("Screenshot 2022-12-22 at 2.12.12 pm");
            button8.setVisibility(GONE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            editText.setVisibility(GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(GONE);
            checkBox1.setVisibility(GONE);
        } else if (selected.equals("Fake Android Camera Filename")) {
            textView.setText("IMG_20230302_114348");
            button8.setVisibility(View.VISIBLE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(GONE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            editText.setVisibility(GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(GONE);
            checkBox1.setVisibility(GONE);
        } else if (selected.equals("Fake Android Screenshot Filename")) {
            textView.setText("Screenshot_2023-05-10-22-53-11-134");
            button8.setVisibility(GONE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            editText.setVisibility(GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(GONE);
            checkBox1.setVisibility(GONE);
        } else if (selected.equals("Fake UUID v1 JPG")) {
            button8.setVisibility(View.VISIBLE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(GONE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            textView.setText("37E9316E-E637-11ED-A05B-0242AC120003");
            editText.setVisibility(View.GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(View.VISIBLE);
            checkBox1.setVisibility(View.VISIBLE);
        } else if (selected.equals("Fake UUID v1 PNG")) {
            button8.setVisibility(GONE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            textView.setText("37E9316E-E637-11ED-A05B-0242AC120003");
            editText.setVisibility(View.GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(View.VISIBLE);
            checkBox1.setVisibility(View.VISIBLE);
        } else if (selected.equals("Fake Epoch Timestamp JPG")) {
            textView.setText("1681935600");
            button8.setVisibility(View.VISIBLE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(GONE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            editText.setVisibility(GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(GONE);
            checkBox1.setVisibility(GONE);
        } else if (selected.equals("Fake Epoch Timestamp PNG")) {
            textView.setText("1681935600");
            button8.setVisibility(GONE);
            button10.setVisibility(View.VISIBLE);
            button11.setVisibility(View.VISIBLE);
            button14.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            editText.setVisibility(GONE);
            editText2.setVisibility(GONE);
            editText3.setVisibility(GONE);
            checkBox1.setVisibility(GONE);
        }
        String selectedseconds = spinner2.getSelectedItem().toString();

        if (textView3.getText().equals("Time")) {
        } else {
            String pickedtime = textView3.getText().toString().substring(0, textView3.length() - 2) + selectedseconds;
            textView3.setText(pickedtime);
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void setDate(View v) {
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

                        monthOfYear += 1;
                        if (monthOfYear <= 9) {
                            String stringmonth = 0 + String.valueOf(monthOfYear);
                            if (dayOfMonth <= 9) {
                                String stringday = 0 + String.valueOf(dayOfMonth);
                                String chosendate = stringyear + stringmonth + stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            } else {
                                String stringday = String.valueOf(dayOfMonth);
                                String chosendate = stringyear + stringmonth + stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            }
                        } else {
                            String stringmonth = String.valueOf(monthOfYear);
                            if (dayOfMonth <= 9) {
                                String stringday = 0 + String.valueOf(dayOfMonth);
                                String chosendate = stringyear + stringmonth + stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            } else {
                                String stringday = String.valueOf(dayOfMonth);
                                String chosendate = stringyear + stringmonth + stringday;
                                textView2.setText(chosendate);
                                System.out.println(chosendate);
                            }
                        }

                    }
                },
                year, month, day);
        datePickerDialog.show();
        Toast.makeText(getApplicationContext(), "Reset to random by tapping text on right", Toast.LENGTH_SHORT).show();
    }

    public void setTime1(View v) {
        final Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(SpoofFilenames.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if (hourOfDay <= 9) {
                            String stringhour = 0 + String.valueOf(hourOfDay);
                            String stringminute = String.valueOf(minute);
                            String stringsecond = "00";
                            textView3.setText(stringhour + stringminute + stringsecond);
                            if (minute <= 9) {
                                stringminute = 0 + String.valueOf(minute);
                                textView3.setText(stringhour + stringminute + stringsecond);
                            }
                        } else {
                            String stringhour = String.valueOf(hourOfDay);
                            String stringminute = String.valueOf(minute);
                            String stringsecond = "00";
                            textView3.setText(stringhour + stringminute + stringsecond);
                            if (minute <= 9) {
                                stringminute = 0 + String.valueOf(minute);
                                textView3.setText(stringhour + stringminute + stringsecond);
                            }
                        }

                    }
                }, hour, minute, false);

        timePickerDialog.show();
        Toast.makeText(getApplicationContext(), "Reset to random by tapping text on right", Toast.LENGTH_SHORT).show();
    }


    //region Generates JPG files to spoof camera images
    public void Generate(View v) throws IOException, ParseException {

        Pattern p = Pattern.compile("\\d{8}");
        Pattern p2 = Pattern.compile("\\d{6}");
        Pattern p3 = Pattern.compile("\\d{4}");
        Pattern p4 = Pattern.compile("[\\d\\w]{12}");
        Matcher m = p.matcher(textView2.getText().toString());
        Matcher m2 = p2.matcher(textView3.getText().toString());
        Matcher m3 = p3.matcher(editText.getText().toString());
        Matcher m4 = p4.matcher(editText3.getText().toString());
        if (imageView.getDrawable() == null){
            Toast.makeText(getApplicationContext(), "No image set", Toast.LENGTH_SHORT).show();
        } else if (spinner.getSelectedItem().toString().equals("Fake Android Camera Filename") && m.matches() && m2.matches()) {
            String androidimgname = "IMG_" + textView2.getText().toString() + "_" + textView3.getText().toString();

            saveJPG(androidimgname);

        } else if (spinner.getSelectedItem().toString().equals("Fake Android Camera Filename") && m.matches()) {
            //24h
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter);
            System.out.println(formatted24h);

            String androidimgname = "IMG_" + textView2.getText().toString() + "_" + formatted24h;

            saveJPG(androidimgname);

        } else if (spinner.getSelectedItem().toString().equals("Fake Android Camera Filename") && m2.matches()) {
            //unix time reference
            Random random = new Random();
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            String androidimgname = "IMG_" + date + "_" + textView3.getText().toString();

            saveJPG(androidimgname);

        } else if (spinner.getSelectedItem().toString().equals("Fake Android Camera Filename")) {
            //unix time reference
            Random random = new Random();
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            //24h
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter);
            System.out.println(formatted24h);

            String androidimgname = "IMG_" + date + "_" + formatted24h;

            saveJPG(androidimgname);

        } else if (spinner.getSelectedItem().toString().equals("Fake iPhone Camera Filename") && m3.matches()) {
            String iphoneimgname = "IMG_" + editText.getText();
            imageView2.buildDrawingCache();
            Bitmap draw = (Bitmap) imageView2.getDrawingCache();
            FileOutputStream outStream = null;
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            System.out.println(iphoneimgname);
            File outFile = new File(directory + "/Technocrat");
            File outFile2 = new File(directory, iphoneimgname + ".jpg");
            if (!outFile2.exists()) {
                System.out.println(outFile);
                outStream = new FileOutputStream(outFile2);
                draw.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                outStream.close();
                Toast.makeText(getApplicationContext(), "File Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "File already exists with this name", Toast.LENGTH_SHORT).show();
            }
        } else if (spinner.getSelectedItem().toString().equals("Fake iPhone Camera Filename")) {
            Random random = new Random();
            int randomiphone = random.nextInt(10000 - 1) + 1;

            String iphoneimgname = "IMG_" + randomiphone;

            saveJPG(iphoneimgname);

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m4.matches() && m2.matches() && m.matches()){
            //MAC Address, date, time provided
            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16) & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m4.matches() && m.matches()){
            //Mac Address and date, no time provided
            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16);
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((formatted24h.substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2,4)))) * 60) + Integer.parseInt(formatted24h.substring(4,6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m4.matches() && m2.matches()){
            //MAC Address, date, time provided
            Random random = new Random();
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16) & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(date2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m2.matches() && m.matches()){
            // Time and date provided, no MAC Address
            Random random = new Random();
            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m2.matches()){
            //Time provided, no date or MAC Address
            Random random = new Random();
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(date2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m.matches()){
            //date provided, no time or MAC Address
            Random random = new Random();
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((formatted24h.substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2,4)))) * 60) + Integer.parseInt(formatted24h.substring(4,6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG") && m4.matches()){
            //MAC Address provided, no time or date
            Random random = new Random();
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16) & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(date2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((formatted24h.substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2,4)))) * 60) + Integer.parseInt(formatted24h.substring(4,6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 JPG")) {
            //Nothing provided
            Random random = new Random();
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(date2);
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((formatted24h.substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2, 4)))) * 60) + Integer.parseInt(formatted24h.substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits = chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();

            if(checkBox1.isChecked()){
                saveJPG(uppercaseUUID);
            } else {
                saveJPG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp JPG") && m.matches() && m2.matches()) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            String epochstr = Long.toString(epoch);

            saveJPG(epochstr);

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp JPG") && m.matches()) {
            Random random = new Random();
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(textView2.getText().toString());
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((formatted24h.substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2, 4)))) * 60) + Integer.parseInt(formatted24h.substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);

            saveJPG(epochstr);

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp JPG") && m2.matches()) {
            Random random = new Random();
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(date2);
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2, 4)))) * 60) + Integer.parseInt(textView3.getText().toString().substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);

            saveJPG(epochstr);

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp JPG")) {
            Random random = new Random();
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(date2);
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((formatted24h.substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2, 4)))) * 60) + Integer.parseInt(formatted24h.substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);

            saveJPG(epochstr);

        }
    }
    //endregion

    //region Generates PNG to spoof screenshots
    public void Generate2(View v) throws IOException, ParseException {

        Pattern p = Pattern.compile("\\d{8}");
        Pattern p2 = Pattern.compile("\\d{6}");
        Pattern p3 = Pattern.compile("\\d{4}");
        Pattern p4 = Pattern.compile("[\\d\\w]{12}");
        Matcher m = p.matcher(textView2.getText().toString());
        Matcher m2 = p2.matcher(textView3.getText().toString());
        Matcher m3 = p3.matcher(editText.getText().toString());
        Matcher m4 = p4.matcher(editText3.getText().toString());
        Random random = new Random();
        if (imageView.getDrawable() == null){
            Toast.makeText(getApplicationContext(), "No image set", Toast.LENGTH_LONG).show();
        } else if (spinner.getSelectedItem().toString().equals("Fake Android Screenshot Filename") && m.matches() && m2.matches()) {
            int randommillis = random.nextInt(1000 - 1) + 1;
            if (randommillis <= 99) {
                String randommillistring = 0 + String.valueOf(randommillis);
                String androidimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8) + "-"
                        + textView3.getText().toString().substring(0, 2) + "-" + textView3.getText().toString().substring(2, 4) + "-"
                        + textView3.getText().toString().substring(4, 6) + "-" + randommillistring;

                savePNG(androidimgname);

            } else {
                String randommillistring = String.valueOf(randommillis);
                String androidimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8) + "-"
                        + textView3.getText().toString().substring(0, 2) + "-" + textView3.getText().toString().substring(2, 4) + "-"
                        + textView3.getText().toString().substring(4, 6) + "-" + randommillistring;

                savePNG(androidimgname);

            }

        } else if (spinner.getSelectedItem().toString().equals("Fake Android Screenshot Filename") && m.matches()) {
            int randommillis = random.nextInt(1000 - 1) + 1;
            //24h
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter);
            System.out.println(formatted24h);

            if (randommillis <= 99) {
                String randommillistring = 0 + String.valueOf(randommillis);
                String androidimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8) + "-"
                        + formatted24h.substring(0, 2) + "-" + formatted24h.substring(2, 4) + "-"
                        + formatted24h.substring(4, 6) + "-" + randommillistring;

                savePNG(androidimgname);

            } else {
                String androidimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8) + "-"
                        + formatted24h.substring(0, 2) + "-" + formatted24h.substring(2, 4) + "-"
                        + formatted24h.substring(4, 6) + "-" + randommillis;

                savePNG(androidimgname);

            }
        } else if (spinner.getSelectedItem().toString().equals("Fake Android Screenshot Filename") && m2.matches()) {
            int randommillis = random.nextInt(1000 - 1) + 1;

            //unix time reference
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            if (randommillis <= 99) {
                String randommillistring = 0 + String.valueOf(randommillis);
                String androidimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8) + "-"
                        + textView3.getText().toString().substring(0, 2) + "-" + textView3.getText().toString().substring(2, 4) + "-"
                        + textView3.getText().toString().substring(4, 6) + "-" + randommillistring;

                savePNG(androidimgname);

            } else {
                String androidimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8) + "-"
                        + textView3.getText().toString().substring(0, 2) + "-" + textView3.getText().toString().substring(2, 4) + "-"
                        + textView3.getText().toString().substring(4, 6) + "-" + randommillis;

                savePNG(androidimgname);

            }
        } else if (spinner.getSelectedItem().toString().equals("Fake Android Screenshot Filename")) {
            //unix time reference
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            //24h
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter);
            System.out.println(formatted24h);

            int randommillis = random.nextInt(1000 - 1) + 1;

            if (randommillis <= 99) {
                String randommillistring = 0 + String.valueOf(randommillis);
                String androidimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8) + "-"
                        + formatted24h.substring(0, 2) + "-" + formatted24h.substring(2, 4) + "-"
                        + formatted24h.substring(4, 6) + "-" + randommillistring;

                savePNG(androidimgname);

            } else {
                String androidimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8) + "-"
                        + formatted24h.substring(0, 2) + "-" + formatted24h.substring(2, 4) + "-"
                        + formatted24h.substring(4, 6) + "-" + randommillis;

                savePNG(androidimgname);

            }
        } else if (spinner.getSelectedItem().toString().equals("Fake iPhone Screenshot Filename") && m.matches() && m2.matches()) {
            int hours = Integer.parseInt(textView3.getText().toString().substring(0, 2));
            if (hours <= 12) {
                String iphoneimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8)
                        + " at " + hours + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " am";

                savePNG(iphoneimgname);

            }  else if (hours == 0){
                String iphoneimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8)
                        + " at " + 12 + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " am";

                savePNG(iphoneimgname);

            } else {
                int twelvehours = hours - 12;
                String iphoneimgname = "Screenshot_" + textView2.getText().toString().substring(0, 4) + "-" +
                        textView2.getText().toString().substring(4, 6) + "-" + textView2.getText().toString().substring(6, 8)
                        + " at " + twelvehours + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " pm";

                savePNG(iphoneimgname);

            }
        } else if (spinner.getSelectedItem().toString().equals("Fake iPhone Screenshot Filename") && m2.matches()) {
            int hours = Integer.parseInt(textView3.getText().toString().substring(0, 2));

            //unix time reference
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            if (hours <= 12) {
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + hours + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " am";

                savePNG(iphoneimgname);

            }  else if (hours == 0){
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + 12 + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " am";

                savePNG(iphoneimgname);

            } else {
                int twelvehours = hours - 12;
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + twelvehours + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " pm";

                savePNG(iphoneimgname);

            }
        } else if (spinner.getSelectedItem().toString().equals("Fake iPhone Screenshot Filename") && m.matches()) {
            int hours = Integer.parseInt(textView3.getText().toString().substring(0, 2));

            //unix time reference
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            if (hours <= 12) {
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + hours + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " am";

                savePNG(iphoneimgname);

            } else if (hours == 0){
            String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                    date.substring(4, 6) + "-" + date.substring(6, 8)
                    + " at " + 12 + "." + textView3.getText().toString().substring(2, 4) + "."
                    + textView3.getText().toString().substring(4, 6) + " am";

                savePNG(iphoneimgname);

            } else {
                int twelvehours = hours - 12;
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + twelvehours + "." + textView3.getText().toString().substring(2, 4) + "."
                        + textView3.getText().toString().substring(4, 6) + " pm";
            }
        } else if (spinner.getSelectedItem().toString().equals("Fake iPhone Screenshot Filename")) {

            //unix time reference
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date = DateFormat.format("yyyyMMdd", cal).toString();
            System.out.println(date);

            //24h reference
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter);
            System.out.println(formatted24h);

            int hours = Integer.parseInt(formatted24h.substring(0, 2));

            if (hours < 12 && hours > 0) {
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + hours + "." + formatted24h.substring(2, 4) + "."
                        + formatted24h.substring(4, 6) + " am";

                savePNG(iphoneimgname);

            } else if (hours == 0){
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + 12 + "." + formatted24h.substring(2, 4) + "."
                        + formatted24h.substring(4, 6) + " am";

                savePNG(iphoneimgname);

            } else {
                int twelvehours = hours - 12;
                String iphoneimgname = "Screenshot_" + date.substring(0, 4) + "-" +
                        date.substring(4, 6) + "-" + date.substring(6, 8)
                        + " at " + twelvehours + "." + formatted24h.substring(2, 4) + "."
                        + formatted24h.substring(4, 6) + " pm";

                savePNG(iphoneimgname);
            }
        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m4.matches() && m2.matches() && m.matches()){
            //MAC Address, date, time provided
            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16) & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }




        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m4.matches() && m.matches()){
            //Mac Address and date, no time provided
            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16);
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((formatted24h.substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2,4)))) * 60) + Integer.parseInt(formatted24h.substring(4,6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m4.matches() && m2.matches()){
            //MAC Address, date, time provided
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16) & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(date2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m2.matches() && m.matches()){
            // Time and date provided, no MAC Address
            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m2.matches()){
            //Time provided, no date or MAC Address
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(date2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m.matches()){
            //date provided, no time or MAC Address
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((formatted24h.substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2,4)))) * 60) + Integer.parseInt(formatted24h.substring(4,6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG") && m4.matches()){
            //MAC Address provided, no time or date
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = Long.parseLong(editText3.getText().toString(), 16) & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(date2);
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((formatted24h.substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2,4)))) * 60) + Integer.parseInt(formatted24h.substring(4,6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits =  chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake UUID v1 PNG")) {
            //Nothing provided
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            long chosen63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
            long variant3BitFlag = 0x8000000000000000L;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(date2);
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((formatted24h.substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2, 4)))) * 60) + Integer.parseInt(formatted24h.substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);
            long androidepoch = Long.parseLong(epochstr) * 1000;
            final long selectedTimeMillis = androidepoch;
            final long time_low = (selectedTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
            final long time_mid = ((selectedTimeMillis >> 32) & 0xFFFF) << 16;
            final long version = 1 << 12;
            final long time_hi = ((selectedTimeMillis >> 48) & 0x0FFF);
            long least64SigBits = chosen63BitLong | variant3BitFlag;
            long most64SigBits = time_low | time_mid | version | time_hi;
            UUID fakeUUID = new UUID(most64SigBits, least64SigBits);
            String uppercaseUUID = fakeUUID.toString().toUpperCase();
            System.out.println(fakeUUID);

            if(checkBox1.isChecked()){
                savePNG(uppercaseUUID);
            } else {
                savePNG(uppercaseUUID.replaceAll("-", ""));
            }

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp PNG") && m.matches() && m2.matches()) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date)formatter.parse(textView2.getText().toString());
            long epoch=(date.getTime()/1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0,2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2,4)))) * 60) + Integer.parseInt(spinner2.getSelectedItem().toString());
            String epochstr = Long.toString(epoch);

            savePNG(epochstr);

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp PNG") && m.matches()) {
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(textView2.getText().toString());
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((formatted24h.substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2, 4)))) * 60) + Integer.parseInt(formatted24h.substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);

            savePNG(epochstr);

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp PNG") && m2.matches()) {
            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(date2);
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((textView3.getText().toString().substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((textView3.getText().toString().substring(2, 4)))) * 60) + Integer.parseInt(textView3.getText().toString().substring(4, 6));
            System.out.println(epoch);
            String epochstr = Long.toString(epoch);

            savePNG(epochstr);

        } else if (spinner.getSelectedItem().toString().equals("Fake Epoch Timestamp PNG")) {
            LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmmss");
            String formatted24h = random24h.format(formatter2);

            long currentunixTime = System.currentTimeMillis() / 1000L;
            long minunixTime = 1451577600;
            long unixtimediff = currentunixTime - minunixTime;
            int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(randomunix * 1000L);
            String date2 = DateFormat.format("yyyyMMdd", cal).toString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = (Date) formatter.parse(date2);
            long epoch = (date.getTime() / 1000L) + ((Integer.parseInt((formatted24h.substring(0, 2)))) * 60 * 60) +
                    ((Integer.parseInt((formatted24h.substring(2, 4)))) * 60) + Integer.parseInt(formatted24h.substring(4, 6));
            System.out.println(epoch);

            String epochstr = Long.toString(epoch);

            savePNG(epochstr);


        }

/*      //unix time reference
        long currentunixTime = System.currentTimeMillis() / 1000L;
        long minunixTime = 1451577600;
        long unixtimediff = currentunixTime - minunixTime;
        int randomunix = random.nextInt(Math.toIntExact(unixtimediff)) + Math.toIntExact(minunixTime);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(randomunix * 1000L);
        String date = DateFormat.format("yyyyMMdd", cal).toString();
        System.out.println(date);

       //24h reference
        LocalDateTime random24h = LocalDateTime.now().minusHours(new Random().nextInt(24)).minusMinutes(new Random().nextInt(60)).minusSeconds(new Random().nextInt(60));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formatted24h = random24h.format(formatter);
        System.out.println(formatted24h);

        //android date_time
        String androidimgname = "IMG_" + date + "_" + formatted24h;
        System.out.println(androidimgname);

        //iphone 0001 to 9999
        int randomiphone = random.nextInt(10000 - 1) + 1;*/


    }
    //endregion


    private Context context;


    public void ImgSelect(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    public void resetDate(View v) {
        textView2.setText("Date");
    }

    public void resetTime(View v) {
        textView3.setText("Time");
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
                    imageView.setImageBitmap(img);
                    String filename = FilenameUtils.getName(String.valueOf(filePath));
                    System.out.println(filename);
                    imageView.setTag(filePath.toString());
                    imageView2.setImageDrawable(imageView.getDrawable());

                }
            }
        }

    }

    public void saveJPG(String filename) throws IOException {
        imageView2.buildDrawingCache();
        Bitmap draw = (Bitmap) imageView2.getDrawingCache();
        FileOutputStream outStream = null;
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        System.out.println(directory);

        File outFile = new File(directory + "/Technocrat");
        File outFile2 = new File(directory, filename + ".jpg");
        if (imageView2.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "No image set", Toast.LENGTH_SHORT).show();
        } else if (!outFile2.exists()) {
            System.out.println(outFile);
            outStream = new FileOutputStream(outFile2);
            draw.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.close();
            Toast.makeText(getApplicationContext(), "File Created", Toast.LENGTH_SHORT).show();
            textView.setText(filename);
        } else {
            Toast.makeText(getApplicationContext(), "File already exists with this name, please retry", Toast.LENGTH_SHORT).show();
        }
    }

    public void savePNG(String filename) throws IOException {
        imageView2.buildDrawingCache();
        Bitmap draw = (Bitmap) imageView2.getDrawingCache();
        FileOutputStream outStream = null;
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        System.out.println(directory);

        File outFile = new File(directory + "/Technocrat");
        File outFile2 = new File(directory, filename + ".png");
        if (imageView2.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "No image set", Toast.LENGTH_SHORT).show();
        } else if (!outFile2.exists()) {
            System.out.println(outFile);
            outStream = new FileOutputStream(outFile2);
            draw.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();
            Toast.makeText(getApplicationContext(), "File Created", Toast.LENGTH_SHORT).show();
            textView.setText(filename.toString());
        } else {
            Toast.makeText(getApplicationContext(), "File already exists with this name, please retry", Toast.LENGTH_SHORT).show();
        }
    }

    public void openHelp (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Heads up").setMessage("Greetings! \n\nScreenshots are saved as .png and camera images are saved as .jpg. \n\n" +
                "Hit the logo right above the bottom-most button to select an image. \n\n" +
                "All photos are re-saved into the photos directory and all exif data should be gone. \n\n" +
                "Faked UUIDs are in all caps - yes I know its a violation of RFC but the point is to blend in with images downloaded from websites. The hyphenated checkbox is to provide flexibility since some websites create UUIDs without hyphens. \n\n" +
                "Epoch timestamps are unix timestamps which should be self explanatory. \n\n" +
                "If fields do not have user input it will be randomly generated. \n\n" +
                "Where date and time can be selected hitting the text to the right of the buttons resets it to random. \n\n" +
                "This isn't meant to be used with duplicitous intent (although you could), but it's meant to preserve privacy and anonymity. Many " +
                "people upload their screenshots or images not knowing date, time, EXIF and even mac address can be derived through the file that is uploaded."
        );

        AlertDialog alert = builder.create();
        alert.show();
    }
// Java KVM more like Java KMS
}
