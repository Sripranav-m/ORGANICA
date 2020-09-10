package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {
    Animation top,bot,left,right,fade;
    ImageView imageView;
    TextView logo,slogan,text1,text2;
    private static int TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bot = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        left = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        right = AnimationUtils.loadAnimation(this,R.anim.right_animation);
        fade = AnimationUtils.loadAnimation(this,R.anim.fading);

        imageView =  findViewById(R.id.splash_image);
        logo = findViewById(R.id.splash_name);
        slogan = findViewById(R.id.splash_sloagn);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        imageView.setAnimation(fade);
        logo.setAnimation(top);
        slogan.setAnimation(bot);
        text1.setAnimation(left);
        text2.setAnimation(right);

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this,walkthrough.class);
                startActivity(intent);
                finish();
            }
        },TIME);
    }
}