package com.example.breathlyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        CountDownTimer cd = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                Animatoo.animateFade(SplashScreen.this);
                finish();
                return;
            }
        }.start();


    }
}
