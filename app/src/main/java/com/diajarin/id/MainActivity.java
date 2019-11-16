package com.diajarin.id;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.diajarin.id.Adapter.CardViewHolder;
import com.diajarin.id.Models.Ads;
import com.diajarin.id.Models.Card;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Card, CardViewHolder> mAdapterTes;
    private FirebaseRecyclerAdapter<Card, CardViewHolder> mAdapterKerja;
    private FirebaseRecyclerAdapter<Card, CardViewHolder> mAdapterMenjadi;
    private RecyclerView mRecyclerTes, mRecyclerKerja, mRecyclerMenjadi;
    private RelativeLayout mManager;
    private android.widget.ImageView ImageView;

    private static final String TAG = "MainActivity";
    private AdView mAdView;

    DatabaseReference reference;

    String akun;

    CarouselView carouselView;
    int[] SliderTop = {R.drawable.slider1, R.drawable.slider2};
    int[] SliderBottom = {R.drawable.slider2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        //id = intent.getStringExtra("id" );

        // Ads Admob
        reference = FirebaseDatabase.getInstance().getReference().child("Ads");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Ads ads = dataSnapshot.getValue(Ads.class);

                View adContainer = findViewById(R.id.adMobView);

                AdView mAdView = new AdView(getBaseContext());
                mAdView.setAdSize(AdSize.BANNER);
                mAdView.setAdUnitId(ads.getBanner());
                ((RelativeLayout)adContainer).addView(mAdView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        // Bottom Navigation
        //onclick
        android.widget.Button setting = (Button) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        Button payment = (Button) findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PaymentsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
                finish();
            }
        });

        // Slider
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(SliderTop.length);
        carouselView.setImageListener(imageListenerTop);

        carouselView = (CarouselView) findViewById(R.id.carouselView2);
        carouselView.setPageCount(SliderBottom.length);
        carouselView.setImageListener(imageListenerBottom);


        // Profile Info

        ImageView = findViewById(R.id.img_profile);
        Picasso.get()
                .load(R.drawable.avatar)
                .into(ImageView);



        // Deklarai Grid Layout

        mRecyclerTes = findViewById(R.id.rv_tes);
        GridLayoutManager gridLayoutTes= new GridLayoutManager(MainActivity.this, 3 );
        mRecyclerTes.setLayoutManager(gridLayoutTes);

        mRecyclerKerja = findViewById(R.id.rv_kerja);
        GridLayoutManager gridLayoutKerja = new GridLayoutManager(MainActivity.this, 3);
        mRecyclerKerja.setLayoutManager(gridLayoutKerja);

        mRecyclerMenjadi = findViewById(R.id.rv_menjadi);
        GridLayoutManager gridLayoutMenjadi = new GridLayoutManager(MainActivity.this, 3);
        mRecyclerMenjadi.setLayoutManager(gridLayoutMenjadi);


        // Set up FirebaseRecyclerAdapter with the Query
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query querytes = getQueryTes(mDatabase);

        FirebaseRecyclerOptions optionstes = new FirebaseRecyclerOptions.Builder<Card>()
                .setQuery(querytes, Card.class)
                .build();

        mAdapterTes = new FirebaseRecyclerAdapter<Card, CardViewHolder>(optionstes) {

            @Override
            public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new CardViewHolder(inflater.inflate(R.layout.item_card, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CardViewHolder holder, int position, @NonNull final Card model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailDiajarinTesActivity.class);
                        intent.putExtra("id", model.id);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapterTes.notifyDataSetChanged();
        mRecyclerTes.setAdapter(mAdapterTes);


        // Set up FirebaseRecyclerAdapter with the Query
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query querykerja = getQueryKerja(mDatabase);

        FirebaseRecyclerOptions optionskerja = new FirebaseRecyclerOptions.Builder<Card>()
                .setQuery(querykerja, Card.class)
                .build();

        mAdapterKerja = new FirebaseRecyclerAdapter<Card, CardViewHolder>(optionskerja) {

            @Override
            public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new CardViewHolder(inflater.inflate(R.layout.item_card, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CardViewHolder holder, int position, @NonNull final Card model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailDiajarinKerja.class);
                        intent.putExtra("id", model.id);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapterKerja.notifyDataSetChanged();
        mRecyclerKerja.setAdapter(mAdapterKerja);


        // Set up FirebaseRecyclerAdapter with the Query
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query querymenjadi = getQueryMenjadi(mDatabase);

        FirebaseRecyclerOptions optionsmenjadi = new FirebaseRecyclerOptions.Builder<Card>()
                .setQuery(querymenjadi, Card.class)
                .build();

        mAdapterMenjadi = new FirebaseRecyclerAdapter<Card, CardViewHolder>(optionsmenjadi) {

            @Override
            public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new CardViewHolder(inflater.inflate(R.layout.item_card, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull CardViewHolder holder, int position, @NonNull final Card model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailDiajarinMenjadi.class);
                        intent.putExtra("id", model.id);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapterMenjadi.notifyDataSetChanged();
        mRecyclerMenjadi.setAdapter(mAdapterMenjadi);

    }

    // Image slider
    ImageListener imageListenerTop = new ImageListener() {
        @Override
        public void setImageForPosition(int position, android.widget.ImageView imageView) {
            imageView.setImageResource(SliderTop[position]);
        }
    };

    ImageListener imageListenerBottom = new ImageListener() {
        @Override
        public void setImageForPosition(int position, android.widget.ImageView imageView) {
            imageView.setImageResource(SliderBottom[position]);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapterTes != null){
            mAdapterTes.startListening();
        }
        if (mAdapterKerja != null){
            mAdapterKerja.startListening();
        }
        if (mAdapterMenjadi != null){
            mAdapterMenjadi.startListening();
        }
    }


    /*@Override
    public void onStop() {
        super.onStop();
        if (mAdapterTes != null) {
            mAdapterTes.stopListening();
        }
        if (mAdapterKerja != null) {
            mAdapterKerja.stopListening();
        }
        if (mAdapterMenjadi != null) {
            mAdapterMenjadi.stopListening();
        }
    }*/

    // Kill Activity on Back Press
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    private Query getQueryTes(DatabaseReference mDatabase){
        Query tes = mDatabase.child("Dashboard").child("diajarin_tes");
        return tes;
    }

    private Query getQueryKerja(DatabaseReference mDatabase){
        Query kerja = mDatabase.child("Dashboard").child("diajarin_kerja");
        return kerja;
    }

    private Query getQueryMenjadi(DatabaseReference mDatabase){
        Query kerja = mDatabase.child("Dashboard").child("diajarin_menjadi");
        return kerja;
    }



}
