package com.ajay.itunesquickbrowser.homescreen.controller;

import android.content.Context;
import android.os.Build;

import com.ajay.itunesquickbrowser.AppModule;
import com.ajay.itunesquickbrowser.BuildConfig;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.network.ItunesSearchAPIService;
import com.ajay.itunesquickbrowser.userstate.UserState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import retrofit2.Call;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ajay on 1/13/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeScreenControllerTest {
    
    private static final String SAMPLE_SEARCH_STRING_1 = "DOG"; 
    
    private static final String SAMPLE_SEARCH_STRING_2 = "CAT"; 

    private HomeScreenController homeScreenController;

    @Before
    public void setup() {
        homeScreenController = new HomeScreenController(true);

        homeScreenController.userState = new UserState(RuntimeEnvironment.application
                .getSharedPreferences(AppModule.SHARED_PREF_NAME, Context.MODE_PRIVATE));

        homeScreenController.itunesSearchAPIService = Mockito.spy(ItunesSearchAPIService.class);
        //noinspection unchecked
        Mockito.when(homeScreenController.itunesSearchAPIService.getSearchResponse("", "")).thenReturn((Call<SearchResponse>) Mockito.mock(Call.class));
    }

    @Test
    public void testFetchSearchResponseReturnsProperState() {
        homeScreenController.userState.updateEntityPosition(0);
        assertTrue(homeScreenController.fetchSearchResponse(SAMPLE_SEARCH_STRING_2));

        assertFalse(homeScreenController.fetchSearchResponse(SAMPLE_SEARCH_STRING_2));

        assertTrue(homeScreenController.fetchSearchResponse(SAMPLE_SEARCH_STRING_1));

        assertFalse(homeScreenController.fetchSearchResponse(SAMPLE_SEARCH_STRING_1));

        homeScreenController.userState.updateEntityPosition(1);
        assertTrue(homeScreenController.fetchSearchResponse(SAMPLE_SEARCH_STRING_1));

        assertFalse(homeScreenController.fetchSearchResponse(null));
    }

    @After
    public void breakdown() {
        homeScreenController = null;
    }
}
