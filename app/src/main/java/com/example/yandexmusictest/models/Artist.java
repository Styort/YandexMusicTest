package com.example.yandexmusictest.models;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Виктор on 05.04.2016.
 */
public class Artist  extends SugarRecord{
    public int id;
    public String name;
    public ArrayList<String> genres;
    public int tracks;
    public int albums;
    public String description;
    public String coverSmall;
    public String coverBig;

    public Artist(int albums, String coverBig, String coverSmall, String description, ArrayList<String> genres, int id, String name, int tracks) {
        this.albums = albums;
        this.coverBig = coverBig;
        this.coverSmall = coverSmall;
        this.description = description;
        this.genres = genres;
        this.id = id;
        this.name = name;
        this.tracks = tracks;
    }
    public Artist(){}
}
