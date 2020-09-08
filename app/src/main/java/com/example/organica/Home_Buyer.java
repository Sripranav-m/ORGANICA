package com.example.organica;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class Home_Buyer extends Fragment {
    /*RecyclerView imprecycler;*/
    TextView snack;
    ImageView bar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button sign_out;
    private FirebaseAuth auth;
    public EditText search_item;
    private ImageView search_button;
    private ImageView buyer_vegetables;
    private ImageView buyer_fruits;
    private ImageView buyer_beverages;
    private ImageView buyer_snacks;
    private ImageView buyer_cart_button;

/*
    TextView vegetables, fruits, beverages;
    TextView view_buyed, view_articles;*/
   /* RecyclerView.Adapter adapter;
    RecyclerView buyrecycler;
    RecyclerView.Adapter adapter1;
    RecyclerView artrecycler;
    RecyclerView.Adapter adapter2;*/
    //ChipNavigationBar chipnavigationbar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Buyer() {
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
    public static Home_Buyer newInstance(String param1, String param2)
    {
        Home_Buyer fragment = new Home_Buyer();
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
        View rootView = inflater.inflate(R.layout.fragment_home_buyer, container, false);

        search_item = (EditText) rootView.findViewById(R.id.search_text);
        search_button = (ImageView) rootView.findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(view);
            }
        });



        buyer_vegetables = (ImageView) rootView.findViewById(R.id.buyer_vegetables);
        buyer_vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyer_vegetable();
            }
        });

        buyer_fruits = (ImageView) rootView.findViewById(R.id.buyer_fruits);
        buyer_fruits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                buyer_fruits();
            }
        });
       /* public void buyer_vegetables(View view) {
            Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
            i.putExtra("item_category", "VEGETABLES");
            startActivity(i);
        }*/
        buyer_beverages = (ImageView) rootView.findViewById(R.id.buyer_beverages2);
        buyer_beverages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                buyer_beverages();
            }
        });

      /*  public void buyer_fruits(View view) {
            Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
            i.putExtra("item_category", "FRUITS");
            startActivity(i);
        }*/

        /*public void buyer_beverages(View view) {
            Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
            i.putExtra("item_category", "BEVERAGES");
            startActivity(i);
        }*/
        buyer_snacks = (ImageView) rootView.findViewById(R.id.buyer_snacks2);
        buyer_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyer_snacks();
            }
        });

     /*   public void buyer_snacks(View view) {
            Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
            i.putExtra("item_category", "SNACKS");
            startActivity(i);
        }*/
        // Inflate the layout for this fragment
     /*   imprecycler = (RecyclerView) rootView.findViewById(R.id.importance);

        imprecycler();
        buyrecycler = rootView.findViewById(R.id.buyed);
        buyrecycler();
        artrecycler = rootView.findViewById(R.id.articles);
        artrecycler();*/
//        chipnavigationbar = findViewById(R.id.chip);
//        chipnavigationbar.setItemSelected(R.id.dashboard,true);

//        bottommenu();


       /* snack = (TextView) rootView.findViewById(R.id.text_snacks);
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
*/

        return rootView;

    }
//
//
//
public void search(View view) {
    String search = search_item.getText().toString();
    if (search.trim().length() > 0) {
        Intent i = new Intent(getActivity(), BuyerSearch.class);
        i.putExtra("search_string", search);
        startActivity(i);
    } else {
        Toast.makeText(getActivity().getApplicationContext(), "Enter something in Search", Toast.LENGTH_SHORT).show();
    }
}

    private void buyer_snacks() {
        Intent i = new Intent(getActivity(), Buyer_see.class);
        i.putExtra("item_category", "SNACKS");
        startActivity(i);

    }

    private void buyer_beverages() {
        Intent i = new Intent(getActivity(), Buyer_see.class);
        i.putExtra("item_category", "BEVERAGES");
        startActivity(i);
    }

    private void buyer_fruits() {
        Intent i = new Intent(getActivity(), Buyer_see.class);
        i.putExtra("item_category", "FRUITS");
        startActivity(i);
    }


    private void buyer_vegetable() {
        Intent i = new Intent(getActivity(), Buyer_see.class);
        i.putExtra("item_category", "VEGETABLES");
        startActivity(i);
    }


}
    /* private void imprecycler() {
        imprecycler.setHasFixedSize(true);
        imprecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<ImpHelper> location = new ArrayList<>();
        location.add(new ImpHelper(R.drawable.img, "text1", "MDIOWMDOIWMDIWEMWEIMCWIMCICDCDSSC"));
        location.add(new ImpHelper(R.drawable.img, "text1", "MDIOWMDOIWMDIWEMWEIMCWIMCICDCDSSC"));
        adapter = new ImpAdapter(location);
        imprecycler.setAdapter(adapter);

    }
*/
   /* private void buyrecycler() {
        buyrecycler.setHasFixedSize(true);
        buyrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<BuyedHelper> location1 = new ArrayList<>();
        location1.add(new BuyedHelper(R.drawable.img, "obj1"));
        location1.add(new BuyedHelper(R.drawable.img, "Obj2"));
        adapter1 = new BuyedAdapter(location1);
        buyrecycler.setAdapter(adapter1);
    }
*/
    /*private void artrecycler() {
        artrecycler.setHasFixedSize(true);
        artrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        ArrayList<ArticlesHelper> location2 = new ArrayList<>();
        location2.add(new ArticlesHelper(R.drawable.img, "obj1"));

        adapter2 = new ArticlesAdapter(location2);
        artrecycler.setAdapter(adapter2);
    }
*/
    /*public void snackactivity() {
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
*/

/*
import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;

        import java.util.ArrayList;

public class BuyerActivity extends AppCompatActivity {
    Button sign_out;
    private FirebaseAuth auth;
    public EditText search_item;

    public void search(View view) {
        String search = search_item.getText().toString();
        if (search.trim().length() > 0) {
            Intent i = new Intent(BuyerActivity.this, BuyerSearch.class);
            i.putExtra("search_string", search);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Failed To search.....", Toast.LENGTH_SHORT).show();
        }
    }

    public void buyer_vegetables(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "VEGETABLES");
        startActivity(i);
    }

    public void buyer_fruits(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "FRUITS");
        startActivity(i);
    }

    public void buyer_beverages(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "BEVERAGES");
        startActivity(i);
    }

    public void buyer_snacks(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "SNACKS");
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
//        sign_out = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        search_item = (EditText) findViewById(R.id.search_text);
//        sign_out.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    auth.signOut();
//                    startActivity(new Intent(BuyerActivity.this, MainActivity.class));
//                }
//            });


    }
}
*/