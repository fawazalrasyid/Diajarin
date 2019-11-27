package com.diajarin.id.Adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.diajarin.id.Models.Slider;
import com.diajarin.id.R;
import com.squareup.picasso.Picasso;

public class SliderViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivImg;
    public CardView cvSlider;

    public SliderViewHolder(View itemView) {
        super(itemView);
        ivImg = itemView.findViewById(R.id.img);

    }

    public void bindToCard(Slider slider, View.OnClickListener onClickListener){
        Picasso.get()
                .load(slider.img)
                .into(ivImg);
        //cvSlider.setOnClickListener(onClickListener);

    }

}
