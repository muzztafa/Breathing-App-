package com.example.breathlyjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompletedActivity extends AppCompatActivity{
    ImageView back;
    ImageView bg1;
    ImageView bg2;
    TextView text;
    TextView textbox;
    ImageView logo;
    ConstraintLayout container;
    String mode;

    ImageView start_breathing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        back = findViewById(R.id.back_imageView);

        container = findViewById(R.id.container);
        bg1 = findViewById(R.id.bg1);
        bg2 = findViewById(R.id.bg2);
        start_breathing= findViewById(R.id.startBreathing_imageView);
        text = findViewById(R.id.startBreathing_text);
        textbox = findViewById(R.id.textbox);
        logo = findViewById(R.id.logo_iV);

        container.setOnTouchListener(new OnSwipeTouchListener(CompletedActivity.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {

            }
            public void onSwipeLeft() {
                Intent in = new Intent(CompletedActivity.this,Settings.class);
                in.putExtra("mode","light");
                startActivity(in);
                Animatoo.animateSlideLeft(CompletedActivity.this);
                finish();
                return;

            }
            public void onSwipeBottom() {

            }

        });

        performAnimation(bg1,0.86f,2000);
        performAnimation(bg2,0.8f,2000);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(CompletedActivity.this,MainActivity.class);
                startActivity(in);
                Animatoo.animateFade(CompletedActivity.this);
                finish();
                return;

//
            }
        });


    }


    void performAnimation(ImageView im, float f, int timer) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(im, "scaleX", f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(im, "scaleY", f);
        scaleDownX.setDuration(timer);
        scaleDownY.setDuration(timer);

        AnimatorSet scaleDown = new AnimatorSet();


        scaleDown.play(scaleDownX).with(scaleDownY);
        //scaleDown.setStartDelay(hold);
        scaleDown.start();

    }
}
