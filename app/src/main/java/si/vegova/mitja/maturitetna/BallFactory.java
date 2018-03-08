package si.vegova.mitja.maturitetna;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/*
 * Class BallFactory
 *
 * Generator Krogcev na random pozicijah
 */

public class BallFactory {

    float minX;
    float maxX;
    float minY;
    float maxY;
    int radij = 130;
    int a = 0;
    int _st_gm=0;


    Random rand;
    Paint paint;

    // Konstrukrot generatorja, kateremu podamo meje sredin krogcev
    BallFactory(float minX, float maxX, float minY, float maxY) {
        rand = new Random();

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        paint = new Paint();

    }


    // Dobi nov krogec
    public Ball getNextBall(){

        Ball ball = new Ball();

    if(_st_gm == 0){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        ball.x = (rand.nextFloat()*(maxX-minX))+minX;
        ball.y = (rand.nextFloat()*(maxY-minY))+minY;

        if(a == 0)  {
            ball.r = radij;
            radij = radij - 10;
            if (radij < 65) a = 1;
        }
        if (a == 1){

            ball.r = radij;
            radij = radij + 10;
            if (radij >= 150) a = 0;
            }

        ball.paint = paint;}





        return ball;
    }
}
