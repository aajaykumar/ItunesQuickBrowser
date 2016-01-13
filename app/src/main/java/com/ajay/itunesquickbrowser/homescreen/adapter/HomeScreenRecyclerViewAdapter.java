package com.ajay.itunesquickbrowser.homescreen.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajay.itunesquickbrowser.R;
import com.ajay.itunesquickbrowser.homescreen.view.EntityHeaderView;
import com.ajay.itunesquickbrowser.homescreen.view.NumOfResultsView;
import com.ajay.itunesquickbrowser.homescreen.view.ResultView;
import com.ajay.itunesquickbrowser.injection.Injector;
import com.ajay.itunesquickbrowser.model.SearchResponse;

import javax.inject.Inject;

/**
 * Created by ajay on 1/12/16.
 */
public class HomeScreenRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Inject
    @Nullable
    SearchResponse searchResponse;

    public HomeScreenRecyclerViewAdapter() {
        super();

        Injector.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public int getItemViewType(final int position) {
        return ViewType.fromInt(position).viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (ViewType.fromInt(viewType)) {
            case ENTITY_HEADER:
                return new EntityHeaderViewHolder(layoutInflater.inflate(R.layout.home_screen_entity_header_view, parent, false));
            case NUM_OF_RESULTS:
                return new NumOfResultsViewHolder(layoutInflater.inflate(R.layout.home_screen_num_of_results_view, parent, false));
            default:
                return new ResultViewHolder(layoutInflater.inflate(R.layout.home_screen_result_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (ViewType.fromInt(position)) {
            case SEARCH_RESULTS:
                ((ResultViewHolder) holder).getView().updateContent(position - 2); // - 2 For EntityHeader and NumOfResults view
        }
    }

    @Override
    public int getItemCount() {
        final int entityHeaderViewCount = 1;

        final int numOfResultsViewCount = 1;

        final int searchResultsViewCount = (searchResponse == null) ? 0 : searchResponse.resultCount;

        // If searchresults are present we show it with numOfResults, else we just show the entity header
        return entityHeaderViewCount +
                (searchResultsViewCount > 0 ? numOfResultsViewCount + searchResultsViewCount : 0);
    }

    /*
     * This needs to be injected from TestInjector, creating which is out of scope for this quick project
     */
    public void setSearchResponseForTesting(final SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }

    public enum ViewType {
        ENTITY_HEADER(0),
        NUM_OF_RESULTS(1),
        SEARCH_RESULTS(2);

        private final int viewType;

        ViewType(final int viewType) {
            this.viewType = viewType;
        }

        public static ViewType fromInt(final int viewType) {
            switch (viewType) {
                case 0:
                    return ENTITY_HEADER;
                case 1:
                    return NUM_OF_RESULTS;
                default:
                    return SEARCH_RESULTS;
            }
        }

        public int toInt() {
            return viewType;
        }
    }

    public static class EntityHeaderViewHolder extends RecyclerView.ViewHolder {
        public EntityHeaderViewHolder(final View itemView) {
            super(itemView);

            itemView.setTag(ViewType.ENTITY_HEADER);
        }

        EntityHeaderView getView() {
            return (EntityHeaderView) itemView;
        }
    }

    public static class NumOfResultsViewHolder extends RecyclerView.ViewHolder {
        public NumOfResultsViewHolder(final View itemView) {
            super(itemView);

            itemView.setTag(ViewType.NUM_OF_RESULTS);
        }

        NumOfResultsView getView() {
            return (NumOfResultsView) itemView;
        }
    }

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        public ResultViewHolder(final View itemView) {
            super(itemView);

            itemView.setTag(ViewType.SEARCH_RESULTS);
        }

        ResultView getView() {
            return (ResultView) itemView;
        }
    }
}
