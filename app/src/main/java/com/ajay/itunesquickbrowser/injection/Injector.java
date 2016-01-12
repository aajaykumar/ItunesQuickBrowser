package com.ajay.itunesquickbrowser.injection;

import android.app.Application;

import com.ajay.itunesquickbrowser.AppModule;
import com.ajay.itunesquickbrowser.ApplicationComponent;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.model.SearchResponseModule;
import com.ajay.itunesquickbrowser.network.DaggerApplicationComponent;
import com.ajay.itunesquickbrowser.network.NetworkModule;

/**
 * Created by ajay on 1/11/16.
 */
public final class Injector {

    private static final Injector instance = new Injector();

    private AppModule appModule;

    private NetworkModule networkModule;

    private ApplicationComponent applicationComponent;

    private Injector() {
        // Prevent Initialization from outside
    }

    public static synchronized Injector getInstance() {
        return instance;
    }

    public void initializeApplicationComponent(final Application application) {
        initializeApplicationComponent(application, null);
    }

    public void initializeApplicationComponent(final Application application, final SearchResponse searchResponse) {
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(getAppModule(application))
                .networkModule(getNetworkModule())
                .searchResponseModule(new SearchResponseModule(searchResponse))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            throw new IllegalStateException("Call initializeApplicationComponent(...) to instantiate ApplicationComponent");
        }

        return applicationComponent;
    }

    private AppModule getAppModule(final Application application) {
        return appModule == null ? appModule = new AppModule(application) : appModule;
    }

    private NetworkModule getNetworkModule() {
        return networkModule == null ? networkModule = new NetworkModule() : networkModule;
    }


}
