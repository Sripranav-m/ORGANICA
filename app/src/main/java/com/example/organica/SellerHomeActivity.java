package com.example.organica;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class SellerHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private ImageView seller_post_btn;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);

        auth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_Seller()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        seller_post_btn = findViewById(R.id.seller_post_btn);
        seller_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerHomeActivity.this, Seller_post.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId())
        {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home_Seller()).commit();
                break;
            case R.id.nav_article:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new articlefragment()).commit();
                break;
            case R.id.nav_my_products:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyProductsFragment()).commit();
                break;
            case R.id.nav_to_seller_buyer_details:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ToSellerBuyerFragment()).commit();
                break;
            case R.id.sign_out:
                auth.signOut();
                finish();
                startActivity(new Intent(SellerHomeActivity.this, splash_screen.class));
                break;
            case R.id.draw_share:
                try {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT,"GO-ORGANIC");
                    shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps?detals?id="+getApplicationContext().getPackageName());
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent,"Share Using"));;
                } catch (Exception e) {
                    Toast.makeText(this,"Unable to share\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.draw_rate:
                Uri uri = Uri.parse("https://play.google.com/store/apps?detals?id="+getApplicationContext().getPackageName());
                Intent rateIntent = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(rateIntent);
                } catch (Exception e) {
                    Toast.makeText(this,"Unable to open now\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
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