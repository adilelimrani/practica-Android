package info.androidhive.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.retrofit.R;
import info.androidhive.retrofit.model.Cast;
import info.androidhive.retrofit.model.Movie;

public class repartoAdapter extends ArrayAdapter<Cast> {

    private LayoutInflater inflater;
    private List<Cast> castList;

    public repartoAdapter(Context context, List<Cast> cast) {
        super(context, R.layout.repart, cast);
        castList = cast;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.repart, null);

        new ViewHolder(convertView, castList.get(position));

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.nombreActor)
        TextView nombrActor;
        @BindView(R.id.personajeActor)
        TextView personajeActor;

        ViewHolder(View view, Cast cast) {
            ButterKnife.bind(this, view);
        }
    }
}