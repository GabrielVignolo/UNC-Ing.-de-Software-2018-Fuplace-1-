package com.example.usuario.brickbreaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MenuDificultad extends AppCompatActivity{
     int tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dificultad);


        setupLaunchButton1();
        setupLaunchButton2();
        setupLaunchButton3();

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MenuDificultad.class);

    }

    public void setupLaunchButton1() {

        Button btn = (Button) findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp=0;
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                 //Intent intent = new Intent(Inicio.this, Menu.class);

                Intent intent = Brickbreaker.makeIntent(MenuDificultad.this);

                startActivity(intent);

            }

        });
    }

    public void setupLaunchButton2() {

        Button btn = (Button) findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp=1;
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                //Intent intent = new Intent(Inicio.this, Menu.class);

                Intent intent = Brickbreaker.makeIntent(MenuDificultad.this);
                startActivity(intent);
            }

        });
    }

    public void setupLaunchButton3() {

        Button btn = (Button) findViewById(R.id.button10);
        tmp=2;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp=2;
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                // Intent intent = new Intent(Inicio.this, Menu.class);
                Intent intent = Brickbreaker.makeIntent(MenuDificultad.this);

                startActivity(intent);

            }

        });

    }
    public int getTmp(){
        return tmp;
    }
}

