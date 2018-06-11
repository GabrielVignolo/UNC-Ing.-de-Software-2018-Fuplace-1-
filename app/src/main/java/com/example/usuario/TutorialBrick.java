package com.example.usuario.brickbreaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class TutorialBrick extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorialbrick);

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, TutorialBrick.class);

    }


}
