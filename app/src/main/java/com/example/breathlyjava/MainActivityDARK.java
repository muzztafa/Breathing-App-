package com.example.breathlyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivityDARK extends AppCompatActivity implements NumberPicker.OnValueChangeListener, FilterBottomSheetDialog.BottomSheetListener{
    private ImageView bg1;
    private ImageView bg2;
    private TextView startBreathing_textView;
    private ImageView bg_imageView;
    private NumberPicker np;

    int extrahold;
    private Button start;

    private ImageView back;
    private ImageView filter;

    private ImageView invisible;

    private TextView time;
    private TextView mins;
    private Button bRelax;
    private Button bSleep;
    private Button fpbe;

    private ImageView sImage;
    private TextView sText;

    private boolean isRunning;
    private float f;


    private int inhale;
    private int exhale;
    private int hold;
    private boolean timer;

    private boolean run;

    private int selectedTimer;
    private int currentTimer ;

    private boolean fbpeAgain=false;

    private CountDownTimer cd;
    private CountDownTimer cdMain;
     ImageView settings;

    private String getTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dark);

        getTimer=getIntent().getStringExtra("timer");

      //  Toast.makeText(this, getTimer ,Toast.LENGTH_SHORT).show();
          settings = findViewById(R.id.settings_imageView);
         settings.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                   Intent in = new Intent(MainActivityDARK.this,Settings.class);
             in.putExtra("mode","dark");
               startActivity(in);
              Animatoo.animateFade(MainActivityDARK.this);
                }
          });

        selectedTimer = 1000*60*60;  //initiliazing timer with infinity



        sText = findViewById(R.id.selected_textView);
        sImage = findViewById(R.id.selected_imageView);

        sText.setVisibility(View.INVISIBLE);
        sImage.setVisibility(View.INVISIBLE);
        run=true;

        isRunning=false;
        bg_imageView = findViewById(R.id.bg_imageView);
        bg1 = findViewById(R.id.bg1);
        bg2 = findViewById(R.id.bg2);
        invisible = findViewById(R.id.invisible);

        time = findViewById(R.id.timer_textView);
        mins = findViewById(R.id.mins_textView);
        bRelax = findViewById(R.id.bRelax_btn);
        bSleep = findViewById(R.id.bSleep_btn3);
        fpbe = findViewById(R.id.fpbe_btn);
        start = findViewById(R.id.start_btn);

        //back = findViewById(R.id.back_imageView);
          filter = findViewById(R.id.filter_imageView);

        startBreathing_textView=findViewById(R.id.startBreathing_text);

        back = findViewById(R.id.backMain_imageView);
        back.setVisibility(View.INVISIBLE);


        inhale=4000;
        hold=2000;
        exhale=4000;

        if(getIntent().getStringExtra("inhale")!=null){
            inhale = Integer.parseInt(getIntent().getStringExtra("inhale"));
        }
        if(getIntent().getStringExtra("exhale")!=null){
            exhale =Integer.parseInt( getIntent().getStringExtra("exhale"));
        }
        if(getIntent().getStringExtra("hold")!=null){
            hold = Integer.parseInt(getIntent().getStringExtra("hold"));
        }


        np = findViewById(R.id.numberPicker);
        np.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        np.setMinValue(1);
        np.setMaxValue(30);
        np.setFadingEdgeLength(2147483647);
        np.setClickable(false);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d",i);
            }
        });

        np.setOnValueChangedListener(this);
        if(getTimer!=null && !getTimer.equals("")){
            if(Integer.parseInt(getTimer)!=0){
            selectedTimer = 1000 * 60 * Integer.parseInt(getTimer);
            np.setValue(Integer.parseInt(getTimer));}
        }
        f = 1f;


        if(inhale==0){inhale=4000;}
        if(exhale==0){exhale=4000;}
        if(hold==0){hold=2000;}


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning) {
                    Intent in = new Intent(MainActivityDARK.this,CompletedActivityDark.class);
                    startActivity(in);
                    finish();
                    Animatoo.animateFade(MainActivityDARK.this);

                    cdMain.cancel();

                    timer=false;
                    isRunning=false;
                    cd.cancel();
                }
                else{
                    filter.setVisibility(View.INVISIBLE);
                    settings.setVisibility(View.INVISIBLE);
                    bg_imageView.setBackgroundResource(R.drawable.background_circle_transition_dark);
                    sImage.setVisibility(View.INVISIBLE);
                    sText.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.INVISIBLE);
                    mins.setVisibility(View.INVISIBLE);
                    bRelax.setVisibility(View.INVISIBLE);
                    bSleep.setVisibility(View.INVISIBLE);
                    fpbe.setVisibility(View.INVISIBLE);
                    np.setVisibility(View.INVISIBLE);
                    //  filter.setVisibility(View.INVISIBLE);
