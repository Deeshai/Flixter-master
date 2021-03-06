package com.example.deeshaiesc.flixter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.deeshaiesc.flixter.models.Config;
import com.example.deeshaiesc.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by deeshaiesc on 6/21/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>
{

    //list of movies
    ArrayList<Movie> movies;

    //config needed for image urls
    Config config;

    //context for rendering
    Context context;

    //initialize with list
    public MovieAdapter(ArrayList<Movie> movies)
    {
        this.movies = movies;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    //creates and inflates a new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //get the context and create inflater
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //create the view using the item_movie layout
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        //return a new ViewHolder
        return new ViewHolder(movieView);
    }

    //binds and inflated view to a new item
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        //get the movie data at the specified position
        Movie movie = movies.get(position);

        //populate the view with the movie data
        holder.TVtitle.setText(movie.getTitle());
        holder.TVoverview.setText(movie.getOverview());

        //determine the current orientation
        boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        //build url for poster image
        String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());

        //if in portrait mode, load the poster image
        if (isPortrait)
        {
            imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        }
        else
        {
            //load the backdrop image
            imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
        }

        //get the correct placeholder and imageview for the current orientation
        int placeholderId = isPortrait ? R.drawable.flicks_movie_placeholder : R.drawable.flicks_backdrop_placeholder;
        ImageView imageView = isPortrait ? holder.IVposterimage : holder.IVbackdrop;

        //load image using glide
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 25, 0))
                .placeholder(placeholderId)
                .error(placeholderId)
                .into(imageView);

    }

    //returns the total number of items in the list
    @Override
    public int getItemCount()
    {
        return movies.size();
    }

    //create the viewholder as a static inner class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ///track view object
        @Nullable @BindView(R.id.IVposterimage) ImageView IVposterimage;
        @Nullable @BindView(R.id.IVbackdrop) ImageView IVbackdrop;
        @Nullable @BindView(R.id.TVtitle) TextView TVtitle;
        @Nullable @BindView(R.id.TVoverview) TextView TVoverview;

        public ViewHolder(View itemView)
        {
            super(itemView);

            ButterKnife.bind(this,itemView);

            //add this as the itemView's OnClickListener
            itemView.setOnClickListener(this);
        }

        //when the user clicks on a row, show MovieDetailsActivity for the selected movie
        @Override
        public void onClick(View v)
        {
            //gets item position
            int position = getAdapterPosition();

            //make sure the position is valid, i.e. actually exists in view
            if (position != RecyclerView.NO_POSITION)
            {
                //get the movie position, this won't work if the class is static
                Movie movie = movies.get(position);

                //create intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);

                //serialize the movie using a parceler, use its short name as key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));

                //show the activity
                context.startActivity(intent);
            }
        }
    }
}

