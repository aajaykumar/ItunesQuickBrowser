package com.ajay.itunesquickbrowser.homescreen.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.ajay.itunesquickbrowser.homescreen.adapter.HomeScreenRecyclerViewAdapter;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.network.SearchResponseReceivedEvent;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by ajay on 1/12/16.
 */
public final class HomeScreenRecyclerView extends RecyclerView {

    @Inject
    EventBus eventBus;

    public HomeScreenRecyclerView(final Context context) {
        super(context);
    }

    public HomeScreenRecyclerView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeScreenRecyclerView(final Context context, @Nullable final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    {
        Injector.getInstance().getApplicationComponent().inject(this);
        eventBus.register(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new HomeScreenRecyclerViewAdapter());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (eventBus != null && eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    @SuppressWarnings("unused")
    public void onEvent(final SearchResponseReceivedEvent event) {
        setAdapter(new HomeScreenRecyclerViewAdapter());
    }
}
