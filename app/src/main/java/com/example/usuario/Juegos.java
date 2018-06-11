package com.example.usuario.brickbreaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Juegos extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegos);

        setupLaunchButton1();
        setupLaunchButton2();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Juegos.class);

    }

    private void setupLaunchButton1() {
        Button btn = (Button) findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                // Intent intent = new Intent(Inicio.this, Menu.class);
                Intent intent = MenuDificultad.makeIntent(Juegos.this);
                startActivity(intent);
            }
        });
    }

    private void setupLaunchButton2(){
        Button btn = (Button) findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                // Intent intent = new Intent(Inicio.this, Menu.class);
                Intent intent = TutorialBrick.makeIntent(Juegos.this);
                startActivity(intent);
            }
        });


    }
}
