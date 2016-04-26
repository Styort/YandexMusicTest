package com.example.yandexmusictest.models;

import com.orm.SugarRecord;

/**
 * Created by Виктор on 05.04.2016.
 */
public class Artist  extends SugarRecord{
    public int id;
    public String name; //имя
    public String genres; //жанр
    public int tracks; //количество треков
    public int albums; //количество альбомов
    public String description; //биография
    public String coverSmall; //маленькая картинка
    public String coverBig; //большая картинка

    public Artist(int albums, String coverBig, String coverSmall, String description, String genres, int id, String name, int tracks) {
        this.albums = albums;
        this.coverBig = coverBig;
        this.coverSmall = coverSmall;
        this.description = description;
        this.genres = genres;
        this.id = id;
        this.name = name;
        this.tracks = tracks;
    }

    public String getGenres() {
        return genres;
    }

    public String getName() {
        return name;
    }

    public Artist(){}
}
