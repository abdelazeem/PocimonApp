package com.semicode.pocimonapp.data.api;

import com.semicode.pocimonapp.data.model.PokemonRespone;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {
@GET("pokemon")
Observable<PokemonRespone> getPokemons();
}
