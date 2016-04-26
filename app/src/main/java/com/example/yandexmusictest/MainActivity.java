package com.example.yandexmusictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yandexmusictest.adapters.ArtistAdapter;
import com.example.yandexmusictest.models.Artist;
import com.orm.SugarContext;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    YandexTask task;
    ListView artistList;
    List<Artist> artists;
    EditText filter;
    ArtistAdapter artistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        initElements();
        task =new YandexTask();
        task.execute(this); //вызываем YandexTask(подгрузка данных из json)
        artists = Artist.listAll(Artist.class); //получаем все элементы из бд
        initAdapter();
        artistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //обрабатываем нажатие на элемент списка
                Intent intent = new Intent(MainActivity.this, ArtistInfo.class);
                intent.putExtra("image", artistAdapter.getItem(position).coverBig.toString()); //передаем данные из бд в другое активити
                intent.putExtra("name", artistAdapter.getItem(position).name.toString());
                intent.putExtra("genres", artistAdapter.getItem(position).genres.toString());
                String numbers = artistAdapter.getItem(position).albums + " альбомов, " + artistAdapter.getItem(position).tracks + " треков.";
                intent.putExtra("numbers", numbers);
                intent.putExtra("biography", artistAdapter.getItem(position).description.toString());
                startActivity(intent); //запускаем активити с информацией о певце
            }
        });
    }

    private void initElements() {
        filter = (EditText)findViewById(R.id.filter_edit_text);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                artistAdapter.getFilter().filter(s.toString());
                artistAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initAdapter() {
        artistAdapter = new ArtistAdapter(this,0, Artist.listAll(Artist.class));
        artistList = ((ListView) findViewById(R.id.lv_artists));
        artistList.setAdapter(artistAdapter);
        artistList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
                {

                }
            }
        });
        artistAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        task.cancel(true); //останавливаем операцию YandexTask
    }

    public void onJSONParsed() {
        initAdapter();
    }


}
