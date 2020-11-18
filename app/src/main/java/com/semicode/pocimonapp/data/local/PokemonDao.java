package com.semicode.pocimonapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.semicode.pocimonapp.data.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {
    @Insert
    public void insertPokemon(Pokemon pokemon);

    @Query("delete from pokemon_table where name = :pokemonName")
    public void deletePokemon(String pokemonName);

    @Query("select * from pokemon_table")
    public LiveData<List<Pokemon>> getAllPokemon();
}
