package com.ajay.itunesquickbrowser.model;

/**
 * Created by ajay on 1/11/16.
 */
public enum Entity {

    ALL("All", ""),
    MOVIE("Movie", "movie"),
    PODCAST("Podcast", "podcast"),
    MUSIC("Music", "song"),
    MUSIC_VIDEO("Music Video", "musicVideo"),
    AUDIO_BOOK("Audio Book", "audiobook"),
    SHORT_FILM("Short Film", "shortFilm"),
    TV_SHOW("TV Show", "tvShow"),
    SOFTWARE("Software", "software"),
    EBOOK("Ebook", "ebook");

    private final String displayName, value;

    Entity(final String displayName, final String value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
