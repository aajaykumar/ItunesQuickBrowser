package com.ajay.itunesquickbrowser.detailscreen;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.detailscreen.view.DetailScreenView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ajay on 1/11/16.
 */
public final class DetailScreenActivity  extends AppCompatActivity {

    public static final String KEY_RESULT_INDEX = "KeyResultIndex";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.detail_screen_view)
    DetailScreenView detailScreenView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_screen_activity);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            final int index = bundle.getInt(KEY_RESULT_INDEX, -1);

            if (index > -1) {
                detailScreenView.loadData(index);
                return;
            }
        }

        Snackbar.make(detailScreenView, R.string.error_message, Snackbar.LENGTH_INDEFINITE).show();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }
}
