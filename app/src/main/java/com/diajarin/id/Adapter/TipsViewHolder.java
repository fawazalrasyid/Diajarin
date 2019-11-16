package com.diajarin.id.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.diajarin.id.Models.Tips;
import com.diajarin.id.R;
import com.squareup.picasso.Picasso;

public class TipsViewHolder extends RecyclerView.ViewHolder {

    public TextView tvJudul;
    public ImageView ivImg;
    public CardView cvvid;

    public TipsViewHolder(View itemView) {
        super(itemView);
        tvJudul = itemView.findViewById(R.id.tv_judul);
        ivImg = itemView.findViewById(R.id.iv_thumbnail);
        cvvid = itemView.findViewById(R.id.cardvid);

    }

    public void bindToCard(Tips tips, View.OnClickListener onClickListener) {
        tvJudul.setText(tips.judul);
        Picasso.get()
                .load(tips.thumbnail)
                //.resize(130, 110)
                .into(ivImg);
        ivImg.setOnClickListener(onClickListener);
        cvvid.setOnClickListener(onClickListener);

    }

}
