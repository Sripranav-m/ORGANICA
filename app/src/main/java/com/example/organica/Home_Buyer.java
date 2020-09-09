package com.example.organica;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
//public class Home_Buyer extends Fragment {
//    /*RecyclerView imprecycler;*/
//    TextView snack;
//    ImageView bar;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    Toolbar toolbar;
//
//    Button sign_out;
//    private FirebaseAuth auth;
//    public EditText search_item;
//    private ImageView search_button;
//    private ImageView buyer_vegetables;
//    private ImageView buyer_fruits;
//    private ImageView buyer_beverages;
//    private ImageView buyer_snacks;
//    private ImageView buyer_cart_button;
//
///*
//    TextView vegetables, fruits, beverages;
//    TextView view_buyed, view_articles;*/
//   /* RecyclerView.Adapter adapter;
//    RecyclerView buyrecycler;
//    RecyclerView.Adapter adapter1;
//    RecyclerView artrecycler;
//    RecyclerView.Adapter adapter2;*/
//    //ChipNavigationBar chipnavigationbar;
//
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Home_Buyer() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment homefragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Home_Buyer newInstance(String param1, String param2)
//    {
//        Home_Buyer fragment = new Home_Buyer();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//        super.onCreate(savedInstanceState);
////
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_home_buyer, container, false);
//
//        search_item = (EditText) rootView.findViewById(R.id.search_text);
//        search_button = (ImageView) rootView.findViewById(R.id.search_button);
//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                search(view);
//            }
//        });
//
//
//
//        buyer_vegetables = (ImageView) rootView.findViewById(R.id.buyer_vegetables);
//        buyer_vegetables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                buyer_vegetable();
//            }
//        });
//
//        buyer_fruits = (ImageView) rootView.findViewById(R.id.buyer_fruits);
//        buyer_fruits.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                buyer_fruits();
//            }
//        });
//
//        buyer_beverages = (ImageView) rootView.findViewById(R.id.buyer_beverages2);
//        buyer_beverages.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                buyer_beverages();
//            }
//        });
//
//
//        buyer_snacks = (ImageView) rootView.findViewById(R.id.buyer_snacks2);
//        buyer_snacks.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                buyer_snacks();
//            }
//        });
//
//     /*   public void buyer_snacks(View view) {
//            Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
//            i.putExtra("item_category", "SNACKS");
//            startActivity(i);
//        }*/
//        // Inflate the layout for this fragment
//     /*   imprecycler = (RecyclerView) rootView.findViewById(R.id.importance);
//
//        imprecycler();
//        buyrecycler = rootView.findViewById(R.id.buyed);
//        buyrecycler();
//        artrecycler = rootView.findViewById(R.id.articles);
//        artrecycler();*/
////        chipnavigationbar = findViewById(R.id.chip);
////        chipnavigationbar.setItemSelected(R.id.dashboard,true);
//
////        bottommenu();
//
//
//       /* snack = (TextView) rootView.findViewById(R.id.text_snacks);
//        snack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
//                snackactivity();
//            }
//        });
//        vegetables = (TextView) rootView.findViewById(R.id.text_vegetables);
//        vegetables.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(MainActivity.this, "This is my Toast message!",
////                        Toast.LENGTH_LONG).show();
//                vegetablesactivity();
//            }
//        });
//
//        fruits = (TextView) rootView.findViewById(R.id.text_fruits);
//        fruits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fruitsactivity();
//            }
//        });
//
//        beverages = (TextView) rootView.findViewById(R.id.text_beverages);
//        beverages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                beveragesactivity();
//            }
//        });
//        view_buyed = (TextView) rootView.findViewById(R.id.view_all_buyed);
//        view_buyed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view_buy();
//            }
//        });
//        view_articles = (TextView) rootView.findViewById(R.id.view_all_articles);
//        view_articles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view_art();
//            }
//        });
//*/
//
//        return rootView;
//
//    }
////
////
////
//public void search(View view) {
//    String search = search_item.getText().toString();
//    if (search.trim().length() > 0) {
//        Intent i = new Intent(getActivity(), BuyerSearch.class);
//        i.putExtra("search_string", search);
//        startActivity(i);
//    } else {
//        Toast.makeText(getActivity().getApplicationContext(), "Enter something in Search", Toast.LENGTH_SHORT).show();
//    }
//}
//
//    private void buyer_snacks() {
//        Intent i = new Intent(getActivity(), Buyer_see.class);
//        i.putExtra("item_category", "SNACKS");
//        startActivity(i);
//
//    }
//
//    private void buyer_beverages() {
//        Intent i = new Intent(getActivity(), Buyer_see.class);
//        i.putExtra("item_category", "BEVERAGES");
//        startActivity(i);
//    }
//
//    private void buyer_fruits() {
//        Intent i = new Intent(getActivity(), Buyer_see.class);
//        i.putExtra("item_category", "FRUITS");
//        startActivity(i);
//    }
//
//
//    private void buyer_vegetable() {
//        Intent i = new Intent(getActivity(), Buyer_see.class);
//        i.putExtra("item_category", "VEGETABLES");
//        startActivity(i);
//    }
//
//
//}
//



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class Home_Buyer extends Fragment {

    EditText search_item;
    ImageView search_button;

    RecyclerView imprecycler;
    TextView snack;
    ImageView bar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ArrayList<BuyedHelper> most_buyed;

    private  BuyedAdapter most_buyed_adapter;
    private DatabaseReference myref;

    TextView vegetables, fruits, beverages;
    TextView view_buyed, view_articles;
    RecyclerView.Adapter adapter;
    RecyclerView buyrecycler;
    RecyclerView.Adapter adapter1;
    RecyclerView artrecycler;
    RecyclerView.Adapter adapter2;
    //ChipNavigationBar chipnavigationbar;





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

        myref = FirebaseDatabase.getInstance().getReference().child("ITEMS");



        // Inflate the layout for this fragment
        imprecycler = (RecyclerView) rootView.findViewById(R.id.importance);
        imprecycler();

        buyrecycler = rootView.findViewById(R.id.buyed);



        RelativeLayout buyer_vegetables = (RelativeLayout) rootView.findViewById(R.id.buyer_vegetables);
        buyer_vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyer_vegetable();
            }
        });

        RelativeLayout buyer_fruits = (RelativeLayout) rootView.findViewById(R.id.buyer_fruits);
        buyer_fruits.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                buyer_fruits();
            }
        });

        RelativeLayout buyer_beverages = (RelativeLayout) rootView.findViewById(R.id.buyer_beverages2);
        buyer_beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyer_beverages();
            }
        });


        RelativeLayout buyer_snacks = (RelativeLayout) rootView.findViewById(R.id.buyer_snacks2);
        buyer_snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyer_snacks();
            }
        });


        view_buyed = (TextView) rootView.findViewById(R.id.view_all_buyed);
        view_buyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_buy();
            }
        });
