package com.example.usuario.brickbreaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class Brickbreaker extends Activity {

    public static Intent makeIntent(Context context) {
        return new Intent(context, Brickbreaker.class);

    }
    // Vista del juego, incluye funcionamiento.

    VistaBrickbreaker vistaBrickbreaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        vistaBrickbreaker = new VistaBrickbreaker(this);
        setContentView(vistaBrickbreaker);

    }

    // implementacion.
    // clase interna.

    //implementamos runnable y usamos un hilo.
    class VistaBrickbreaker extends SurfaceView implements Runnable {

        Thread gameThread = null;

        //objeto para canvas y paint.
        SurfaceHolder ourHolder;

        volatile boolean playing;

        // pausamos en el inicio.
        boolean paused = true;

        Canvas canvas;
        Paint paint;

        // variable para los fotogramas por segundo.
        long fps;

        // para calcular los fps
        private long timeThisFrame;

        // tamaño de la pantalla en pixeles
        int screenX;
        int screenY;

        Paddle paddle;

        Ball ball;

        // hasta 200 ladrillos
        Brick[] bricks = new Brick[200];
        int numBricks = 0;


        // puntaje
        int score = 0;

        // vidas
        int lives = 3;


        public VistaBrickbreaker(Context context) {

            super(context);

            // inicializa ourHolder y paint.
            ourHolder = getHolder();
            paint = new Paint();

            // objeto display para acceder a informacion en pantalla.
            Display display = getWindowManager().getDefaultDisplay();
            // Cargamos la resolucion en un objeto Point.
            Point size = new Point();
            display.getSize(size);

            screenX = size.x;
            screenY = size.y;

            paddle = new Paddle(screenX, screenY);

            ball = new Ball(screenX, screenY);

            createBricksAndRestart();

        }

        public void createBricksAndRestart() {

            // coloca la bola en el punto de inicio.
            ball.reset(screenX, screenY);

            int brickWidth = screenX / 15;
            int brickHeight = screenY / 18;

            // construye ladrillos.
            numBricks = 0;
            for (int column = 0; column < 15; column++) {
                for (int row = 0; row < 4; row++) {
                    bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                    numBricks++;
                }
            }
            // resetea el juego cuando pierde todas las vidas.
            if (lives == 0) {
                score = 0;
                lives = 3;
            }
        }

        @Override
        public void run() {
            while (playing) {
                // captura el tiempo en milisegundos para almacenarlo.
                long startFrameTime = System.currentTimeMillis();
                // hace update a frame.
                if (!paused) {
                    update();
                }
                // Ddibuja un fotograma
                draw();
                // calcula los fps en este fotograma
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }

            }

        }

        //hace el update
        public void update() {

            // mueve paddle
            paddle.update(fps);

            ball.update(fps);

            // cuando la pelota pega contra los ladrillos
            for (int i = 0; i < numBricks; i++) {
                if (bricks[i].getVisibility()) {
                    if (RectF.intersects(bricks[i].getRect(), ball.getRect())) {
                        bricks[i].setInvisible();
                        ball.reverseYVelocity();
                        score = score + 10;
                    }
                }
            }
            // cuando la pelota pega contra paddle
            if (RectF.intersects(paddle.getRect(), ball.getRect())) {
                ball.setRandomXVelocity();
                ball.reverseYVelocity();
                ball.clearObstacleY(paddle.getRect().top - 2);
            }
            // hace rebotar la pelota cuando pega en el pozo de la pantalla
            if (ball.getRect().bottom > screenY) {
                ball.reverseYVelocity();
                ball.clearObstacleY(screenY - 2);

                // pierde una vida
                lives--;

                if (lives == 0) {
                    paused = true;
                    createBricksAndRestart();
                }
            }

            // hace rebotar la pelota cuando pega en la parte superior de la pantalla
            if (ball.getRect().top < 0)

            {
                ball.reverseYVelocity();
                ball.clearObstacleY(12);

            }

            // cuando la pelota pega contra la cara izquierda
            if (ball.getRect().left < 0)

            {
                ball.reverseXVelocity();
                ball.clearObstacleX(2);
            }

            // cuando la pelota pega contra la cara derecha
            if (ball.getRect().right > screenX - 10) {

                ball.reverseXVelocity();
                ball.clearObstacleX(screenX - 22);

            }

            // pausa si se gana
            if (score == numBricks * 10)

            {
                paused = true;
                createBricksAndRestart();
            }

        }

        // dibuja la pantalla mas reciente
        public void draw() {

            // para ver si la superficie de dibujo es valida
            if (ourHolder.getSurface().isValid()) {
                // bloquea el canvas como listo para dibujar
                canvas = ourHolder.lockCanvas();

                // dibuja el color de fondo
                canvas.drawColor(Color.argb(255, 150, 26, 54));

                // elige el color de pincel
                paint.setColor(Color.argb(255, 255, 255, 255));

                // dibuja paddle
                canvas.drawRect(paddle.getRect(), paint);

                // Dibuja la pelota
                canvas.drawRect(ball.getRect(), paint);

                // cambia el color del pincel
                paint.setColor(Color.argb(255, 0, 125, 255));

                // si los brick son visibles los dibuja
                for (int i = 0; i < numBricks; i++) {
                    if (bricks[i].getVisibility()) {
                        canvas.drawRect(bricks[i].getRect(), paint);
                    }
                }

                // elige el pincel para dibujar colores
                paint.setColor(Color.argb(255, 255, 255, 255));

                //dibuja el puntaje
                paint.setTextSize(40);
                canvas.drawText("Score: " + score + "   Lives: " + lives, 10, 50, paint);

                // si el jugado gana
                if (score == numBricks * 10) {
                    paint.setTextSize(90);
                    canvas.drawText("Ganaste!", 10, screenY / 2, paint);
                }

                // si el jugador pierde
                if (lives == 0) {
                    paint.setTextSize(90);
                    canvas.drawText("Perdiste!", 10, screenY / 2, paint);
                }

                //dibuja en la screen
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        // Si SimpleGameEngine Activity pausada o parada
        // termina la ejecucion del hilo.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "en el join del Thread");
            }
        }

        // si SimpleGameEngine Activity esta iniciada
        // inicia el hilo
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // SurfaceView class implementa onTouchListener
        // podemos sobreescribir el metodo para detectar pulsaciones en la pantalla
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                // ha tocado la pantalla
                case MotionEvent.ACTION_DOWN:
                    paused = false;
                    if (motionEvent.getX() > screenX / 2) {

                        paddle.setMovementState(paddle.RIGHT);
                    } else

                    {
                        paddle.setMovementState(paddle.LEFT);
                    }

                    break;

                // ha dejado de tocar la pantalla
                case MotionEvent.ACTION_UP:

                    paddle.setMovementState(paddle.STOPPED);
                    break;
            }

            return true;
        }

    }


    // se ejecuta cuando comienza el juego
    @Override
    protected void onResume() {
        super.onResume();
        vistaBrickbreaker.resume();
    }

    // este metodo se ejecuta cuand se pausa el juego o se sale
    @Override
    protected void onPause() {
        super.onPause();
       vistaBrickbreaker.pause();
    }


}
