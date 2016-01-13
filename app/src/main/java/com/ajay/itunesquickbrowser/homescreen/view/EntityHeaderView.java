package com.ajay.itunesquickbrowser.homescreen.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.homescreen.adapter.EntitySpinnerAdapter;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.userstate.UserState;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ajay on 1/12/16.
 */
public final class EntityHeaderView extends CardView {

    @Inject
    UserState userState;

    @Bind(R.id.entity_spinner)
    Spinner entitySpinner;

    public EntityHeaderView(final Context context) {
        super(context);
    }

    public EntityHeaderView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public EntityHeaderView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        Injector.getInstance().getApplicationComponent().inject(this);

        ButterKnife.bind(this);

        entitySpinner.setAdapter(new EntitySpinnerAdapter(getContext()));

        entitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                userState.updateEntityPosition(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                userState.updateEntityPosition(0); // 0 -> All is the default selected option
            }
        });

        entitySpinner.setSelection(userState.getEntityPosition());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        ButterKnife.unbind(this);
    }
}
