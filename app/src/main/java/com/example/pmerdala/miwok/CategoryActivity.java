package com.example.pmerdala.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
    private MediaPlayer.OnCompletionListener completionListener;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListemer;
    private AudioManager audioManager;
    private ImageView lastSelect = null;

    public CategoryActivity(int backgroud) {
        super();
        this.backgroud = backgroud;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        List<Word> words = getWords();
        makeWordAdapter(words);
        prepareMediaPlayer();
    }

    protected abstract List<Word> getWords();

    private void prepareMediaPlayer() {
        completionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mediaPlayer != null) {
                    releaseMediaPlayer();
                }
            }
        };
        audioFocusChangeListemer = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                        changePlayIcon(R.drawable.ic_play_arrow_black_24dp);
                    }
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releaseMediaPlayer();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    if (mediaPlayer != null) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        changePlayIcon(R.drawable.ic_play_arrow_white_24dp);
                    }
                }
            }
        };
    }

    private void makeWordAdapter(List<Word> words) {
        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, backgroud, words);
        final ListView parentView = (ListView) findViewById(R.id.list_item_view);
        parentView.setAdapter(itemsAdapter);
        parentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = (Word) parent.getAdapter().getItem(position);
                if (word.hasSound()) {
                    play(word.getSoundResourceId(),view);
                }
            }
        });

    }

    private synchronized void releaseMediaPlayer() {
        audioManager.abandonAudioFocus(audioFocusChangeListemer);
        changePlayIcon(R.drawable.ic_play_arrow_white_24dp);
        lastSelect=null;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void changePlayIcon(int iconResourceId){
        if (lastSelect != null) {
            lastSelect.setImageResource(iconResourceId);
        }
    }

    private void play(int soundResourceId,View view) {
        releaseMediaPlayer();
        if (view!=null) {
            lastSelect = (ImageView) view.findViewById(R.id.play_icon);
            changePlayIcon(R.drawable.ic_play_arrow_black_24dp);
        }
        int audioFocusState = audioManager.requestAudioFocus(audioFocusChangeListemer, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (audioFocusState == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mediaPlayer = MediaPlayer.create(this, soundResourceId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }
    }

}
