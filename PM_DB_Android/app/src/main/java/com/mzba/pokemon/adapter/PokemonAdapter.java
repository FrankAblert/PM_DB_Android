package com.mzba.pokemon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.mzba.pokemon.R;
import com.mzba.pokemon.entity.PokemonEntity;
import com.mzba.pokemon.widget.CustomFooterView;
import com.mzba.pokemon.widget.RatioImageView;

import java.util.List;

/**
 * Created by 06peng on 16/8/19.
 */
public class PokemonAdapter extends CommonRecyclerViewAdapter<PokemonEntity> {

    private Context mContext;

    public PokemonAdapter(Context context, List<PokemonEntity> data) {
        super(context, data);
        mContext = context;
        addFooterView(new CustomFooterView(context));
    }

    @Override
    public RecyclerViewAdapterItem initAdapterItem(ViewGroup viewGroup, int type) {
        return new PokemonViewItem(mContext, viewGroup, R.layout.pm_item);
    }

    public class PokemonViewItem extends RecyclerViewAdapterItem<PokemonEntity> {

        View cardView;
        RatioImageView imageView;
        TextView nameTv;
        TextView descriptionTv;

        public PokemonViewItem(Context context, ViewGroup viewGroup, int layoutResId) {
            super(context, viewGroup, layoutResId);
            imageView = getView(R.id.iv_pm_image);
            imageView.setOriginalSize(50, 50);
            nameTv = getView(R.id.tv_pm_name);
            descriptionTv = getView(R.id.tv_pm_description);
            cardView = getView(R.id.card_pm);
        }

        @Override
        public void initViews(PokemonEntity data, int position) {
            nameTv.setText(data.getName());
            descriptionTv.setText(data.getDescription());
            Glide.with(mContext)
                    .load(data.getImage())
                    .crossFade()
                    .fitCenter()
                    .placeholder(R.color.cardview_light_background)
                    .dontAnimate()
                    .into(imageView)
                    .getSize(new SizeReadyCallback() {
                        @Override
                        public void onSizeReady(int width, int height) {
                            if (!cardView.isShown()) {
                                cardView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
    }
}
