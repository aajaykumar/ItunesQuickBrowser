package com.ajay.itunesquickbrowser.homescreen.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.detailscreen.DetailScreenActivity;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.Result;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.network.SearchResponseReceivedEvent;
import com.ajay.itunesquickbrowser.support.RoundedTransformation;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by ajay on 1/12/16.
 */
public final class ResultView extends CardView {

    @Inject
    EventBus eventBus;

    @Inject
    @Nullable
    SearchResponse searchResponse;

    @Bind(R.id.thumbnail_image_view)
    ImageView thumbnailImageView;

    @Bind(R.id.name_text_view)
    TextView nameTextView;

    @Bind(R.id.kind_text_view)
    TextView kindTextView;

    @Bind(R.id.price_text_view)
    TextView priceTextView;

    private int dataPosition;

    public ResultView(final Context context) {
        super(context);
    }

    public ResultView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public ResultView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
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

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                startDetailActivity();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (eventBus != null && eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    @SuppressWarnings("unused")
    public void onEvent(final SearchResponseReceivedEvent event) {
        Injector.getInstance().getApplicationComponent().inject(this);
    }

    public void updateContent(final int newDataPosition) {
        dataPosition = newDataPosition;

        if (searchResponse != null && nameTextView != null) {
            final Result result = searchResponse.getResult(dataPosition);

            if (result != null) {
                nameTextView.setText(result.trackName);
                priceTextView.setText(getResources().getString(R.string.price, result.trackPrice));
                kindTextView.setText(result.kind);

                Picasso.with(getContext())
                        .load(result.artworkUrl100)
                        .transform(new RoundedTransformation())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(thumbnailImageView);
            }
        }
    }

    private void startDetailActivity() {
        final Intent intent = new Intent(getContext(), DetailScreenActivity.class);
        intent.putExtra(DetailScreenActivity.KEY_RESULT_INDEX, dataPosition);

        final Pair<View, String> toolBarTransition = Pair.create(((Activity) getContext()).findViewById(R.id.toolbar),
                getResources().getString(R.string.transition_tool_bar));
        final Pair<View, String> imageViewTransition = Pair.create((View) thumbnailImageView,
                getResources().getString(R.string.transition_image_content));
        final Pair<View, String> titleViewTransition = Pair.create((View) nameTextView,
                getResources().getString(R.string.transition_title));
        final Pair<View, String> priceViewTransition = Pair.create((View) priceTextView,
                getResources().getString(R.string.transition_price));
        final Pair<View, String> kindViewTransition = Pair.create((View) kindTextView,
                getResources().getString(R.string.transition_kind));

        //noinspection unchecked
        final ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation((Activity) getContext(), toolBarTransition, imageViewTransition,
                        titleViewTransition, priceViewTransition, kindViewTransition);

        getContext().startActivity(intent, options.toBundle());
    }
}
