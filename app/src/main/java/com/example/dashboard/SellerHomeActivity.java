package com.example.dashboard;//package com.example.dashboard;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.dashboard.HelperClass.ArticlesAdapter;
//import com.example.dashboard.HelperClass.ArticlesHelper;
//import com.example.dashboard.HelperClass.BuyedAdapter;
//import com.example.dashboard.HelperClass.BuyedHelper;
//import com.example.dashboard.HelperClass.ImpAdapter;
//import com.example.dashboard.HelperClass.ImpHelper;
//import com.example.dashboard.HelperClass.Vegetables;
//import com.example.dashboard.HelperClass.beverages;
//import com.example.dashboard.HelperClass.fruits;
//import com.google.android.material.navigation.NavigationView;
//import com.ismaeldivita.chipnavigation.ChipNavigationBar;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.bringToFront();
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homefragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//        }
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
////
////        bar = findViewById(R.id.bar);
////        bar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(MainActivity.this, dizzy.class);
////                startActivity(intent);
////            }
////        });
//        imprecycler = findViewById(R.id.importance);
//
//        imprecycler();
//        buyrecycler = findViewById(R.id.buyed);
//        buyrecycler();
//        artrecycler = findViewById(R.id.articles);
//        artrecycler();
////        chipnavigationbar = findViewById(R.id.chip);
////        chipnavigationbar.setItemSelected(R.id.dashboard,true);
//
////        bottommenu();
//
//
//        snack = (TextView) findViewById(R.id.text_snacks);
//        snack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackactivity();
//            }
//        });
//        vegetables = (TextView) findViewById(R.id.text_vegetables);
//        vegetables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
//                vegetablesactivity();
//            }
//        });
//
//        fruits = (TextView) findViewById(R.id.text_fruits);
//        fruits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fruitsactivity();
//            }
//        });
//
//        beverages = (TextView) findViewById(R.id.text_beverages);
//        beverages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                beveragesactivity();
//            }
//        });
//        view_buyed = (TextView) findViewById(R.id.view_all_buyed);
//        view_buyed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view_buy();
//            }
//        });
//        view_articles = (TextView) findViewById(R.id.view_all_articles);
//        view_articles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view_art();
//            }
//        });
//
//    }
//
//
//    private void imprecycler() {
//        imprecycler.setHasFixedSize(true);
//        imprecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        ArrayList<ImpHelper> location = new ArrayList<>();
//        location.add(new ImpHelper(R.drawable.img, "text1", "MDIOWMDOIWMDIWEMWEIMCWIMCICDCDSSC"));
//        location.add(new ImpHelper(R.drawable.img, "text1", "MDIOWMDOIWMDIWEMWEIMCWIMCICDCDSSC"));
//        adapter = new ImpAdapter(location);
//        imprecycler.setAdapter(adapter);
//
//    }
//
//    private void buyrecycler() {
//        buyrecycler.setHasFixedSize(true);
//        buyrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        ArrayList<BuyedHelper> location1 = new ArrayList<>();
//        location1.add(new BuyedHelper(R.drawable.img, "obj1"));
//        location1.add(new BuyedHelper(R.drawable.img, "Obj2"));
//        adapter1 = new BuyedAdapter(location1);
//        buyrecycler.setAdapter(adapter1);
//    }
//
//    private void artrecycler() {
//        artrecycler.setHasFixedSize(true);
//        artrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//
//        ArrayList<ArticlesHelper> location2 = new ArrayList<>();
//        location2.add(new ArticlesHelper(R.drawable.img, "obj1"));
//
//        adapter2 = new ArticlesAdapter(location2);
//        artrecycler.setAdapter(adapter2);
//    }
//
//    public void snackactivity() {
//        Intent intent = new Intent(this, Snacks.class);
//        startActivity(intent);
//    }
//
//    public void vegetablesactivity() {
//
//        Intent intent = new Intent(this, Vegetables.class);
//        startActivity(intent);
//    }
//
//    public void fruitsactivity() {
//        Intent intent = new Intent(this, fruits.class);
//        startActivity(intent);
//
//    }
//
//    public void beveragesactivity() {
//        Intent intent = new Intent(this, beverages.class);
//        startActivity(intent);
//
//    }
//
//    public void view_buy() {
//        Intent intent = new Intent(this, most_buyed.class);
//        startActivity(intent);
//
//    }
//
//    public void view_art() {
//        Intent intent = new Intent(this, articles_all.class);
//        startActivity(intent);
//
//    }
//
//    public void dizzy() {
//
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.nav_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homefragment()).commit();
//                break;
//            case R.id.nav_article:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new articlefragment()).commit();
//                break;
//            case R.id.nav_cart:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new cartfragment()).commit();
//                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//}
//


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SellerHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

//        sign_out = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();
//        sign_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                auth.signOut();
//                startActivity(new Intent(SellerHomeActivity.this, MainActivity.class));
//            }
//        });


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView =findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container1, new homefragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container1,new homefragment()).commit();
                break;
            case R.id.nav_article:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container1,new articlefragment()).commit();
                break;
            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container1,new cartfragment()).commit();
                break;
            case R.id.sign_out:
                auth.signOut();
                finish();
                startActivity(new Intent(SellerHomeActivity.this, MainActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


}