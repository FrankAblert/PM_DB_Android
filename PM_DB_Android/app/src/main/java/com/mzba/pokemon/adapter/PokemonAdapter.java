package com.mzba.pokemon.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzba.pokemon.R;
import com.mzba.pokemon.entity.PokemonEntity;
import com.mzba.pokemon.util.ImageDownloader;

import java.util.List;

/**
 * Created by 06peng on 16/8/19.
 */
public class PokemonAdapter extends CommonRecyclerViewAdapter<PokemonEntity> {

    private Context mContext;

    public PokemonAdapter(Context context, List<PokemonEntity> data) {
        super(context, data);
        mContext = context;
    }

    @Override
    public RecyclerViewAdapterItem initAdapterItem(ViewGroup viewGroup, int type) {
        return new PokemonViewItem(mContext, viewGroup, R.layout.support_simple_spinner_dropdown_item);
    }

    public class PokemonViewItem extends RecyclerViewAdapterItem<PokemonEntity> {

        ImageView imageView;
        TextView nameTv;

        public PokemonViewItem(Context context, ViewGroup viewGroup, int layoutResId) {
            super(context, viewGroup, layoutResId);
            imageView = (ImageView) getView(0);
            nameTv = (TextView) getView(0);
        }

        @Override
        public void initViews(PokemonEntity data, int position) {
            nameTv.setText(data.getName());
            ImageDownloader.getInstance().displayImage(mContext, data.getImage(), imageView, 100, 100, 0);
        }
    }
}
