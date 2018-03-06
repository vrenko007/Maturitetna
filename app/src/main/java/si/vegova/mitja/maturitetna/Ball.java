package si.vegova.mitja.maturitetna;

import android.graphics.Canvas;
import android.graphics.Paint;

/*
 * Class Ball
 *
 * Krogci, ki predstavljajo pike, ki jih je treba kliknit v igri
 */
class Ball {

    public float x;
    public float y;
    public float r;

    public int score = 1;

    public Paint paint;

    public void drawOn(Canvas canvas){
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
