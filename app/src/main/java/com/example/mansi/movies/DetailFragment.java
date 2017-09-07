package com.example.mansi.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {

    static TextView mDuration;
    static ImageView mTrailer1;
    static ImageView mTrailer2;
    static ImageView mTrailer3;
    static TextView mTrailerLabel;
    static LinearLayout mTrailer1Layout;
    static LinearLayout mTrailer2Layout;
    static LinearLayout mTrailer3Layout;
    TextView mTitle;
    TextView mSynopsis;
    ImageView mImageView;
    TextView mRatings;
    TextView mReleaseDate;
    int movieId;

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Movie movieToDisplay = (Movie) getActivity().getIntent()
                .getParcelableExtra(getString(R.string.open_detail_intent_key));


        mTitle = (TextView) rootView.findViewById(R.id.movie_title);
        mImageView = (ImageView) rootView.findViewById(R.id.detail_poster);
        mSynopsis = (TextView) rootView.findViewById(R.id.detail_synopsis);
        mDuration = (TextView) rootView.findViewById(R.id.detail_duration);
        mRatings = (TextView) rootView.findViewById(R.id.detail_ratings);
        mTrailerLabel = (TextView) rootView.findViewById(R.id.trailer_label);
        mReleaseDate = (TextView) rootView.findViewById(R.id.detail_release_date);
        mTrailer1 = (ImageView) rootView.findViewById(R.id.trailer_1_play_button);
        mTrailer2 = (ImageView) rootView.findViewById(R.id.trailer_2_play_button);
        mTrailer3 = (ImageView) rootView.findViewById(R.id.trailer_3_play_button);
        mTrailer1Layout = (LinearLayout) rootView.findViewById(R.id.trailer_1_layout);
        mTrailer2Layout = (LinearLayout) rootView.findViewById(R.id.trailer_2_layout);
        mTrailer3Layout = (LinearLayout) rootView.findViewById(R.id.trailer_3_layout);


        movieId = movieToDisplay.getId();

        //displaying title
        String title = movieToDisplay.getTitle();
        mTitle.setText(title);

        //displaying poster
        String relativeUrl = movieToDisplay.getPoster();
        if (relativeUrl != null && !TextUtils.isEmpty(relativeUrl))
            Picasso.with(getContext())
                    .load(Utility.getAbsoluteUrlForPoster(relativeUrl))
                    .into(mImageView);

        //display date
        String releaseDate = movieToDisplay.getReleaseDate();
        if (releaseDate != null && !TextUtils.isEmpty(releaseDate)) {
            mReleaseDate.setText(Utility.formatDate(releaseDate));
        }

        //displaying ratings
        if (movieToDisplay.getRatings() != -1) {
            String ratings = String.format(getString(R.string.format_ratings), movieToDisplay.getRatings());
            mRatings.setText(ratings);
        }

        //displaying synopsis
        String synopsis = movieToDisplay.getSynopsis();
        if (synopsis != null && !TextUtils.isEmpty(synopsis))
            mSynopsis.setText(synopsis);

        return rootView;
    }
}