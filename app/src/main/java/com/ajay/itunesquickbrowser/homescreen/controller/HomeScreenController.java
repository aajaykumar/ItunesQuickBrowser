package com.ajay.itunesquickbrowser.homescreen.controller;

import android.app.Application;
import android.support.annotation.Nullable;

import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.Entity;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.network.ItunesSearchAPIService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajay on 1/11/16.
 */
public class HomeScreenController {

    @Inject
    Application application;

    @Inject
    ItunesSearchAPIService itunesSearchAPIService;

    @Inject
    @Nullable
    SearchResponse searchResponse;

    public HomeScreenController() {
        Injector.getInstance().getApplicationComponent().inject(this);
    }

    public void fetchSearchResponse(final String searchTerm, final Entity entity) {
        final Call<SearchResponse> searchResponseCall =
                itunesSearchAPIService.getSearchResponse(searchTerm, entity.getValue());
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(final Response<SearchResponse> response) {
                if (response != null && response.isSuccess()) {
                    Injector.getInstance().initializeApplicationComponent(application, response.body());

                    Injector.getInstance().getApplicationComponent().inject(HomeScreenController.this);

                }
            }

            @Override
            public void onFailure(final Throwable t) {
                /*Snackbar.make(view, ":[", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }
}
