package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dashboard.HelperClass.SliderAdapter;

public class walkthrough extends AppCompatActivity {
    private ViewPager slideview;
    private LinearLayout dotlayout;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private Button back;
    private Button next;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walkthrough);
        slideview = (ViewPager) findViewById(R.id.walk);
        dotlayout = (LinearLayout) findViewById(R.id.dots);
        back = (Button) findViewById(R.id.button1);
        next = (Button) findViewById(R.id.button2);
        sliderAdapter = new SliderAdapter(this);
        slideview.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideview.addOnPageChangeListener(viewListener);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current == dots.length - 1){
                    Intent intent = new Intent(walkthrough.this,register.class);
                    startActivity(intent);
                    finish();
                }
                slideview.setCurrentItem(current+1);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideview.setCurrentItem(current-1);
            }
        });

    }

    public void addDotsIndicator(int position) {
        dots = new TextView[3];
        dotlayout.removeAllViews();
        int i;
        for (i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparent));
            dotlayout.addView(dots[i]);

        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            current = position;
            if (position == 0) {
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);
                next.setText("NEXT");
                back.setText("");
            }
            else if(position == dots.length - 1){
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                next.setText("FINISH");
                back.setText("BACK");
            }
            else{
                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                next.setText("NEXT");
                back.setText("BACK");


            }




        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}