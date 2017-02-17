package info.androidhive.retrofit.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.fragments.descriptionFragment;
import info.androidhive.retrofit.model.Movie;
import info.androidhive.retrofit.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.Response;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> implements View.OnClickListener {

    private List<Movie> movies;
    private View.OnClickListener listener;


    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView caratula;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            //movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            caratula = (ImageView) v.findViewById(R.id.caratula);
        }
    }

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        view.setOnClickListener(this);
        MovieViewHolder mvh = new MovieViewHolder(view);

        return mvh;
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        ImageView bmImage;

        public ImageDownloader(ImageView bmImage){ this.bmImage = bmImage;}
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap miIcon = null;
            try {
                InputStream in = new URL(url).openStream();
                miIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
            return miIcon;
        }
        protected void onPostExecute(Bitmap result) {bmImage.setImageBitmap(result);}
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        Movie movie = movies.get(position);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        //holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        new ImageDownloader(holder.caratula).execute("https://image.tmdb.org/t/p/w500" + movie.getPosterPath());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response){





    }
}