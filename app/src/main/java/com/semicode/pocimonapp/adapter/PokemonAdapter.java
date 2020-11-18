package com.semicode.pocimonapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.semicode.pocimonapp.R;
import com.semicode.pocimonapp.data.model.Pokemon;

import java.util.ArrayList;
import java.util.List;


public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> {


    private List<Pokemon> dataList = new ArrayList<>();
    private LayoutInflater mInflater;
    Activity activity;
    Context context;

    // data is passed into the constructor
    public PokemonAdapter(Activity activity) {
        this.context = activity;
        this.mInflater = LayoutInflater.from(activity);

    }

    public void setDataList(List<Pokemon> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public Pokemon getItem(int postion) {
        return dataList.get(postion);
    }

    @Override
    public PokemonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pokemon_item, parent, false);
        return new PokemonHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonHolder holder, int position) {
        setData(holder, position);


    }


    private void setData(PokemonHolder holder, int position) {
        holder.textView.setText(dataList.get(position).getName());
        Glide.with(context).load(dataList.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class PokemonHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        private View view;

        PokemonHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pokemon_item_image);
            textView = itemView.findViewById(R.id.pokemon_item_text_view);
            view = itemView;

            context = itemView.getContext();
        }

    }
}