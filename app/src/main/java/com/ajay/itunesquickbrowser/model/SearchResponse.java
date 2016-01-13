package com.ajay.itunesquickbrowser.model;

import java.util.ArrayList;

/**
 * Created by ajay on 1/11/16.
 */
public class SearchResponse {

    public int resultCount;

    public ArrayList<Result> results;

    public Result getResult(final int index) {

        if (results != null && index < results.size()) {
            return results.get(index);
        }

        return null;
    }
}
