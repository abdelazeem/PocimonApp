package com.semicode.pocimonapp.viewModels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.semicode.pocimonapp.data.model.Pokemon;
import com.semicode.pocimonapp.data.model.PokemonRespone;
import com.semicode.pocimonapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCache;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    Repository repository;
    MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    LiveData<List<Pokemon>> favPokemonList = new MutableLiveData<>();

    public LiveData<List<Pokemon>> getFavPokemonList() {
        return favPokemonList;
    }

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonRespone, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonRespone pokemonRespone) throws Throwable {
                        ArrayList<Pokemon> list = pokemonRespone.getResults();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }

    public void insertPokemon(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        repository.deletePokemon(pokemonName);
    }

    public void getFavPokemon() {
        favPokemonList = repository.getFavPokemons();
    }
}
