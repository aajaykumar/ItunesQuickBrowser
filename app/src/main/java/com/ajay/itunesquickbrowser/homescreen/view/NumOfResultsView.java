package com.ajay.itunesquickbrowser.homescreen.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.network.SearchResponseReceivedEvent;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by ajay on 1/12/16.
 */
public final class NumOfResultsView extends CardView {

    @Bind(R.id.num_of_results_text_view)
    TextView numOfResultsTextView;

    @Inject
    EventBus eventBus;

    @Inject
    @Nullable
    SearchResponse searchResponse;

    public NumOfResultsView(final Context context) {
        super(context);
    }

    public NumOfResultsView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public NumOfResultsView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        Injector.getInstance().getApplicationComponent().inject(this);

        eventBus.register(this);
        ButterKnife.bind(this);

        updateContent();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (eventBus != null && eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
        ButterKnife.unbind(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(final SearchResponseReceivedEvent event) {
        Injector.getInstance().getApplicationComponent().inject(this);

        updateContent();
    }

    private void updateContent() {
        if (searchResponse != null && numOfResultsTextView != null) {
            numOfResultsTextView.setText(getResources().getString(R.string.no_of_results, searchResponse.resultCount));
        }
    }
}
