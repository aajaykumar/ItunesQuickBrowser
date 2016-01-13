package com.ajay.itunesquickbrowser;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.ajay.itunesquickbrowser.userstate.UserState;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * Created by ajay on 1/11/16.
 */
@Module
public class AppModule {

    public static final String SHARED_PREF_NAME = "ItunesQuickBrowser";

    private final Application application;

    private final EventBus eventBus;

    public AppModule(final Application application) {
        this.application = application;
        eventBus = new EventBus();
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return eventBus;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference() {
        return application.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    UserState providesUserState(final SharedPreferences sharedPreferences) {
        return new UserState(sharedPreferences);
    }

}
