package com.ajay.itunesquickbrowser.homescreen;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.homescreen.controller.HomeScreenController;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.network.SearchResponseReceivedEvent;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class HomeScreenActivity extends AppCompatActivity {

    private final HomeScreenController homeScreenController = new HomeScreenController();

    @Inject
    EventBus eventBus;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.getInstance().getApplicationComponent().inject(this);

        eventBus.register(this);

        setContentView(R.layout.home_screen_activity);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        handleSearchRequestIfApplicable(getIntent());
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);

        handleSearchRequestIfApplicable(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        updateSearchableInfo(menu);

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (eventBus != null && eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }

        ButterKnife.unbind(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(final SearchResponseReceivedEvent event) {
        if (!event.isSuccessful() || event.isEmpty()) {
            Snackbar.make(toolbar, R.string.no_result, Snackbar.LENGTH_LONG).show();
        }

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void updateSearchableInfo(final Menu menu) {
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    }

    private void handleSearchRequestIfApplicable(final Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);

            if (homeScreenController.fetchSearchResponse(query)) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
