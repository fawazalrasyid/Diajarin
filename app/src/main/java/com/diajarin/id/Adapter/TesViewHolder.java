package com.diajarin.id.Adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.diajarin.id.Models.MulaiTes;
import com.diajarin.id.R;

public class TesViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public FrameLayout frameLayout;

    public TesViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_text);
        frameLayout = itemView.findViewById(R.id.rv_tes);

    }

    public void bindToCard(MulaiTes tes, View.OnClickListener onClickListener){
        tvName.setText(tes.name);
        frameLayout.setOnClickListener(onClickListener);

    }

}
