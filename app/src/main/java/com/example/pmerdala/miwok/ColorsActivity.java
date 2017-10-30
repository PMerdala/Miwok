package com.example.pmerdala.miwok;

import java.util.ArrayList;

public class ColorsActivity extends CategoryActivity {

    public ColorsActivity(){
        super(R.color.category_colors);
    }
    @Override
    protected ArrayList<Word> getWords(){
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red","weṭeṭṭi",R.drawable.color_red));
        words.add(new Word("green","chokokki",R.drawable.color_green));
        words.add(new Word("brown","ṭakaakki",R.drawable.color_brown));
        words.add(new Word("gray","ṭopoppi",R.drawable.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black));
        words.add(new Word("white","kelelli",R.drawable.color_white));
        words.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow));
        return words;
    }

}
