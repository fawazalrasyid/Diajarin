package com.diajarin.id;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diajarin.id.Models.Ads;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DiajarinTesMulaiTes extends AppCompatActivity {

    String tes1url, tes2url, tes3url, tes4url, tes5url, img;
    private ImageView imgTes;

    DatabaseReference reference;

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
        tes1url = intent.getStringExtra("tes1url");
        tes2url = intent.getStringExtra("tes2url");
        tes3url = intent.getStringExtra("tes3url");
        tes4url = intent.getStringExtra("tes4url");
        tes5url = intent.getStringExtra("tes5url");
        img = intent.getStringExtra("img");

        imgTes = findViewById(R.id.img_tes);
        Picasso.get()
                .load(img)
                //.resize(130, 110)
                .into(imgTes);


        FrameLayout tes1 = (FrameLayout)findViewById(R.id.tes1);
        tes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiajarinTesMulaiTes.this, DiajarinTesWebView.class);
                i.putExtra("url", tes1url);
                startActivity(i);
            }
        });

        FrameLayout tes2 = (FrameLayout)findViewById(R.id.tes2);
        tes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiajarinTesMulaiTes.this, DiajarinTesWebView.class);
                i.putExtra("url", tes2url);
                startActivity(i);
            }
        });

        FrameLayout tes3 = (FrameLayout)findViewById(R.id.tes3);
        tes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiajarinTesMulaiTes.this, DiajarinTesWebView.class);
                i.putExtra("url", tes3url);
                startActivity(i);
            }
        });

        FrameLayout tes4 = (FrameLayout)findViewById(R.id.tes4);
        tes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiajarinTesMulaiTes.this, DiajarinTesWebView.class);
                i.putExtra("url", tes4url);
                startActivity(i);
            }
        });

    }

    // Kill Activity on Back Press
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
