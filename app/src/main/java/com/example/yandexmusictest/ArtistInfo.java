package com.example.yandexmusictest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ArtistInfo extends AppCompatActivity {
    ImageView bigCover;
    TextView tv_genres,tv_number,tv_biography;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        initElements();
        loadData(); // получаем данные
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);
        String bigImage = intent.getStringExtra("image");
        String numbers = intent.getStringExtra("numbers");
        String biography = intent.getStringExtra("biography");
        String genres = intent.getStringExtra("genres");
        tv_genres.setText(genres);
        tv_number.setText(numbers);
        tv_biography.setText(biography);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        Glide.with(this).load(bigImage).override(width,400).centerCrop().into(bigCover);//подгружаем фотографию
    }

    private void initElements() {
        bigCover = (ImageView)findViewById(R.id.iv_full_photo);
        tv_genres = (TextView)findViewById(R.id.tv_genres_full);
        tv_number = (TextView)findViewById(R.id.tv_number_full);
        tv_biography = (TextView)findViewById(R.id.tv_biography);
    }
}
