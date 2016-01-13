package com.ajay.itunesquickbrowser.userstate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.ajay.itunesquickbrowser.AppModule;
import com.ajay.itunesquickbrowser.BuildConfig;
import com.ajay.itunesquickbrowser.model.Entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ajay on 1/12/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class UserStateUnitTest {

    private SharedPreferences sharedPreferences;

    @Before
    public void mockSharedPreference() {
        sharedPreferences = RuntimeEnvironment.application
                .getSharedPreferences(AppModule.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    @Test
    public void testUserStateGetSet() {
        final int entityLength = Entity.values().length;

        final UserState userState = new UserState(sharedPreferences);

        userState.updateEntityPosition(-1);
        assertEquals(0, userState.getEntityPosition());
        assertEquals(Entity.ALL, userState.getEntity());
        assertNotEquals(Entity.EBOOK, userState.getEntity());

        userState.updateEntityPosition(entityLength);
        assertEquals(0, userState.getEntityPosition());
        assertEquals(Entity.ALL, userState.getEntity());
        assertNotEquals(Entity.SOFTWARE, userState.getEntity());

        userState.updateEntityPosition(1);
        assertEquals(1, userState.getEntityPosition());
        assertEquals(Entity.MOVIE, userState.getEntity());
        assertNotEquals(Entity.SHORT_FILM, userState.getEntity());
    }

    @After
    public void tearDown() {
        sharedPreferences = null;
    }
}
