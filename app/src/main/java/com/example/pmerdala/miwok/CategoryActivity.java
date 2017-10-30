package com.example.pmerdala.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by PMerd_000 on 2017-10-27.
 */

public abstract class CategoryActivity extends AppCompatActivity {

    private final int backgroud;

    public CategoryActivity(int backgroud){
        super();
        this.backgroud = backgroud;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        List<Word> words = getWords();
        displayWords(words);
    }

    protected abstract List<Word> getWords();

    private void displayWords(List<Word> words) {
        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this, backgroud, words);
        ListView parentView = (ListView) findViewById(R.id.list_item_view);
        parentView.setAdapter(itemsAdapter);

    }

}
