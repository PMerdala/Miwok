package com.example.pmerdala.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by merdala on 2017-11-02.
 */

public abstract class CategoryFragment extends Fragment {

    private final int background;
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener completionListener;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListemer;
    private AudioManager audioManager;
    private ImageView lastSelect = null;

    public CategoryFragment(int background){
        super();
        this.background = background;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.word_list,container,false);
        List<Word> words = getWords();
        makeWordAdapter(words,rootView);
        prepareMediaPlayer();
        return rootView;
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

    private void makeWordAdapter(List<Word> words,View rootView) {
        WordArrayAdapter itemsAdapter = new WordArrayAdapter(rootView.getContext(), background, words);
        final ListView parentView = (ListView)rootView.findViewById(R.id.list_item_view);
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
            mediaPlayer = MediaPlayer.create(getActivity(), soundResourceId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completionListener);
        }
    }
}
