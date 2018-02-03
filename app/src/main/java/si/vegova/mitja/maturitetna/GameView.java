package si.vegova.mitja.maturitetna;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

class GameView extends View implements View.OnTouchListener {

    private Timer tmr;

    private Ball _ball;

    private BallFactory ballFactory;

    private int _score;

    private int _time;

    private int xMin = 0;          // This view's bounds
    private int xMax;
    private int yMin = 50;
    private int yMax;

    private boolean processingTouch = false;
    private boolean _gameOver;


    private MainActivity _master;

    // Called back when the view is first created or its size changes.
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // Set the movement bounds for the ball
        xMax = w-1;
        yMax = h-1;


        ballFactory = new BallFactory(xMin+90,xMax-90,yMin+90,yMax-90);
    }

    public GameView(Context context) {
        super(context);

        this.setFocusableInTouchMode(true);
        this.setClickable(true);

        this.setOnTouchListener(this);

        _master = (MainActivity) context;

        ballFactory = new BallFactory(90,500,140,500);


        _ball = ballFactory.getNextBall();

        _time = 60;
        tmr = new Timer(true);
        tmr.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                _time --;
            }
        },0, 1000);

        _score = 0;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint stringPaint = new Paint();
        stringPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        stringPaint.setColor(Color.BLACK);

        if(_time < 1){
            _gameOver = true;
            tmr.cancel();

            stringPaint.setTextSize(84);
            canvas.drawText(String.format("Game Over", _score), xMax/4, yMax/2-40, stringPaint);
            canvas.drawText(String.format("Score: %d", _score), xMax/4, yMax/2+50, stringPaint);


        }
        else{
            stringPaint.setTextSize(42);
            canvas.drawCircle(_ball.x, _ball.y, _ball.r, _ball.paint);
            canvas.drawText(String.format(Locale.ENGLISH, "Score: %d     Time: %d", _score, _time), 0, 50, stringPaint);

            invalidate();
        }




        super.onDraw(canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if(!processingTouch && !_gameOver){
            processingTouch = true;
            float x = event.getX();
            float y = event.getY();
            if(x > _ball.x-_ball.r &&
                    x < _ball.x+_ball.r &&
                    y > _ball.y-_ball.r &&
                    y < _ball.y+_ball.r){

                _score += _ball.score;
                _ball.score = 0;

                _ball = ballFactory.getNextBall();
            }
            processingTouch = false;
        }

        if(_gameOver){
            MenuView menu = new MenuView(_master);
            menu.setBackgroundColor(Color.BLACK);
            _master.setContentView(menu);
        }
        return true;
    }
}
