package com.example.usuario.brickbreaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        setupLaunchButton1();
        setupLaunchButton2();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Menu.class);

    }

    private void setupLaunchButton1() {
        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                // Intent intent = new Intent(Inicio.this, Menu.class);
                Intent intent = Juegos.makeIntent(Menu.this);
                startActivity(intent);
            }
        });
    }

    private void setupLaunchButton2() {
        Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                //  .show();

                // Intent intent = new Intent(Inicio.this, Menu.class);
                Intent intent = Juegos.makeIntent(Menu.this);
                startActivity(intent);
            }
        });
    }
}


