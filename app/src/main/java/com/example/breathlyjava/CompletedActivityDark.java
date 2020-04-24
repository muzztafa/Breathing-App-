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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompletedActivityDark extends AppCompatActivity {
    ImageView back;
    ImageView bg1;
    ImageView bg2;
    TextView text;
    TextView textbox;
    ImageView logo;
    ConstraintLayout container;

    ImageView start_breathing;
    String mode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_dark);




        back = findViewById(R.id.back_imageView);
        container = findViewById(R.id.container);


        bg1 = findViewById(R.id.bg1);
        bg2 = findViewById(R.id.bg2);
        start_breathing = findViewById(R.id.startBreathing_imageView);
        text = findViewById(R.id.startBreathing_text);
        textbox = findViewById(R.id.textbox);
        logo = findViewById(R.id.logo_iV);

        container.setOnTouchListener(new OnSwipeTouchListener(CompletedActivityDark.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                Intent in = new Intent(CompletedActivityDark.this,Settings.class);
                in.putExtra("mode","dark");
                startActivity(in);
                Animatoo.animateSlideRight(CompletedActivityDark.this);
                finish();
                return;
            }
            public void onSwipeLeft() {
                Intent in = new Intent(CompletedActivityDark.this,Settings.class);
                in.putExtra("mode","dark");
                startActivity(in);
                Animatoo.animateSlideLeft(CompletedActivityDark.this);
                finish();
                return;

            }
            public void onSwipeBottom() {

            }

        });
        performAnimation(bg1, 0.86f, 2000);
        performAnimation(bg2, 0.8f, 2000);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  if (mode == null) {
                //    Intent in = new Intent(CompletedActivityDark.this, MainActivityDARK.class);
                  //  startActivity(in);
                    //Animatoo.animateFade(CompletedActivityDark.this);
                    //finish();
                    //return;
                // } else if (mode == "light") {
                //   Intent in = new Intent(CompletedActivityDark.this, MainActivity.class);
                //  startActivity(in);
                //   Animatoo.animateFade(CompletedActivityDark.this);
                //   finish();
                //   return;
                //  } else {
                //      Intent in = new Intent(CompletedActivityDark.this, MainActivityDARK.class);
                //    startActivity(in);
                //    Animatoo.animateFade(CompletedActivityDark.this);
                //    finish();
                //    return;
                // }

                Intent in = new Intent(CompletedActivityDark.this,MainActivityDARK.class);
                startActivity(in);
                Animatoo.animateFade(CompletedActivityDark.this);
                finish();
                return;


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

