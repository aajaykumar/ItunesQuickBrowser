package com.ajay.itunesquickbrowser.homescreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.homescreen.controller.HomeScreenController;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.Entity;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.network.SearchResponseReceivedEvent;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class HomeScreenActivity extends AppCompatActivity {

    private final HomeScreenController homeScreenController = new HomeScreenController();

    @Inject
    EventBus eventBus;

    @Inject
    @Nullable
    SearchResponse searchResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.getInstance().getApplicationComponent().inject(this);

        eventBus.register(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //TODO Write search logic here
                homeScreenController.fetchSearchResponse("bird", Entity.ALL);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (eventBus != null) {
            eventBus.unregister(this);
        }
    }

    public void onEvent(final SearchResponseReceivedEvent event) {

    }
}
