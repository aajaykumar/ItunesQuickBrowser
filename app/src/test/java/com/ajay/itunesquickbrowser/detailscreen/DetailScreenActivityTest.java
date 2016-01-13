package com.ajay.itunesquickbrowser.detailscreen;

import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajay.itunesquickbrowser.BuildConfig;
import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.detailscreen.view.DetailScreenView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by ajay on 1/13/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class DetailScreenActivityTest {

    private DetailScreenActivity detailScreenActivity;

    @Before
    public void setup() {
        detailScreenActivity = Robolectric.setupActivity(DetailScreenActivity.class);
    }

    @Test
    public void testActivityChildViewsArePresent() {
        final Toolbar toolbar = (Toolbar) detailScreenActivity.findViewById(R.id.toolbar);
        assertNotNull(toolbar);

        final DetailScreenView detailScreenView = (DetailScreenView) detailScreenActivity.findViewById(R.id.detail_screen_view);
        assertNotNull(detailScreenView);

        final ImageView productImageView = (ImageView) detailScreenView.findViewById(R.id.product_image_view);
        assertNotNull(productImageView);

        final TextView nameTextView = (TextView) detailScreenView.findViewById(R.id.name_text_view);
        assertNotNull(nameTextView);

        final TextView artistTextView = (TextView) detailScreenView.findViewById(R.id.artist_text_view);
        assertNotNull(artistTextView);

        final TextView priceTextView = (TextView) detailScreenView.findViewById(R.id.price_text_view);
        assertNotNull(priceTextView);

        final TextView descTextView = (TextView) detailScreenView.findViewById(R.id.desc_text_view);
        assertNotNull(descTextView);

        final TextView kindTextView = (TextView) detailScreenView.findViewById(R.id.kind_text_view);
        assertNotNull(kindTextView);
    }

    @After
    public void tearDown() {
        detailScreenActivity = null;
    }
}