//                    back.setVisibility(View.INVISIBLE);
                    //    settings.setVisibility(View.INVISIBLE);

                    isRunning=true;
                    timer=true;
                    start.setText("STOP");
                    extrahold = 0;

                    cdMain = new CountDownTimer(selectedTimer,inhale+hold+exhale+extrahold) {
                        @Override
                        public void onTick(long l) {

                            cd = new CountDownTimer(inhale, inhale) {
                                public void onTick(long millisUntilFinished) {
                                    start.setText("STOP");

                                    startBreathing_textView.setText("Breath in");
                                    f = 2f;
                                    performAnimation(bg2, f, inhale, hold);
                                    performAnimation(bg1, f - 0.5f, inhale, hold);
                                }



                                public void onFinish() {
                                    timer = false;

                                    cd = new CountDownTimer(hold, hold) {
                                        public void onTick(long millisUntilFinished) {
                                            startBreathing_textView.setText("Hold");
                                        }



                                        public void onFinish() {
                                            cd = new CountDownTimer(exhale, exhale) {
                                                public void onTick(long millisUntilFinished) {
                                                    f = 1f;
                                                    startBreathing_textView.setText("Breath out");

                                                    performAnimation(bg2, f, exhale, hold);
                                                    performAnimation(bg1, f, exhale, hold);
                                                }



                                                public void onFinish() {

                                                    if(fbpeAgain){
                                                        cd = new CountDownTimer(hold, hold) {
                                                            @Override
                                                            public void onTick(long l) {
                                                                startBreathing_textView.setText("Hold");
                                                            }

                                                            @Override
                                                            public void onFinish() {

                                                            }
                                                        }.start();

                                                    }
                                                    timer = false;
                                                }
                                            }.start();
                                        }
                                    }.start();



                                }
                            }.start();
                        }

                        @Override
                        public void onFinish() {

                            try {
                                wait(1000);
                            }
                            catch (Exception e){}
                            Intent in = new Intent(MainActivityDARK.this,CompletedActivity.class);
                            startActivity(in);
                            Animatoo.animateFade(MainActivityDARK.this);
                            finish();

                        }
                    }.start();
                }

            }





        });



         filter.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             FilterBottomSheetDialog d = new FilterBottomSheetDialog();
             d.show(getSupportFragmentManager(),"exampleBottomSheet");
          }
           });

        bRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inhale=5000;
                exhale=5000;
                hold=0;
                fbpeAgain = false;
                back.setVisibility(View.VISIBLE);
                sText.setText("Breathe to Relax");
                sImage.setImageResource(R.drawable.restingwhite);

               // performLogoAnimation(sImage,bSleep.getX(),sImage.getX()-670f,bSleep.getY(),sImage.getY()-90f);




                sText.setVisibility(View.VISIBLE);
                sImage.setVisibility(View.VISIBLE);


                circularReveal();


                bRelax.setVisibility(View.INVISIBLE);
                bSleep.setVisibility(View.INVISIBLE);
                fpbe.setVisibility(View.INVISIBLE);

                filter.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.INVISIBLE);
            }
        });

        bSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inhale=4000;
                exhale=7000;
                hold=8000;
                fbpeAgain = false;

               // performLogoAnimation(sImage,bSleep.getX()-600f,sImage.getX()-670f,bSleep.getY(),sImage.getY()-90f);

                sText.setText("Breathe to Sleep");
                sImage.setImageResource(R.drawable.sleepwhite);
                back.setVisibility(View.VISIBLE);
                sText.setVisibility(View.VISIBLE);
                sImage.setVisibility(View.VISIBLE);
                bRelax.setVisibility(View.INVISIBLE);
                bSleep.setVisibility(View.INVISIBLE);
                fpbe.setVisibility(View.INVISIBLE);

                circularReveal();

                filter.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.INVISIBLE);
            }
        });

        fpbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setVisibility(View.VISIBLE);
                inhale=2000;
                exhale=2000;
                hold=2000;
                extrahold = hold;
                fbpeAgain = true;
                sImage.setImageResource(R.drawable.exercisewhite);

              //  performLogoAnimation(sImage,sImage.getX(),sImage.getX()-670f,fpbe.getY(),sImage.getY()-90f);


                sText.setText("FPBE");
                sText.setVisibility(View.VISIBLE);
                sImage.setVisibility(View.VISIBLE);

                circularReveal();


                bRelax.setVisibility(View.INVISIBLE);
                bSleep.setVisibility(View.INVISIBLE);
                fpbe.setVisibility(View.INVISIBLE);

                filter.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.INVISIBLE);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setVisibility(View.INVISIBLE);
                sText.setVisibility(View.INVISIBLE);
                sImage.setVisibility(View.INVISIBLE);
                bRelax.setVisibility(View.VISIBLE);
                bSleep.setVisibility(View.VISIBLE);
                fpbe.setVisibility(View.VISIBLE);

                filter.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);
            }
        });





    }

    void performAnimation(ImageView im, float f, int timer, int hold) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(im, "scaleX", f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(im, "scaleY", f);
        scaleDownX.setDuration(timer);
        scaleDownY.setDuration(timer);

        AnimatorSet scaleDown = new AnimatorSet();


        scaleDown.play(scaleDownX).with(scaleDownY);
        //scaleDown.setStartDelay(hold);
        scaleDown.start();

    }


    void performLogoAnimation(ImageView im,float xCurrentPos, float xDesired, float yCurrentPos, float yDesired){

        Animation anim= new TranslateAnimation(xCurrentPos, xDesired, yCurrentPos, yDesired);
        anim.setDuration(1000);
        im.startAnimation(anim);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

        for (int c=1; c<31;c++){
            if(c==i1){
                selectedTimer=1000*60*i;
            }
        }

    }

    @Override
    public void onButtonClicked(int inhale, int exhale, int hold) {
        this.inhale=inhale;
        this.exhale=exhale;
        this.hold=hold;
        fbpeAgain=false;
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
            this.exhale=exhale;
        }
    }

    @Override
    public void onHoldProgressChanged(int hold) {
        if (hold!=0){
            this.hold=hold;
        }
    }

    public void circularReveal(){
        int cx=sImage.getWidth()/2;
        int cy=sImage.getHeight()/2;
        float finalRadius = (float)Math.hypot(cx,cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(sImage,cx,cy,0,finalRadius);
        anim.setDuration(1000);
        anim.start();
    }
    }

