package com.semicode.pocimonapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.semicode.pocimonapp.R;
import com.semicode.pocimonapp.adapter.PokemonAdapter;
import com.semicode.pocimonapp.data.model.Pokemon;
import com.semicode.pocimonapp.viewModels.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class Favorite extends AppCompatActivity {
    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.activity_favorite_rv);
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        SetUpSwipe();
        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getFavPokemon();
        viewModel.getFavPokemonList().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                adapter.setDataList(pokemons);
            }
        });
        Button button = findViewById(R.id.activity_favorite_btn_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Favorite.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SetUpSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int itemPosition = viewHolder.getAdapterPosition();
                Pokemon selectedPokemon = adapter.getItem(itemPosition);
                viewModel.deletePokemon(selectedPokemon.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(Favorite.this, "item delete", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}