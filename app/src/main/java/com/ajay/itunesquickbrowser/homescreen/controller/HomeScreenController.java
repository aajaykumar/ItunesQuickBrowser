package com.ajay.itunesquickbrowser.homescreen.controller;

import android.app.Application;
import android.support.annotation.Nullable;

import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.Entity;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.network.ItunesSearchAPIService;
import com.ajay.itunesquickbrowser.network.SearchResponseReceivedEvent;
import com.ajay.itunesquickbrowser.userstate.UserState;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ajay on 1/11/16.
 */
public class HomeScreenController {

    private Entity lastEntitySearched;

    private String lastSearchTermSearched;

    @Inject
    Application application;

    @Inject
    EventBus eventBus;

    @Inject
    ItunesSearchAPIService itunesSearchAPIService;

    @Inject
    @Nullable
    SearchResponse searchResponse;

    @Inject
    UserState userState;

    public HomeScreenController() {
        Injector.getInstance().getApplicationComponent().inject(this);
    }

    public boolean fetchSearchResponse(final String searchTerm) {
        final Entity entity = userState.getEntity();

        if (searchTerm.equals(lastSearchTermSearched) && entity.equals(lastEntitySearched)) {
            return false;
        }

        lastEntitySearched = entity;
        lastSearchTermSearched = searchTerm;

        final Call<SearchResponse> searchResponseCall = itunesSearchAPIService.getSearchResponse(searchTerm, entity.getValue());


        searchResponseCall.enqueue(new HttpCallback());

        return true;
    }

    private class HttpCallback implements Callback<SearchResponse> {
        @Override
        public void onResponse(final Response<SearchResponse> response) {
            if (response != null && response.isSuccess()) {
                Injector.getInstance().initializeApplicationComponent(application, response.body());

                Injector.getInstance().getApplicationComponent().inject(HomeScreenController.this);

                eventBus.post(new SearchResponseReceivedEvent(true, searchResponse.resultCount == 0));
            } else {
                eventBus.post(new SearchResponseReceivedEvent(false));
            }
        }

        @Override
        public void onFailure(final Throwable t) {
            eventBus.post(new SearchResponseReceivedEvent(false));
        }
    }
}
