package com.example.pmerdala.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by PMerd_000 on 2017-10-27.
 */

public class WordArrayAdapter extends ArrayAdapter<Word> {

    private final static int resource = R.layout.list_item;
    private final static int miwok_text_view_id = R.id.miwok_text_view;
    private final static int default_text_view_id = R.id.default_text_view;
    private final static int image_view_id = R.id.image;
    private final static int translate_word_layout = R.id.list_word_translation_layout;
    private final static int play_icon_id = R.id.play_icon;
    private final int backgroudColorResourceId;

    public WordArrayAdapter(@NonNull Context context, int backgroudColorResourceId, @NonNull List<Word> objects) {
        super(context, 0, objects);
        this.backgroudColorResourceId = backgroudColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        makeCustomStyle(listItemView);
        Word word = getItem(position);
        changeWord(listItemView, word);
        return listItemView;
    }

    private void changeWord(View view, final Word word) {
        TextView miwokTextView = (TextView) view.findViewById(miwok_text_view_id);
        TextView defaultTextView = (TextView) view.findViewById(default_text_view_id);
        ImageView imageView = (ImageView) view.findViewById(image_view_id);
        miwokTextView.setText(word.getMiwokTranslation());
        defaultTextView.setText(word.getDefaultTranslation());
        if (imageView != null) {
            if (word.hasImage()) {
                imageView.setImageResource(word.getImageResourceId());
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }


    private void makeCustomStyle(View view) {
        LinearLayout translateLayout = (LinearLayout) view.findViewById(translate_word_layout);
        translateLayout.setBackgroundResource(backgroudColorResourceId);
        ImageView playIcon = (ImageView)view.findViewById(play_icon_id);
        playIcon.setBackgroundResource(backgroudColorResourceId);
        playIcon.setImageResource(R.drawable.ic_play_arrow_white_24dp);
    }
}
