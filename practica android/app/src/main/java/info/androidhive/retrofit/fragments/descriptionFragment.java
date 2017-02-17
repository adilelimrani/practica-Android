package info.androidhive.retrofit.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.adapter.MoviesAdapter;
import info.androidhive.retrofit.model.Movie;
import info.androidhive.retrofit.model.MoviesResponse;
import info.androidhive.retrofit.rest.ApiClient;
import info.androidhive.retrofit.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class descriptionFragment extends Fragment {

    private int id;
    private TextView title;
    private TextView description;
    private ImageView caratula;
    private Movie movie;
    private ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_description2, container, false);
        TextView descripcion = (TextView) view.findViewById(R.id.tvDescription);
        descripcion.setText(getArguments().getString("descripcion"));
        TextView titulo = (TextView) view.findViewById(R.id.tvTitle);
        titulo.setText(getArguments().getString("titulo"));
        ListView reparto = (ListView) view.findViewById(R.id.reparto);

        ImageView caratula = (ImageView) view.findViewById(R.id.caratula);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies("7e8f60e325cd06e164799af1e317d7a7");
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {



            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
        return view;
    }

    public void consultaDescription(View v){

        description = (TextView) v.findViewById(R.id.tvDescription);
        title = (TextView) v.findViewById(R.id.tvTitle);
        caratula = (ImageView) v.findViewById(R.id.ivCaratula);

        description.setText(movie.getOverview());
        title.setText(movie.getTitle());
        new descriptionFragment.ImageDownloader(caratula).execute("https://image.tmdb.org/t/p/w500" + movie.getPosterPath());

    }
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

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


}
