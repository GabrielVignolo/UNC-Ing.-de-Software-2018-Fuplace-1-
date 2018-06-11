package com.example.usuario.brickbreaker;
import java.util.ArrayList;
import android.graphics.RectF;



public class Paddle {//implements Sujeto{

    // RectF es un objeto que contiene 4 coordenadas
    private RectF rect;

    // que tan larga y gruesa sera paddle
    private float length;
    private float height;
    Dificultad facil = new Facil();
    Dificultad normal = new Normal();
    Dificultad dificil = new Dificil();
    private ArrayList<Dificultad> dificultad;


    // X la parte mas de la izquierda
    private float x;

    // Y es la parte mas alta
    private float y;

    // velocidad de paddle
    private float paddleSpeed;

    // para que lados se movera
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    // se mueve o esta detenida
    private int paddleMoving = STOPPED;

    // constructor
    public Paddle(int screenX, int screenY){
        dificultad = new ArrayList<>();
        dificultad.add(facil);
        dificultad.add(normal);
        dificultad.add(dificil);

            length = dificultad.get(new MenuDificultad().getTmp()).lengthPaddle();
            height = dificultad.get(new MenuDificultad().getTmp()).heightPaddle();

            // pone paddle casi al medio
        x = screenX / 2;
        y = screenY - 150;

        rect = new RectF(x, y, x + length, y + height);

        // velocidad de paddle
        paddleSpeed = dificultad.get(new MenuDificultad().getTmp()).speedPaddle();
    }
     //getter para hacer el rectangulo que hace que sea visible en VistaBrickbreaker
    public RectF getRect(){
        return rect;
    }

    // metodo para cambiar direccion de paddle
    public void setMovementState(int state){
        paddleMoving = state;
    }

    // este metodo va a ser llamado en update() desde VistaBrickbreaker determina si debe moverse y cambia las coordenadas de rectF
    public void update(long fps){
        if(paddleMoving == LEFT){
            x = x - paddleSpeed / fps;
        }

        if(paddleMoving == RIGHT){
            x = x + paddleSpeed / fps;
        }

        rect.left = x;
        rect.right = x + length;
    }

}