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
    private MediaPlayer.OnCompletionListener completionListener ;

    public CategoryActivity(int backgroud) {
        super();
        this.backgroud = backgroud;
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        List<Word> words = getWords();
        makeWordAdapter(words);
        prepareMediaPlayer();
    }

    protected abstract List<Word> getWords();

    private void prepareMediaPlayer(){
        completionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mediaPlayer != null) {
                    releaseMediaPlayer();
                }
            }
        };
    }

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

    private synchronized void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void play(int soundResourceId) {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(this, soundResourceId);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(completionListener);
    }

}
