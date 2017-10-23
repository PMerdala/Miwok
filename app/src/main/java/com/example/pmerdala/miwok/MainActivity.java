package com.example.pmerdala.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createOnClickListener(R.id.numbers, NumbersActivity.class);
        createOnClickListener(R.id.family,FamilyActivity.class);
        createOnClickListener(R.id.colors,ColorsActivity.class);
        createOnClickListener(R.id.phrases, PhrasesActivity.class);
    }

    private void createOnClickListener(int id,final Class<? extends AppCompatActivity> clazz){
        TextView textview = (TextView) findViewById(id);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,clazz);
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
            }
        });
    }
}
