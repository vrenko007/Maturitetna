package si.vegova.mitja.maturitetna.GameViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import si.vegova.mitja.maturitetna.UIElements.Ball;
import si.vegova.mitja.maturitetna.GlobalSettings;

/*
 * View Time Game
 * Igra, ki dodaja cas kliknenim krogcem
 */
public class TimeGameView extends BaseGameView {

    // Trenutni krogec
    private Ball _ball;

    private float _timePast, _time;


    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {

        super.onSizeChanged(w,h,oldW,oldH);

        setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);

        _ball = ballFactory.getNextBall();

    }

    public TimeGameView(Context context) {
        super(context);


        // instanciramo Odstevalnik
        _time = 30f;
        _timePast = 0f;
    }


    // Klic izrisa
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        // Nastavimo stil pisave
        Paint stringPaint = new Paint();
        DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        stringPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        stringPaint.setColor(Color.BLACK);
        stringPaint.setTextAlign(Paint.Align.CENTER);

        // Preverimo konec igre
        if(_gameOver || _time <= 0){
            _gameOver = true;
            tmr.cancel();

            stringPaint.setTextSize(130);
            canvas.drawText(String.format("Game Over"), xMax/2, yMax/2-100, stringPaint);
            canvas.drawText(String.format("Time in game: %s", df.format(_timePast)), xMax/2, yMax/2, stringPaint);
            _backButton.drawOn(canvas);


        }
        else{
            // Izpisemo podatke o igri (tocke in cas, ki nam je preostal)

            _backButton1.drawOn(canvas);
            stringPaint.setTextSize(50);
            _ball.drawOn(canvas);

            canvas.drawText(String.format(Locale.ENGLISH, "Time: %s", df.format(_time)), xMax/2, 50, stringPaint);

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
            if(firstClick) {
                firstClick = false;
                tmr.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        _time -= 0.01;
                        _timePast += 0.01;
                    }
                }, 0, 10);
            }
            if(_ball.contains(x,y)){
                _time += 0.25f;
                _ball = ballFactory.getNextBall();
            }
        }

        return super.onTouch(view, event);
    }
}
