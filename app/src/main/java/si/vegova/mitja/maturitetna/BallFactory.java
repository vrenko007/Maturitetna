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


    Random rand;
    Paint paint;

    // Konstrukrot generatorja, kateremu podamo meje sredin krogcev
    BallFactory(float minX, float maxX, float minY, float maxY){
        rand = new Random();

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
    }


    // Dobi nov krogec
    public Ball getNextBall(){

        Ball ball = new Ball();

        ball.x = (rand.nextFloat()*(maxX-minX))+minX;
        ball.y = (rand.nextFloat()*(maxY-minY))+minY;
        ball.r = 90;
        ball.paint = paint;

        return ball;
    }
}
