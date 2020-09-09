package com.example.organica;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context = context;
    }
    public int[] slide_images = {R.drawable.organic,R.drawable.organic,R.drawable.organic};
    public String[] slide_headings = {"FAST DELIVERY","SAVE MONEY","STAY HEALTHY"};
    public String[] slide_desc = {"Order just what you need- not more, not less and we will deliver to your doorstep",
    "No minimum orders, order at low cost",
    "No interactions, no door bell rings"
            };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);
        ImageView sliderImageView = (ImageView) view.findViewById(R.id.c_img);
        TextView sliderHeadingView = (TextView) view.findViewById(R.id.head);
        TextView sliderDescriptionView = (TextView) view.findViewById(R.id.desc);
        sliderImageView.setImageResource(slide_images[position]);
        sliderHeadingView.setText(slide_headings[position]);
        sliderDescriptionView.setText(slide_desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
