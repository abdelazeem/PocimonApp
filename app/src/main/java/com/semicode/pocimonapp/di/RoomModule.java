package com.semicode.pocimonapp.di;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.semicode.pocimonapp.data.api.PokemonApiService;
import com.semicode.pocimonapp.data.local.PokemonDB;
import com.semicode.pocimonapp.data.local.PokemonDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RoomModule {

    @Provides
    @Singleton
    public static PokemonDB provideDB(Application application) {
        return Room.databaseBuilder(application, PokemonDB.class, "pokemon_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao provideDao(PokemonDB pokemonDB) {
        return pokemonDB.pokemonDao();

    }
}
