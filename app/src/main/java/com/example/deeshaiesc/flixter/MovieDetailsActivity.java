package com.example.deeshaiesc.flixter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.deeshaiesc.flixter.models.Movie;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by deeshaiesc on 6/22/17.
 */

public class MovieDetailsActivity extends AppCompatActivity
{
    //the movie to display

    Movie movie;



    //the view objects
    @BindView(R.id.TVtitle) TextView TVtitle;
    @BindView(R.id.TVoverview) TextView TVoverview;
    @BindView(R.id.RBvoteaverage) RatingBar RBvoteaverage;
    @BindView(R.id.RDview) TextView RDview;
    @BindView(R.id.RDtext) TextView RDtext;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //resolve the view objects
        ButterKnife.bind(this);

        //unwrap the movie passed in the via intent, using  its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '$s", movie.getTitle()));

        //set the title and overview
        TVtitle.setText(movie.getTitle());
        TVoverview.setText(movie.getOverview());
        RDview.setText(movie.getReleaseDate());

        //vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = (float) movie.getVoteAverage();
        RBvoteaverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);


    }

}


