package com.ajay.itunesquickbrowser.network;

/**
 * Created by ajay on 1/11/16.
 */
public class SearchResponseReceivedEvent {

    private final boolean isSuccessful;

    private final boolean isEmpty;

    public SearchResponseReceivedEvent(final boolean isSuccessful) {
        this(isSuccessful, false);
    }

    public SearchResponseReceivedEvent(final boolean isSuccessful, final boolean isEmpty) {
        this.isSuccessful = isSuccessful;
        this.isEmpty = isEmpty;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
