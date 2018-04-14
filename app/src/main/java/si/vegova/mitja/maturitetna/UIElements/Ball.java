package si.vegova.mitja.maturitetna.UIElements;

import android.graphics.Canvas;
import android.graphics.Paint;

import si.vegova.mitja.maturitetna.GlobalSettings;

/*
 * Class Ball
 *
 * Krogci, ki predstavljajo pike, ki jih je treba kliknit v igri
 */
public class Ball {

    public float x;
    public float y;
    public float r;

    public float vX;
    public float vY;

    public int score = 1;

    public float minX;
    public float maxX;
    public float minY;
    public float maxY;

    public Paint paint;

    public void drawOn(Canvas canvas){

        if(x+vX > maxX || x+vX < minX){
            vX *= -1;
        }

        if(y+vY > maxY || y+vY < minY){
            vY *= -1;
        }

        x += vX;
        y += vY;
        paint.setColor(GlobalSettings.colors[GlobalSettings.ball_color]);
        canvas.drawCircle( x, y, r, paint);
    }

    public boolean contains(float x2, float y2){/*   x > _ball.x-_ball.r &&
                    x < _ball.x+_ball.r &&
                    y > _ball.y-_ball.r &&
                    y < _ball.y+_ball.r*/
        // ce je dotik v krogcu povecamo tocke
        return (x2-x)*(x2-x) + (y2-y)*(y2-y) <= r*r; //formula, ki računa krožnico kroga (ali pitagorov izrek)
    }
}
