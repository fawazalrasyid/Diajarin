package com.diajarin.id.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.diajarin.id.Models.Card;
import com.diajarin.id.R;
import com.squareup.picasso.Picasso;

public class CardViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvImg;
    public ImageView ivImg;

    public CardViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_text);
        tvImg = itemView.findViewById(R.id.tv_url);
        ivImg = itemView.findViewById(R.id.iv_img);

    }

    public void bindToCard(Card card, View.OnClickListener onClickListener){
        tvName.setText(card.name);
        tvImg.setText(card.img);
        Picasso.get()
                .load(card.img)
                //.resize(130, 110)
                .into(ivImg);
        ivImg.setOnClickListener(onClickListener);

    }

}
