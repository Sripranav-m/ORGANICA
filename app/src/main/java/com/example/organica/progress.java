package com.example.organica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class progress extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView;
    TextView textView1;
    Animation fade;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_progress);
        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.progress_text);
        fade = AnimationUtils.loadAnimation(this,R.anim.fading);
        textView1 = (TextView)findViewById(R.id.loading);
        textView1.setAnimation(fade);

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(i <= 100){
                    textView.setText(""+i+"%");
                    progressBar.setProgress(i);
                    i++;
                    h.postDelayed(this,1);
                }
                else{
                    Intent intent = new Intent(progress.this,splash_screen.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1);
    }

}