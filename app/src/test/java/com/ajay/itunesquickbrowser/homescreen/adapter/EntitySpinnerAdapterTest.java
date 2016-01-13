package com.ajay.itunesquickbrowser.homescreen.adapter;

import android.content.Context;
import android.os.Build;

import com.ajay.itunesquickbrowser.BuildConfig;
import com.ajay.itunesquickbrowser.model.Entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

/**
 * Created by ajay on 1/13/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class EntitySpinnerAdapterTest {

    Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
    }

    @Test
    public void testAdapterGetCount() {
        final EntitySpinnerAdapter adapter = new EntitySpinnerAdapter(context);
        assertTrue(adapter.getCount() == Entity.values().length);
    }
}
