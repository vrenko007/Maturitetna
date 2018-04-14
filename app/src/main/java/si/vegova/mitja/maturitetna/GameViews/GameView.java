package si.vegova.mitja.maturitetna.GameViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import si.vegova.mitja.maturitetna.UIElements.Ball;
import si.vegova.mitja.maturitetna.GlobalSettings;

/*
 * View Game
 * Osnovna igra, ki ima 60 sekund in steje koliko krogcev kliknes
 */
public class GameView extends BaseGameView {

    // Trenutni krogec
    private Ball _ball;

    // Tocke
    private int _score;


    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {

        super.onSizeChanged(w,h,oldW,oldH);

        setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);

        _ball = ballFactory.getNextBall();

    }

    public GameView(Context context) {
        super(context);

        // nastavimo tocke na 0
        _score = 0;
    }


    // Klic izrisa
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        // Nastavimo stil pisave
        Paint stringPaint = new Paint();
        stringPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        stringPaint.setColor(Color.BLACK);
        stringPaint.setTextAlign(Paint.Align.CENTER);

        // Preverimo konec igre
        if(_gameOver || _time < 1){
            _gameOver = true;
            tmr.cancel();

            stringPaint.setTextSize(130);
            canvas.drawText(String.format("Game Over", _score), xMax/2, yMax/2-100, stringPaint);
            canvas.drawText(String.format("Score: %d", _score), xMax/2, yMax/2, stringPaint);
            _backButton.drawOn(canvas);


        }
        else{
            // Izpisemo podatke o igri (tocke in cas, ki nam je preostal)

            _backButton1.drawOn(canvas);
            stringPaint.setTextSize(50);
            _ball.drawOn(canvas);
            canvas.drawText(String.format(Locale.ENGLISH, "Score: %d     Time: %d", _score, _time), xMax/2, 50, stringPaint);
            //canvas.drawText(String.format(Locale.ENGLISH, "State: %s",_state), 0, 80, stringPaint);

            invalidate(); // zaprosimo za nov izris
        }

    }

    // Event za dotik
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        float x = event.getX(), y = event.getY();

        // Upostevamo samo dotik, ne pa tudi drzanja gumba
        // preverjamo ce ze kalkuliramo dotik ali pa ce je konec igre
        if(!_gameOver && event.getAction() == MotionEvent.ACTION_DOWN){
            if(firstClick){
                firstClick = false;
                tmr.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        _time--;
                    }
                }, 0, 1000);
            }
            if(_ball.contains(x,y)){
                _score += _ball.score;
                _ball = ballFactory.getNextBall();
            }else {
                _score -= _ball.score;
            }
        }

        return super.onTouch(view, event);
    }
}
