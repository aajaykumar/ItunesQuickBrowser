package com.ajay.itunesquickbrowser.homescreen.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ajay.itunesquickbrowser.BuildConfig;
import com.ajay.itunesquickbrowser.model.ParserLogicUnitTest;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ajay on 1/13/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeScreenRecyclerViewAdapterTest {

    private static final int EMPTY_ADAPTER_COUNT = 1;

    private static final int EXPECTED_ADAPTER_COUNT = 3;

    private static final int RECYCLER_VIEW_FIRST_CHILD = 0;

    private static final int RECYCLER_VIEW_SECOND_CHILD = 1;

    private static final int RECYCLER_VIEW_THIRD_CHILD = 2;

    private SearchResponse searchResponse;

    @Before
    public void setup() {
        searchResponse = new Gson().fromJson(ParserLogicUnitTest.SAMPLE_JSON, SearchResponse.class);
    }

    @Test
    public void testAdapterGetItemCount() {
        final HomeScreenRecyclerViewAdapter adapter = Mockito.spy(HomeScreenRecyclerViewAdapter.class);

        assertEquals(EMPTY_ADAPTER_COUNT, adapter.getItemCount());

        adapter.setSearchResponseForTesting(searchResponse);
        assertEquals(EXPECTED_ADAPTER_COUNT, adapter.getItemCount());
    }

    @Test
    public void testAdapterGetItemVIewType() {
        final HomeScreenRecyclerViewAdapter adapter = Mockito.spy(HomeScreenRecyclerViewAdapter.class);
        adapter.setSearchResponseForTesting(searchResponse);

        assertEquals(HomeScreenRecyclerViewAdapter.ViewType.ENTITY_HEADER.toInt(), adapter.getItemViewType(RECYCLER_VIEW_FIRST_CHILD));
        assertEquals(HomeScreenRecyclerViewAdapter.ViewType.NUM_OF_RESULTS.toInt(), adapter.getItemViewType(RECYCLER_VIEW_SECOND_CHILD));
        assertEquals(HomeScreenRecyclerViewAdapter.ViewType.SEARCH_RESULTS.toInt(), adapter.getItemViewType(RECYCLER_VIEW_THIRD_CHILD));
    }

    //@Test Fix Mock of View
    public void testOnCreateViewHolder() {
        final HomeScreenRecyclerViewAdapter adapter = Mockito.spy(HomeScreenRecyclerViewAdapter.class);
        adapter.setSearchResponseForTesting(searchResponse);

        final ViewGroup parent = Mockito.spy(ViewGroup.class);
        parent.setLayoutDirection(View.LAYOUT_DIRECTION_INHERIT);
        parent.setLayoutParams(new ViewGroup.LayoutParams(100, 100));

        final ApplicationInfo applicationInfo = Mockito.spy(ApplicationInfo.class);
        Mockito.when(applicationInfo.targetSdkVersion).thenReturn(Build.VERSION_CODES.LOLLIPOP);

        final Context context = Mockito.spy(RuntimeEnvironment.application);
        Mockito.when(context.getApplicationInfo()).thenReturn(applicationInfo);

        Mockito.when(parent.getContext()).thenReturn(context);

        final RecyclerView.ViewHolder viewHolder = adapter.onCreateViewHolder(parent,
                HomeScreenRecyclerViewAdapter.ViewType.ENTITY_HEADER.toInt());
        assertNotNull(viewHolder);
        assertTrue(viewHolder instanceof HomeScreenRecyclerViewAdapter.EntityHeaderViewHolder);

        final RecyclerView.ViewHolder viewHolder2 = adapter.onCreateViewHolder(parent,
                HomeScreenRecyclerViewAdapter.ViewType.NUM_OF_RESULTS.toInt());
        assertNotNull(viewHolder);
        assertTrue(viewHolder2 instanceof HomeScreenRecyclerViewAdapter.NumOfResultsViewHolder);

        final RecyclerView.ViewHolder viewHolder3 = adapter.onCreateViewHolder(parent,
                HomeScreenRecyclerViewAdapter.ViewType.SEARCH_RESULTS.toInt());
        assertNotNull(viewHolder);
        assertTrue(viewHolder3 instanceof HomeScreenRecyclerViewAdapter.ResultViewHolder);
    }
}
