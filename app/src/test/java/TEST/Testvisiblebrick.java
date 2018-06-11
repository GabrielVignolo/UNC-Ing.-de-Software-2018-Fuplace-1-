package TEST;

import com.example.usuario.brickbreaker.Brick;

import org.junit.Test;

public class Testvisiblebrick {
    Brick brick=new Brick(3,14,1920/15, 1080/18);

    @Test
    public void test(){

        brick.setInvisible();
        junit.framework.Assert.assertEquals(false,brick.getVisibility());
    }
}
