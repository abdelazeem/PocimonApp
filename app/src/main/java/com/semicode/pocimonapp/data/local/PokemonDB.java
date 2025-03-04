package com.semicode.pocimonapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.semicode.pocimonapp.data.model.Pokemon;

@Database(entities = Pokemon.class, version = 1, exportSchema = false)
public abstract class PokemonDB extends RoomDatabase {
    public abstract PokemonDao pokemonDao();
}
