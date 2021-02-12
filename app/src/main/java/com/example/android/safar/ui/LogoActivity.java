package com.example.android.safar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;

public class LogoActivity extends AppCompatActivity {

    public final static String TAG = "LogoActivity";

    private ImageView planAroundEarthImage;
    private Animation planeAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        planAroundEarthImage = findViewById(R.id.airplane_image_view);
        planeAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        planAroundEarthImage.setAnimation(planeAnimation);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000); // 4 seconds
                    startActivity(new Intent(LogoActivity.this, MainActivity.class));
                } catch (InterruptedException e) {
                    Log.e(TAG, "InterruptedException in run() -> " + e.getMessage());
                }
            }
        };
        thread.start();

    }


    public void cancelAnim(View view) {
        Toast.makeText(this, "Cancelling Animation", Toast.LENGTH_LONG).show();
        planeAnimation.cancel();
    }
}











