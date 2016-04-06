package com.example.yandexmusictest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yandexmusictest.R;
import com.example.yandexmusictest.models.Artist;

import java.util.List;

/**
 * Created by Виктор on 05.04.2016.
 */
public class ArtistAdapter extends ArrayAdapter {
    Activity context;
    List<Artist> artists;
    public ArtistAdapter(Context context, int resource, List<Artist> objects) {
        super(context, resource,objects);
        this.context = (Activity) context;
        this.artists = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item_artist,null);
        ((TextView) convertView.findViewById(R.id.name_tv)).setText(artists.get(position).name); // добавляем имя
        String genres = "";
        try{
            for (String genre :artists.get(position).genres){ //считываем жанры
                genres += genre + ",";
            }
        }catch (Exception ex){

        }
        ((TextView) convertView.findViewById(R.id.genres_tv)).setText(genres); //добавляем жанры
        String numbers = artists.get(position).albums+" альбомов, "+artists.get(position).tracks+" треков.";
        ((TextView) convertView.findViewById(R.id.number_tv)).setText(numbers); //добавляем количество альбомов и треков
        Glide.with(context).load(artists.get(position).coverSmall).into(
                (ImageView)convertView.findViewById(R.id.artist_photo_iv));//подгружаем фотографию

        return convertView;
    }
}
