package com.example.pmerdala.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by PMerd_000 on 2017-10-27.
 */

public abstract class CategoryActivity extends AppCompatActivity {

    private final int backgroud;
    private MediaPlayer mediaPlayer;

    public CategoryActivity(int backgroud) {
        super();
        this.backgroud = backgroud;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        List<Word> words = getWords();
        makeWordAdapter(words);
    }

    protected abstract List<Word> getWords();

    private void makeWordAdapter(List<Word> words) {
        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, backgroud, words);
        ListView parentView = (ListView) findViewById(R.id.list_item_view);
        parentView.setAdapter(itemsAdapter);
        parentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word) parent.getAdapter().getItem(position);
                if (word.hasSound()) {
                    play(word.getSoundResourceId());
                }
            }
        });

    }

    private void play(int soundResourceId) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, soundResourceId);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    mediaPlayer = null;
                }
            });
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Trwa Odtwarzanie poprzedniej informacji", Toast.LENGTH_SHORT).show();
        }

    }

}
