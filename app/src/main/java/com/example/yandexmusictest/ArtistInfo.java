package com.example.yandexmusictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        loadData();
    }

    private void loadData() {
        Intent intent = getIntent();
        String bigImage = intent.getStringExtra("image");
        String numbers = intent.getStringExtra("numbers");
        String biography = intent.getStringExtra("biography");
        //String genres = data.getStringExtra("genres");
        //tv_genres.setText(genres);
        tv_number.setText(numbers);
        tv_biography.setText(biography);
        Glide.with(this).load(bigImage).into(bigCover);//подгружаем фотографию
    }

    private void initElements() {
        bigCover = (ImageView)findViewById(R.id.iv_full_photo);
        tv_genres = (TextView)findViewById(R.id.tv_genres_full);
        tv_number = (TextView)findViewById(R.id.tv_number_full);
        tv_biography = (TextView)findViewById(R.id.tv_biography);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

        }
    }
}
