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
import android.widget.RelativeLayout;

import com.diajarin.id.Adapter.TipsViewHolder;
import com.diajarin.id.Models.Ads;
import com.diajarin.id.Models.Tips;
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

public class DetailDiajarinMenjadi extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Tips, TipsViewHolder> mAdapterTips;
    private RecyclerView mRecyclerTes, mRecyclerKerja, mRecyclerMenjadi;
    private RelativeLayout mManager;
    private android.widget.ImageView ImageView;

    DatabaseReference reference;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diajarinmenjadi);

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

        // Get Extra from MainActivity
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        mRecyclerTes = findViewById(R.id.rv_tips);
        GridLayoutManager gridLayoutTips= new GridLayoutManager(DetailDiajarinMenjadi.this, 1 );
        mRecyclerTes.setLayoutManager(gridLayoutTips);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query querytips = getQueryTes(mDatabase);

        FirebaseRecyclerOptions optionstips= new FirebaseRecyclerOptions.Builder<Tips>()
                .setQuery(querytips, Tips.class)
                .build();

        mAdapterTips = new FirebaseRecyclerAdapter<Tips, TipsViewHolder>(optionstips) {

            @Override
            public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new TipsViewHolder(inflater.inflate(R.layout.item_video, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull TipsViewHolder holder, int position, @NonNull final Tips model) {
                holder.bindToCard(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailDiajarinMenjadi.this, VideoPlayerActivity.class);
                        intent.putExtra("url", model.url);
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapterTips.notifyDataSetChanged();
        mRecyclerTes.setAdapter(mAdapterTips);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapterTips != null){
            mAdapterTips.startListening();
        }
    }

    private Query getQueryTes(DatabaseReference mDatabase){
        Query tes = mDatabase.child("DiajariMenjadi").child(String.valueOf(id)).child("video");
        return tes;
    }

    // Kill Activity on Back Press
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}