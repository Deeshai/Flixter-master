package com.example.deeshaiesc.flixter.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


/**
 * Created by deeshaiesc on 6/21/17.
 */

@Parcel //annotation indicates class is Parceable

public class Movie
{

    //values from the API
    public String title;
    public String overview;
    public String posterPath; //only the path
    public String backdropPath;
    public double voteAverage;
    public String releaseDate;


    //no-arg, empty constructor required for Parceler
    public Movie()
    {

    }

    //initialize from JSON data
    public Movie(JSONObject object) throws JSONException
    {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
        releaseDate = object.getString("release_date");
    }

    public String getTitle()
    {
        return title;
    }

    public String getOverview()
    {
        return overview;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public double getVoteAverage()
    {
        return voteAverage;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }
}
