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
    int r = 130;

    int barva;


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
        barva = rand.nextInt((4-0)+1)+0;
        if (barva == 0) paint.setColor(Color.GREEN); //Barva kroga
        else if (barva == 1) paint.setColor(Color.BLUE); //Barva kroga
        else if (barva == 2) paint.setColor(Color.BLACK); //Barva kroga
        else if (barva == 3) paint.setColor(Color.YELLOW); //Barva kroga
        else if(barva == 4) paint.setColor(Color.RED); //Barva kroga
        paint.setStyle(Paint.Style.FILL);
    }


    // Dobi nov krogec
        public Ball getNextBall(){

        Ball ball = new Ball();

        ball.x = (rand.nextFloat()*(maxX-minX))+minX;
        ball.y = (rand.nextFloat()*(maxY-minY))+minY;
        if (r > 20) ball.r = r - 1;


        barva = rand.nextInt((4-0)+1)+0;
        if (barva == 0) paint.setColor(Color.GREEN); //Barva kroga
        else if (barva == 1) paint.setColor(Color.BLUE); //Barva kroga
        else if (barva == 2) paint.setColor(Color.BLACK); //Barva kroga
        else if (barva == 3) paint.setColor(Color.YELLOW); //Barva kroga
        else if(barva == 4) paint.setColor(Color.RED); //Barva kroga
        ball.paint = paint;


        return ball;
    }
}
