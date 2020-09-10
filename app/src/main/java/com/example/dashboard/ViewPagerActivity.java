package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.widget.Adapter;

import com.example.dashboard.HelperClass.Model;
import com.example.dashboard.HelperClass.ViewPageAdapter;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {
    ViewPager viewPager2;
    ViewPageAdapter adapter;
    ArrayList<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.brochure, "Brochure", "A brochure is an informative paper document (often also used for advertising) that can be folded into a template, pamphlet, or leaflet. A brochure can also be a set of related unfolded papers put into a pocket folder or packet. Brochures are promotional documents, primarily used to introduce a company, organization, products or services and inform prospective customers or members of the public of the benefits."));
        models.add(new Model(R.drawable.sticker, "Sticker", "A brochure is an informative paper document (often also used for advertising) that can be folded into a template, pamphlet, or leaflet. A brochure can also be a set of related unfolded papers put into a pocket folder or packet. Brochures are promotional documents, primarily used to introduce a company, organization, products or services and inform prospective customers or members of the public of the benefits."));
        models.add(new Model(R.drawable.poster, "Poster", "A brochure is an informative paper document (often also used for advertising) that can be folded into a template, pamphlet, or leaflet. A brochure can also be a set of related unfolded papers put into a pocket folder or packet. Brochures are promotional documents, primarily used to introduce a company, organization, products or services and inform prospective customers or members of the public of the benefits."));
        models.add(new Model(R.drawable.namecard, "NameCard", "A brochure is an informative paper document (often also used for advertising) that can be folded into a template, pamphlet, or leaflet. A brochure can also be a set of related unfolded papers put into a pocket folder or packet. Brochures are promotional documents, primarily used to introduce a company, organization, products or services and inform prospective customers or members of the public of the benefits."));
        adapter = new ViewPageAdapter(models, this);
        viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(adapter);
        viewPager2.setPadding(130, 0, 130, 0);
        Integer[]  colorstemp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),



        };
        colors = colorstemp;
        viewPager2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager2.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                } else {
                        viewPager2.setBackgroundColor(colors[colors.length - 1]);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}