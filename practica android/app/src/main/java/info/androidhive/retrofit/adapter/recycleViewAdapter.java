package info.androidhive.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.model.Movie;

public class recycleViewAdapter extends ArrayAdapter<Movie> {

    private LayoutInflater inflater;
    private List<Movie> movieList;

    public recycleViewAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.list_item_movie, movies);
        movieList = movies;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_movie, null);

            new ViewHolder(convertView, movieList.get(position));

            return convertView;
        }


    static class ViewHolder {
        @BindView(R.id.caratula)
        ImageView caratula;
        @BindView(R.id.subtitle)
        TextView subtitle;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.rating_image)
        ImageView ratingImage;
        @BindView(R.id.rating)
        TextView rating;
        @BindView(R.id.movies_layout)
        LinearLayout moviesLayout;

        ViewHolder(View view, Movie movie) {
            ButterKnife.bind(this, view);
        }
    }
}
