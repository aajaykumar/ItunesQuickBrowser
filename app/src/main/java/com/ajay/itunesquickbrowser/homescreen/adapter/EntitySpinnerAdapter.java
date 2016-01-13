package com.ajay.itunesquickbrowser.homescreen.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.model.Entity;

/**
 * Created by ajay on 1/11/16.
 */
public class EntitySpinnerAdapter extends ArrayAdapter<Entity> {

    public EntitySpinnerAdapter(final Context context) {
        super(context, R.layout.home_screen_entity_drop_down_text_view, Entity.values());
    }
}
