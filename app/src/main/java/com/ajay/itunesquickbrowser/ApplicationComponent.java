package com.ajay.itunesquickbrowser;

import com.ajay.itunesquickbrowser.detailscreen.view.DetailScreenView;
import com.ajay.itunesquickbrowser.homescreen.HomeScreenActivity;
import com.ajay.itunesquickbrowser.homescreen.adapter.HomeScreenRecyclerViewAdapter;
import com.ajay.itunesquickbrowser.homescreen.controller.HomeScreenController;
import com.ajay.itunesquickbrowser.homescreen.view.EntityHeaderView;
import com.ajay.itunesquickbrowser.homescreen.view.HomeScreenRecyclerView;
import com.ajay.itunesquickbrowser.homescreen.view.NumOfResultsView;
import com.ajay.itunesquickbrowser.homescreen.view.ResultView;
import com.ajay.itunesquickbrowser.model.SearchResponseModule;
import com.ajay.itunesquickbrowser.network.NetworkModule;
import com.ajay.itunesquickbrowser.userstate.UserState;

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

    void inject(HomeScreenRecyclerViewAdapter adapter);

    void inject(HomeScreenRecyclerView view);

    void inject(NumOfResultsView view);

    void inject(EntityHeaderView view);

    void inject(ResultView view);

    void inject(DetailScreenView view);

    void inject(UserState state);
}
