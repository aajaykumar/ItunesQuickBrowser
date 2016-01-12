package com.ajay.itunesquickbrowser.network;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by ajay on 1/11/16.
 */
@Module
public class NetworkModule {

    private static final int HTTP_CACHE_SIZE_MB = 10 * 1024 * 1024;

    private static final String BASE_URL = "https://itunes.apple.com";

    @Provides
    @Singleton
    Cache provideCache(final Application application) {
        return new Cache(application.getCacheDir(), HTTP_CACHE_SIZE_MB);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final Cache cache) {
        return new OkHttpClient.Builder().cache(cache).build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final Gson gson, final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ItunesSearchAPIService provideItunesSearchAPIService(final Retrofit retrofit) {
        return retrofit.create(ItunesSearchAPIService.class);
    }
}
