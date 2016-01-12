package com.ajay.itunesquickbrowser;

import com.ajay.itunesquickbrowser.homescreen.HomeScreenActivity;
import com.ajay.itunesquickbrowser.homescreen.controller.HomeScreenController;
import com.ajay.itunesquickbrowser.model.SearchResponseModule;
import com.ajay.itunesquickbrowser.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ajay on 1/11/16.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, SearchResponseModule.class})
public interface ApplicationComponent {

    void inject(HomeScreenActivity activity);

    void inject(HomeScreenController controller);

}
