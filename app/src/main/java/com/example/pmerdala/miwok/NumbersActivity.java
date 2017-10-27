package com.example.pmerdala.miwok;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        String[] words = initEnglishNumbers();
        displayWords(words);
    }

    private String[] initEnglishNumbers(){
        String[] englishNumbers = new String[10];
        englishNumbers[0]="one";
        englishNumbers[1]="two";
        englishNumbers[2]="free";
        englishNumbers[3]="four";
        englishNumbers[4]="five";
        englishNumbers[5]="six";
        englishNumbers[6]="seven";
        englishNumbers[7]="eight";
        englishNumbers[8]="nine";
        englishNumbers[9]="ten";
        return englishNumbers;
    }

    private void displayWords(String[] words){
        for (int i=0;i<10;i++) {
            Log.v("NumbersActivity", "Word at index 0: " + words[i]);
        }
    }

}
