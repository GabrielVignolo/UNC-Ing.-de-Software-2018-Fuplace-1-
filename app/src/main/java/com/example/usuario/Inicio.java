package com.example.usuario.brickbreaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Inicio extends AppCompatActivity{

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        setupLaunchButton();

    }

    private void setupLaunchButton(){
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(Inicio.this, "Clicked 'launch second'.", Toast.LENGTH_SHORT)
                      //  .show();

               // Intent intent = new Intent(Inicio.this, Menu.class);
                Intent intent = Menu.makeIntent(Inicio.this);
                startActivity(intent);
            }
        });


        }
    }

