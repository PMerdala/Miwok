package com.example.pmerdala.miwok;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        List<String> words = initEnglishNumbers();
        displayWords(words);
    }

    private List<String> initEnglishNumbers(){
        List<String> englishNumbers = new ArrayList<String>();
        englishNumbers.add("one");
        englishNumbers.add("two");
        englishNumbers.add("three");
        englishNumbers.add("four");
        englishNumbers.add("five");
        englishNumbers.add("six");
        englishNumbers.add("seven");
        englishNumbers.add("eight");
        englishNumbers.add("nine");
        englishNumbers.add("ten");
        return englishNumbers;
    }

    private void displayWords(List<String> words){
        LinearLayout parentView = (LinearLayout) findViewById(R.id.numbersView);
        for (int i=0;i<words.size();i++) {
            parentView.addView(createTextView(words.get(i)));
        }
    }

    private TextView createTextView(String word ){
        TextView textView = new TextView(getBaseContext());
        textView.setText(word);
        textView.setTextSize(24);
        textView.setPadding(16,16,16,16);
        textView.setBackgroundColor(getResources().getColor(R.color.category_numbers));
        return textView;
    }

}
