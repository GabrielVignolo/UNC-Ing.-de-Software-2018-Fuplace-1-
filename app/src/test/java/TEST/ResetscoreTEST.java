package TEST;

import com.example.usuario.brickbreaker.Ball;
import com.example.usuario.brickbreaker.Brick;

import org.junit.Test;

public class ResetscoreTEST {

    int lives=0;
    int score=100;
    int screenX=1920;
    int screenY=1080;
    int numBricks=0;

    Ball ball= new Ball(1920,1080);
    Brick[] bricks = new Brick[200];

    public void createBricksAndRestart() {

        ball.reset(screenX, screenY);

        int brickWidth = screenX / 15;
        int brickHeight = screenY / 18;

        numBricks = 0;
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 7; row++) {
                bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                numBricks++;
            }
        }
        if (lives == 0) {
            score = 0;
            lives = 3;
        }
    }

    @Test
    public void test(){
        createBricksAndRestart();
        junit.framework.Assert.assertEquals(0,score);
    }
}
