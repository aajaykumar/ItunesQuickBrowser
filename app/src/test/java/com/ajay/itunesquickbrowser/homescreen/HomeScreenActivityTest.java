package com.ajay.itunesquickbrowser.homescreen;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.ajay.itunesquickbrowser.BuildConfig;
import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.detailscreen.DetailScreenActivity;
import com.ajay.itunesquickbrowser.homescreen.adapter.HomeScreenRecyclerViewAdapter;
import com.ajay.itunesquickbrowser.homescreen.view.EntityHeaderView;
import com.ajay.itunesquickbrowser.homescreen.view.HomeScreenRecyclerView;
import com.ajay.itunesquickbrowser.homescreen.view.NumOfResultsView;
import com.ajay.itunesquickbrowser.homescreen.view.ResultView;
import com.ajay.itunesquickbrowser.model.ParserLogicUnitTest;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ajay on 1/13/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeScreenActivityTest {

    private HomeScreenActivity homeScreenActivity;

    private SearchResponse searchResponse;

    @Before
    public void setup() {
        homeScreenActivity = Robolectric.setupActivity(HomeScreenActivity.class);

        searchResponse = new Gson().fromJson(ParserLogicUnitTest.SAMPLE_JSON, SearchResponse.class);
    }

    @Test
    public void testActivityChildViewsArePresent() {
        final Toolbar toolbar = (Toolbar) homeScreenActivity.findViewById(R.id.toolbar);
        assertNotNull(toolbar);

        final HomeScreenRecyclerView recyclerView = (HomeScreenRecyclerView) homeScreenActivity.findViewById(R.id.home_screen_recycler_view);
        assertNotNull(recyclerView);

        final ProgressBar progressBar = (ProgressBar) homeScreenActivity.findViewById(R.id.progress_bar);
        assertNotNull(progressBar);
    }

    @Test
    public void testRecyclerChildViewsAreInOrder() {
        final HomeScreenRecyclerViewAdapter adapter = Mockito.spy(HomeScreenRecyclerViewAdapter.class);
        adapter.setSearchResponseForTesting(searchResponse);

        assertTrue(adapter.getItemCount() > 1);

        final HomeScreenRecyclerView recyclerView = (HomeScreenRecyclerView) homeScreenActivity.findViewById(R.id.home_screen_recycler_view);
        recyclerView.setAdapter(adapter);

        final EntityHeaderView entityHeaderView = (EntityHeaderView) recyclerView.getChildAt(0);
        assertNotNull(entityHeaderView);

        final NumOfResultsView numOfResultsView = (NumOfResultsView) recyclerView.getChildAt(1);
        assertNotNull(numOfResultsView);

        final ResultView resultView = (ResultView) recyclerView.getChildAt(2);
        assertNotNull(resultView);
    }

    @Test
    public void testLaunchOfDetailScreen() {
        final HomeScreenRecyclerViewAdapter adapter = Mockito.spy(HomeScreenRecyclerViewAdapter.class);
        adapter.setSearchResponseForTesting(searchResponse);

        final HomeScreenRecyclerView recyclerView = (HomeScreenRecyclerView) homeScreenActivity.findViewById(R.id.home_screen_recycler_view);
        recyclerView.setAdapter(adapter);

        final ResultView resultView = (ResultView) recyclerView.getChildAt(2);
        assertNotNull(resultView);

        resultView.performClick();

        final Intent expectedIntent = new Intent(homeScreenActivity, DetailScreenActivity.class);

        final ShadowActivity shadowActivity = Shadows.shadowOf(homeScreenActivity);

        final Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

    @After
    public void tearDown() {
        homeScreenActivity = null;
    }
}
