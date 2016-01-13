package com.ajay.itunesquickbrowser.userstate;

import android.content.SharedPreferences;

import com.ajay.itunesquickbrowser.model.Entity;

/**
 * Created by ajay on 1/12/16.
 */
public class UserState {

    public static final String KEY_ENTITY_POSITION = "EntityPosition";

    private final SharedPreferences sharedPreferences;

    public UserState(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public int getEntityPosition() {
        return sharedPreferences.getInt(KEY_ENTITY_POSITION, 0);
    }

    public void updateEntityPosition(final int entityPosition) {
        if (entityPosition < 0 || entityPosition >= Entity.values().length) {
            return;
        }

        sharedPreferences.edit().putInt(KEY_ENTITY_POSITION, entityPosition).apply();
    }

    public Entity getEntity() {
        return Entity.values()[getEntityPosition()];
    }
}
