package com.ajay.itunesquickbrowser.network;

import com.ajay.itunesquickbrowser.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ajay on 1/11/16.
 */
public interface ItunesSearchAPIService {

    @GET("/search")
    Call<SearchResponse> getSearchResponse(@Query("term") final String searchTerm,
                                           @Query("entity") final String entity);
}
