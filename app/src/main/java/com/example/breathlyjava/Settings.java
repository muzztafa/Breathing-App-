package com.example.breathlyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Settings extends AppCompatActivity implements FilterBottomSheetDialog.BottomSheetListener {
    TextView t1;
    TextView t2;
    Switch switch1;
    ConstraintLayout layout;
    ImageView back;
    String mode;
    boolean isOn;
    ConstraintLayout container;
    TextView value;

    ImageView settings;

    SeekBar timer;

    int inhale;
    int exhale;
    int hold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        value = findViewById(R.id.id_TextView);
        timer = findViewById(R.id.timerSeekbar);

        settings = findViewById(R.id.filter);

        mode = getIntent().getStringExtra("mode");

        t1=findViewById(R.id.textView8);
        t2 = findViewById(R.id.textView);
        switch1 =findViewById(R.id.switch1);
        back = findViewById(R.id.back);
        container = findViewById(R.id.container);

        if(mode.equals("dark")){
            switch1.setChecked(true);
            isOn = true;
            container.setBackgroundColor(getResources().getColor(R.color.darkTheme));
        }
        else {switch1.setChecked(false);
            isOn = false;
            container.setBackgroundColor(getResources().getColor(R.color.lighTheme));
        }

        settings.setOnClickListener(new View.OnClickListener() {
               @Override
              public void onClick(View view) {
                 FilterBottomSheetDialog d = new FilterBottomSheetDialog();
               d.show(getSupportFragmentManager(),"exampleBottomSheet");
                }
              });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isOn=true;
                    container.setBackgroundColor(getResources().getColor(R.color.darkTheme));


                }
                else {
                    isOn=false;
                    container.setBackgroundColor(getResources().getColor(R.color.lighTheme));
                }
            }
        });
        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value.setText(""+timer.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        container.setOnTouchListener(new OnSwipeTouchListener(Settings.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                if(isOn){
                    Intent in = new Intent(Settings.this,MainActivityDARK.class);
                    in.putExtra("timer",""+timer.getProgress());
                    in.putExtra("inhale",""+inhale);
                    in.putExtra("exhale",""+exhale);
                    in.putExtra("hold",""+hold);
                    startActivity(in);
                    Animatoo.animateSlideRight(Settings.this);
                    finish();
                    return;
                }

                else{
                    Intent in = new Intent(Settings.this,MainActivity.class);
                    in.putExtra("timer",""+timer.getProgress());
                    in.putExtra("inhale",""+inhale);
                    in.putExtra("exhale",""+exhale);
                    in.putExtra("hold",""+hold);
                    startActivity(in);
                    Animatoo.animateSlideRight(Settings.this);
                    finish();
                    return;
                }

            }
            public void onSwipeLeft() {

            }
            public void onSwipeBottom() {

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOn){
                    Intent in = new Intent(Settings.this,MainActivityDARK.class);
                    in.putExtra("timer",""+timer.getProgress());

                    in.putExtra("inhale",""+inhale);
                    in.putExtra("exhale",""+exhale);
                    in.putExtra("hold",""+hold);
                    startActivity(in);
                    Animatoo.animateFade(Settings.this);
                    finish();
                    return;
                }

                else{
                    Intent in = new Intent(Settings.this,MainActivity.class);

                    in.putExtra("timer",""+timer.getProgress());
                    in.putExtra("inhale",""+inhale);
                    in.putExtra("exhale",""+exhale);
                    in.putExtra("hold",""+hold);
                    startActivity(in);
                    Animatoo.animateFade(Settings.this);
                    finish();
                    return;
                }
            }
        });
    }

    @Override
    public void onButtonClicked(int inhale, int exhale, int hold) {


        this.inhale = inhale;
        this.exhale = exhale;
        this.hold = hold;
    }

    @Override
    public void onInhaleProgressChanged(int inhale) {
        if (inhale!=0){
            this.inhale=inhale;
        }
    }

    @Override
    public void onExhaleProgressChanged(int exhale) {
        if (exhale!=0){
            this.exhale=inhale;
        }
    }

    @Override
    public void onHoldProgressChanged(int hold) {
        if (hold!=0){
            this.hold=inhale;
        }
    }
}
