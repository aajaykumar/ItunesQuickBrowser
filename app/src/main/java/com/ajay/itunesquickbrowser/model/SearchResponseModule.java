package com.ajay.itunesquickbrowser.model;

import android.support.annotation.Nullable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ajay on 1/11/16.
 */
@Module
public class SearchResponseModule {

    private final SearchResponse searchResponse;

    public SearchResponseModule(final SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }

    @Provides
    @Singleton
    @Nullable
    SearchResponse provideSearchResponse() {
        return searchResponse;
    }
}
