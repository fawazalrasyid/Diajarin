package com.diajarin.id;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diajarin.id.Models.Ads;
import com.diajarin.id.Models.Tes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailDiajarinTes extends AppCompatActivity {

    private TextView OverView, Profile;
    private ImageView imgProfile;

    private ProgressDialog progressDialog;
    DatabaseReference reference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diajrintes);

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

        // Deklarasi Item View
        OverView = (TextView)findViewById(R.id.text_overview);
        Profile = (TextView)findViewById(R.id.text_profile);
        imgProfile = (ImageView)findViewById(R.id.img_detail);


        // Get Extra from MainActivity
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        progressDialog = ProgressDialog.show(DetailDiajarinTes.this,
                null,
                "Memuat...",
                true,
                false);

        update();

    }

    private void update() {

        reference = FirebaseDatabase.getInstance().getReference().child("TesDetail").child(String.valueOf(id));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final Tes tes = dataSnapshot.getValue(Tes.class);

                OverView.setText(tes.getOverview());
                Profile.setText(tes.getProfile());
                Picasso.get()
                        .load(tes.getImg())
                        //.resize(130, 110)
                        .into(imgProfile);

                // Set OnClick
                final Button btn1 = (Button)findViewById(R.id.btn1);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DetailDiajarinTes.this, DiajarinTesTanya.class);
                        i.putExtra("tanyaurl", tes.getTanyaurl());
                        startActivity(i);


                    }
                });

                Button btn2 = (Button)findViewById(R.id.btn2);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DetailDiajarinTes.this, DiajarinTesTips.class);
                        i.putExtra("id", tes.getId());
                        startActivity(i);


                    }
                });

                Button btn3 = (Button)findViewById(R.id.btn3);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DetailDiajarinTes.this, DiajarinTesMulaiTes.class);
                        i.putExtra("img", tes.getImg());
                        i.putExtra("id", id);
                        startActivity(i);


                    }
                });

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

    }

    // Kill Activity on Back Press
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}