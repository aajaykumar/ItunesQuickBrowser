package com.ajay.itunesquickbrowser;

import android.app.Application;

import com.ajay.itunesquickbrowser.injection.Injector;

/**
 * Created by ajay on 1/11/16.
 */
public final class IQBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Injector.getInstance().initializeApplicationComponent(this);
    }
}
