package com.example.dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashboard.HelperClass.ArticlesAdapter;
import com.example.dashboard.HelperClass.ArticlesHelper;
import com.example.dashboard.HelperClass.BuyedAdapter;
import com.example.dashboard.HelperClass.BuyedHelper;
import com.example.dashboard.HelperClass.ImpAdapter;
import com.example.dashboard.HelperClass.ImpHelper;
import com.example.dashboard.HelperClass.Vegetables;
import com.example.dashboard.HelperClass.beverages;
import com.example.dashboard.HelperClass.fruits;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefragment extends Fragment {
    RecyclerView imprecycler;
    TextView snack;
    ImageView bar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView vegetables, fruits, beverages;
    TextView view_buyed, view_articles;
    RecyclerView.Adapter adapter;
    RecyclerView buyrecycler;
    RecyclerView.Adapter adapter1;
    RecyclerView artrecycler;
    RecyclerView.Adapter adapter2;
    //ChipNavigationBar chipnavigationbar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homefragment newInstance(String param1, String param2) {
        homefragment fragment = new homefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        super.onCreate(savedInstanceState);
//
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        imprecycler = (RecyclerView) rootView.findViewById(R.id.importance);

        imprecycler();
        buyrecycler = rootView.findViewById(R.id.buyed);
        buyrecycler();
        artrecycler = rootView.findViewById(R.id.articles);
        artrecycler();
//        chipnavigationbar = findViewById(R.id.chip);
//        chipnavigationbar.setItemSelected(R.id.dashboard,true);

//        bottommenu();


        snack = (TextView) rootView.findViewById(R.id.text_snacks);
        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                snackactivity();
            }
        });
        vegetables = (TextView) rootView.findViewById(R.id.text_vegetables);
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
                vegetablesactivity();
            }
        });

        fruits = (TextView) rootView.findViewById(R.id.text_fruits);
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fruitsactivity();
            }
        });

        beverages = (TextView) rootView.findViewById(R.id.text_beverages);
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beveragesactivity();
            }
        });
        view_buyed = (TextView) rootView.findViewById(R.id.view_all_buyed);
        view_buyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_buy();
            }
        });
        view_articles = (TextView) rootView.findViewById(R.id.view_all_articles);
        view_articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_art();
            }
        });


        return rootView;


//
//
//
    }

    private void imprecycler() {
        imprecycler.setHasFixedSize(true);
        imprecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<ImpHelper> location = new ArrayList<>();
        location.add(new ImpHelper(R.drawable.img, "text1", "MDIOWMDOIWMDIWEMWEIMCWIMCICDCDSSC"));
        location.add(new ImpHelper(R.drawable.img, "text1", "MDIOWMDOIWMDIWEMWEIMCWIMCICDCDSSC"));
        adapter = new ImpAdapter(location);
        imprecycler.setAdapter(adapter);

    }

    private void buyrecycler() {
        buyrecycler.setHasFixedSize(true);
        buyrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<BuyedHelper> location1 = new ArrayList<>();
        location1.add(new BuyedHelper(R.drawable.img, "obj1"));
        location1.add(new BuyedHelper(R.drawable.img, "Obj2"));
        adapter1 = new BuyedAdapter(location1);
        buyrecycler.setAdapter(adapter1);
    }

    private void artrecycler() {
        artrecycler.setHasFixedSize(true);
        artrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        ArrayList<ArticlesHelper> location2 = new ArrayList<>();
        location2.add(new ArticlesHelper(R.drawable.img, "obj1"));

        adapter2 = new ArticlesAdapter(location2);
        artrecycler.setAdapter(adapter2);
    }

    public void snackactivity() {
        Intent intent = new Intent(getActivity(), Snacks.class);
        startActivity(intent);
    }

    public void vegetablesactivity() {

        Intent intent = new Intent(getActivity(), Vegetables.class);
        startActivity(intent);
    }

    public void fruitsactivity() {
        Intent intent = new Intent(getActivity(), fruits.class);
        startActivity(intent);

    }

    public void beveragesactivity() {
        Intent intent = new Intent(getActivity(), beverages.class);
        startActivity(intent);

    }

    public void view_buy() {
        Intent intent = new Intent(getActivity(), most_buyed.class);
        startActivity(intent);

    }

    public void view_art() {
        Intent intent = new Intent(getActivity(), articles_all.class);
        startActivity(intent);

    }

    public void dizzy() {

    }

}

