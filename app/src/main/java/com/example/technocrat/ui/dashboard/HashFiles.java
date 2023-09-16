package com.example.technocrat.ui.dashboard;

import static android.view.View.GONE;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.technocrat.R;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFiles extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    int theme = 0;
    int buttonTheme = 0;

    ConstraintLayout constraintLayout;
    ActionBar actionBar;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    EditText editText4;
    Button button17;
    Button button18;
    Button button19;
    ImageView imageView6;
    ImageView imageView7;
    Spinner spinner4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_files);

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

        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        editText4 = findViewById(R.id.editTextText4);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        spinner4 = findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        spinner4.setSelection(0, true);


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
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static final int PICK_FILE = 2;

    public void FileSelect(View v) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE);
    }

    public void Hash (View V) throws FileNotFoundException {
        InputStream is = getContentResolver().openInputStream(Uri.parse(textView4.getText().toString()));
        String selected = spinner4.getSelectedItem().toString();
        MessageDigest digest = null;
        if (selected.equals("MD5")) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                Log.e("calculateMD5", "Exception while getting Digest", e);
                System.out.println("a");
            }
            byte[] buffer = new byte[8192];
            int read;

            try {
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                String output = bigInt.toString(16);
                output = String.format("%32s", output).replace(' ', '0');
                System.out.println(output);
                editText4.setText(output);
            } catch (IOException e) {
                throw new RuntimeException("Unable to process file for MD5", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("calculateMD5", "Exception on closing MD5 input stream", e);
                }
            }
        } else if (selected.equals("SHA-1")){
            try {
                digest = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                Log.e("calculateSHA-1", "Exception while getting Digest", e);
                System.out.println("a");
            }
            byte[] buffer = new byte[8192];
            int read;

            try {
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                String output = bigInt.toString(16);
                output = String.format("%32s", output).replace(' ', '0');
                System.out.println(output);
                editText4.setText(output);
            } catch (IOException e) {
                throw new RuntimeException("Unable to process file for SHA-1", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("calculateSHA-1", "Exception on closing SHA-1 input stream", e);
                }
            }
        } else if (selected.equals("SHA-224")){
            try {
                digest = MessageDigest.getInstance("SHA-224");
            } catch (NoSuchAlgorithmException e) {
                Log.e("calculateSHA-224", "Exception while getting Digest", e);
                System.out.println("a");
            }
            byte[] buffer = new byte[8192];
            int read;

            try {
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                String output = bigInt.toString(16);
                output = String.format("%32s", output).replace(' ', '0');
                System.out.println(output);
                editText4.setText(output);
            } catch (IOException e) {
                throw new RuntimeException("Unable to process file for SHA-224", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("calculateSHA-224", "Exception on closing SHA-224 input stream", e);
                }
            }
        } else if (selected.equals("SHA-256")){
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                Log.e("calculateSHA-256", "Exception while getting Digest", e);
                System.out.println("a");
            }
            byte[] buffer = new byte[8192];
            int read;

            try {
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                String output = bigInt.toString(16);
                output = String.format("%32s", output).replace(' ', '0');
                System.out.println(output);
                editText4.setText(output);
            } catch (IOException e) {
                throw new RuntimeException("Unable to process file for SHA-256", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("calculateSHA-256", "Exception on closing SHA-256 input stream", e);
                }
            }
        } else if (selected.equals("SHA-384")){
            try {
                digest = MessageDigest.getInstance("SHA-384");
            } catch (NoSuchAlgorithmException e) {
                Log.e("calculateSHA-384", "Exception while getting Digest", e);
                System.out.println("a");
            }
            byte[] buffer = new byte[8192];
            int read;

            try {
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                String output = bigInt.toString(16);
                output = String.format("%32s", output).replace(' ', '0');
                System.out.println(output);
                editText4.setText(output);
            } catch (IOException e) {
                throw new RuntimeException("Unable to process file for SHA-384", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("calculateSHA-384", "Exception on closing SHA-384 input stream", e);
                }
            }
        } else if (selected.equals("SHA-512")){
            try {
                digest = MessageDigest.getInstance("SHA-512");
            } catch (NoSuchAlgorithmException e) {
                Log.e("calculateSHA-512", "Exception while getting Digest", e);
                System.out.println("a");
            }
            byte[] buffer = new byte[8192];
            int read;

            try {
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
                byte[] md5sum = digest.digest();
                BigInteger bigInt = new BigInteger(1, md5sum);
                String output = bigInt.toString(16);
                output = String.format("%32s", output).replace(' ', '0');
                System.out.println(output);
                editText4.setText(output);
            } catch (IOException e) {
                throw new RuntimeException("Unable to process file for SHA-512", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e("calculateSHA-512", "Exception on closing SHA-512 input stream", e);
                }
            }
        }
    }

    public void copy(View V) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(editText4.getText());
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData
                    .newPlainText("Copied Hash", editText4.getText());
            clipboard.setPrimaryClip(clip);
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                "Hash copied!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @SuppressLint("Range")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE) {
            if (requestCode == PICK_FILE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                String filepath2 = filePath.getPath();
                System.out.println(filepath2);
                textView4.setText(String.valueOf(filePath));
                textView5.setText(String.valueOf(filepath2));
                Cursor c = getContentResolver().query(filePath, null, null, null, null);
                c.moveToFirst();
                System.out.println(c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME)));
                textView6.setText(c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME)));
                String extension = c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME)).substring(c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME)).lastIndexOf("."));
                System.out.println(extension);
                if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png") || extension.equals(".bmp") || extension.equals(".gif")
                 || extension.equals(".webp") || extension.equals(".heic") || extension.equals(".heif")){
                    Bitmap img = null;
                    try {
                        img = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if (img != null) {
                        imageView6.setImageBitmap(img);
                    }
                }
                }
            }
        }

    public void openHelp2 (View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Heads up").setMessage("Greetings! \n\nScreenshots are saved as .png and camera images are saved as .jpg. \n\n" +
                "Pick a file, hashing algo and hit hash to hash the file. \n\n" +
                "SHA-1 should return a 40 character string. Some websites use 41 characters which would be the same thing with a 0 in front. \n\n"
        );
        AlertDialog alert = builder.create();
        alert.show();
    }
}