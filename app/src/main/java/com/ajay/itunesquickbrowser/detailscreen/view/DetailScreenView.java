package com.ajay.itunesquickbrowser.detailscreen.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.Result;
import com.ajay.itunesquickbrowser.model.SearchResponse;
import com.ajay.itunesquickbrowser.support.RoundedTransformation;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ajay on 1/12/16.
 */
public final class DetailScreenView extends CoordinatorLayout {

    @Inject
    @Nullable
    SearchResponse searchResponse;

    @Bind(R.id.product_image_view)
    ImageView productImageView;

    @Bind(R.id.name_text_view)
    TextView nameTextView;

    @Bind(R.id.artist_text_view)
    TextView artistTextView;

    @Bind(R.id.price_text_view)
    TextView priceTextView;

    @Bind(R.id.desc_text_view)
    TextView descTextView;

    @Bind(R.id.kind_text_view)
    TextView kindTextView;


    public DetailScreenView(final Context context) {
        super(context);
    }

    public DetailScreenView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailScreenView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        ButterKnife.bind(this);

        updateProductImageHeight();

        Injector.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        ButterKnife.unbind(this);
    }

    public void loadData(final int index) {
        if (searchResponse == null) {
            showSnackError();

            return;
        }

        final Result result = searchResponse.getResult(index);

        if (result != null) {
            nameTextView.setText(result.trackName);
            artistTextView.setText(result.artistName);
            priceTextView.setText(getResources().getString(R.string.price, result.trackPrice));
            kindTextView.setText(result.kind);
            descTextView.setText(result.longDescription);

            Picasso.with(getContext())
                    .load(result.artworkUrl100)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new RoundedTransformation())
                    .into(productImageView);
        } else {
            showSnackError();
        }
    }

    private void updateProductImageHeight() {
        final int width = getResources().getDisplayMetrics().widthPixels;
        final int height = getResources().getDisplayMetrics().heightPixels;

        final ViewGroup.LayoutParams layoutParams = productImageView.getLayoutParams();
        layoutParams.height = Math.min(width, height);

        productImageView.setLayoutParams(layoutParams);
    }

    private void showSnackError() {
        Snackbar.make(this, R.string.error_message, Snackbar.LENGTH_INDEFINITE).show();
    }
}
