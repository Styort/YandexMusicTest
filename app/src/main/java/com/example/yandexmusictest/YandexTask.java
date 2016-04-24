package com.example.yandexmusictest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.yandexmusictest.models.Artist;
import com.google.common.io.CharStreams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Виктор on 05.04.2016.
 */
public class YandexTask extends AsyncTask {
    MainActivity activity;
    @Override
    protected Object doInBackground(Object[] params) {
        activity = (MainActivity) params[0];
        try {
            URL url = new URL("http://cache-default03d.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();//соединяемся с json
            String response = CharStreams.toString(new InputStreamReader(connection.getInputStream())); //преобразовываем полученные данные в строку
            JSONArray jArray = new JSONArray(response); //т.к. данные json в нашем случае это один большой массив, создаем JSONArray.
            Artist.deleteAll(Artist.class); //удаляем все элемент БД.
            for (int i = 0; i < jArray.length(); i++) { //считываем все элементы в json-массиве в бд.
                JSONObject object = jArray.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                JSONArray genresArr = object.getJSONArray("genres");
                String genres = "";
                for (int j = 0; j < genresArr.length(); j++) {
                    String genre = genresArr.getString(j);
                    if (j != genresArr.length() - 1) {
                        genres += genre + ",";
                    } else {
                        genres += genre;
                    }
                }
                int tracks = object.getInt("tracks");
                int albums = object.getInt("albums");
                String description = object.getString("description");
                JSONObject coversObj = object.getJSONObject("cover");
                String smallCover = coversObj.getString("small");
                String bigCover = coversObj.getString("big");


                Artist artist = new Artist(albums ,bigCover,smallCover,description,genres,id,name,tracks);

                artist.save(); //сохраняем данные в бд
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        activity.onJSONParsed();
    }
}