//        view_articles = (TextView) rootView.findViewById(R.id.view_all_articles);
//        view_articles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view_art();
//            }
//        });


        return rootView;


//
//
//
    }

    @Override
    public void onStart() {
        super.onStart();

        buyrecycler.setHasFixedSize(true);
        buyrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        most_buyed = new ArrayList<>();

        GetDataFromFirebase("VEGETABLES");
        GetDataFromFirebase("BEVERAGES");
        GetDataFromFirebase("SNACKS");
        GetDataFromFirebase("FRUITS");

    }


    private void imprecycler() {
        imprecycler.setHasFixedSize(true);
        imprecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<ImpHelper> location = new ArrayList<>();
        location.add(new ImpHelper(R.drawable.organic8, "MONEY", "Why to waste money? Try Our App"));
        location.add(new ImpHelper(R.drawable.organic9, "LOVE ORGANIC", "Wanna be healthy? Go for Organic"));
        location.add(new ImpHelper(R.drawable.organic5, "ALERT", "Reduces your exposure to pesticides"));
        adapter = new ImpAdapter(location);
        imprecycler.setAdapter(adapter);

    }

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


    public void GetDataFromFirebase(final String item_category){

        Query query = myref.child(item_category);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    BuyedHelper iteminfo=new BuyedHelper("","",0);
                    iteminfo.setItem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setItem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setItem_buy_count(Integer.parseInt(snapshott.child("item_buy_count").getValue().toString()));

                    most_buyed.add(iteminfo);
                }
                Collections.sort(most_buyed);
                most_buyed_adapter =new BuyedAdapter(getActivity().getApplicationContext(),most_buyed);
                buyrecycler.setAdapter(most_buyed_adapter);
                most_buyed_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    private void artrecycler() {
//        artrecycler.setHasFixedSize(true);
//        artrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//
//
//        ArrayList<ArticlesHelper> location2 = new ArrayList<>();
//        location2.add(new ArticlesHelper(R.drawable.img, "obj1"));
//
//        adapter2 = new ArticlesAdapter(location2);
//        artrecycler.setAdapter(adapter2);
//    }

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


    public void view_buy() {
        Intent intent = new Intent(getActivity(), most_buyed.class);
        startActivity(intent);

    }

//    public void view_art() {
//        Intent intent = new Intent(getActivity(), articles_all.class);
//        startActivity(intent);
//
//    }


}

