package com.semicode.pocimonapp.repository;

import androidx.lifecycle.LiveData;

import com.semicode.pocimonapp.data.api.PokemonApiService;
import com.semicode.pocimonapp.data.local.PokemonDao;
import com.semicode.pocimonapp.data.model.Pokemon;
import com.semicode.pocimonapp.data.model.PokemonRespone;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {

    PokemonApiService pokemonApiService;
    PokemonDao pokemonDao ;

    @Inject
    public Repository(PokemonApiService pokemonApiService,PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao =pokemonDao ;
    }

    public Observable<PokemonRespone> getPokemons() {

        return pokemonApiService.getPokemons();
    }
    public void insertPokemon(Pokemon pokemon){
        pokemonDao.insertPokemon(pokemon);
    }
    public void deletePokemon(String pokemonName){
        pokemonDao.deletePokemon(pokemonName);
    }
    public LiveData<List<Pokemon>> getFavPokemons() {
    return pokemonDao.getAllPokemon();
    }

}
