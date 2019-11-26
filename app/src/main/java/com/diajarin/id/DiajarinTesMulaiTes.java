package com.diajarin.id;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diajarin.id.Adapter.TesViewHolder;
import com.diajarin.id.Models.Ads;
import com.diajarin.id.Models.MulaiTes;
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

public class DiajarinTesMulaiTes extends AppCompatActivity {

    private ImageView imgTes;

    String img, id;

    DatabaseReference reference;
    DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<MulaiTes, TesViewHolder> mAdapter;
    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diajarintes_mulaites);

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

        Intent intent = getIntent();
        img = intent.getStringExtra("img");
        id = intent.getStringExtra("id");

        imgTes = findViewById(R.id.img_tes);
        Picasso.get()
                .load(img)
                //.resize(130, 110)
                .into(imgTes);

        mRecycler = findViewById(R.id.rv_tes);
        LinearLayoutManager gridLayout= new GridLayoutManager(DiajarinTesMulaiTes.this, 1 );
        mRecycler.setLayoutManager(gridLayout);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<MulaiTes>()
                .setQuery(query, MulaiTes.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<MulaiTes, TesViewHolder>(options) {

            @Override
            public TesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new TesViewHolder(inflater.inflate(R.layout.item_tes, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull TesViewHolder holder, int position, @NonNull final MulaiTes model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DiajarinTesMulaiTes.this, WebviewActivity.class);
                        intent.putExtra("url", model.url);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null){
            mAdapter.startListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("TesDetail").child(String.valueOf(id)).child("tes");
        return query;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
