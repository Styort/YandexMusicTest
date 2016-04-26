package com.example.yandexmusictest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yandexmusictest.R;
import com.example.yandexmusictest.models.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виктор on 05.04.2016.
 */
public class ArtistAdapter extends ArrayAdapter {
    Activity context;
    List<Artist> artists;
    List<Artist> mOriginalArtists;
    public ArtistAdapter(Context context, int resource, List<Artist> objects) {
        super(context, resource,objects);
        this.context = (Activity) context;
        this.artists = objects;
    }

    @Override
    public Artist getItem(int position) {
        return artists.get(position);
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // We implement here the filter logic

                if (mOriginalArtists == null) {
                    mOriginalArtists = new ArrayList<Artist>(artists); // saves the original data in mOriginalValues
                }

                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = mOriginalArtists;
                    results.count = mOriginalArtists.size();
                }
                else {
                    // We perform filtering operation
                    List<Artist> nPersonList = new ArrayList<Artist>();

                    for (Artist p : mOriginalArtists) {
                        if (p.getName().toUpperCase().startsWith(constraint.toString().toUpperCase())
                                ||p.getGenres().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                            nPersonList.add(p);
                    }

                    results.values = nPersonList;
                    results.count = nPersonList.size();

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    artists = (List<Artist>) results.values;
                    notifyDataSetChanged();
                }
            }

        };
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item_artist,null);
        ((TextView) convertView.findViewById(R.id.name_tv)).setText(artists.get(position).name); // добавляем имя
        String genres = artists.get(position).genres;
        ((TextView) convertView.findViewById(R.id.genres_tv)).setText(genres); //добавляем жанры
        String numbers = artists.get(position).albums+" альбомов, "+artists.get(position).tracks+" треков.";
        ((TextView) convertView.findViewById(R.id.number_tv)).setText(numbers); //добавляем количество альбомов и треков
        Glide.with(context).load(artists.get(position).coverSmall).into(
                (ImageView)convertView.findViewById(R.id.artist_photo_iv));//подгружаем фотографию
        return convertView;
    }
}
