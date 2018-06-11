package com.example.usuario.brickbreaker;

import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Random;

public class Ball {
    RectF rect;
    float xVelocity;
    float yVelocity;
    float ballWidth = 10;
    float ballHeight = 10;

    Dificultad facil = new Facil();
    Dificultad normal = new Normal();
    Dificultad dificil = new Dificil();
    private ArrayList<Dificultad> dificultad;

    public Ball(int screenX, int screenY){

        dificultad = new ArrayList<>();
        dificultad.add(facil);
        dificultad.add(normal);
        dificultad.add(dificil);

        xVelocity = dificultad.get(new MenuDificultad().getTmp()).bolaxVelocity();
        yVelocity = dificultad.get(new MenuDificultad().getTmp()).bolayVelocity();

        //pone la bola en el centro al fondo de la pantalla
        rect = new RectF();

    }

    public RectF getRect(){
        return rect;
    }

    public void update(long fps){
        rect.left = rect.left + (xVelocity / fps);
        rect.top = rect.top + (yVelocity / fps);
        rect.right = rect.left + ballWidth;
        rect.bottom = rect.top - ballHeight;
    }

    public void reverseYVelocity(){
        yVelocity = -yVelocity;
    }

    public void reverseXVelocity(){
        xVelocity = - xVelocity;
    }

    public void setRandomXVelocity(){
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }

    public void clearObstacleY(float y){
        rect.bottom = y;
        rect.top = y - ballHeight;
    }

    public void clearObstacleX(float x){
        rect.left = x;
        rect.right = x + ballWidth;
    }

    public void reset(int x, int y){
        rect.left = x / 2;
        rect.top = y - 20;
        rect.right = x / 2 + ballWidth;
        rect.bottom = y - 20 - ballHeight;
    }

}