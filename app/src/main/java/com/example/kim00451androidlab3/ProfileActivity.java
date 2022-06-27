package com.example.kim00451androidlab3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ProfileActivity extends AppCompatActivity {

    public final String TAG = "PROFILE_ACTIVITY";
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    ActivityResultLauncher<Intent> myPictureTakerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tv = findViewById(R.id.textEnter);

        Intent intent= getIntent();
        String value = intent.getStringExtra("Email");

        tv.setText(value);

        final ImageButton button = (ImageButton) findViewById(R.id.imageButton2);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    myPictureTakerLauncher.launch(takePictureIntent);
                }

            }
        });
        ImageView imgView = new ImageView(this);

        myPictureTakerLauncher =
                registerForActivityResult( new ActivityResultContracts.StartActivityForResult()
                        , new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result){

                                if (result.getResultCode() == Activity.RESULT_OK)
                                { Intent data = result.getData();
                                    Bitmap imgbitmap = (Bitmap)data.getExtras().get("data");
                                    imgView.setImageBitmap(imgbitmap); // the imageButton
                                }

                                else if(result.getResultCode() == Activity.RESULT_CANCELED)
                                    Log.i(TAG, "User refused to capture a picture.");
                            }
                        });





    }
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "In the onStart() event");
    }
    public void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "In the onRestart() event");
    }
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "In the onResume() event");
    }
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "In the onPause() event");
    }
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "In the onStop() event");
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "In the onDestroy() event");
    }




}