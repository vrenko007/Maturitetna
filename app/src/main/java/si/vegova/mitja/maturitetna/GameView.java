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
/*
 * View Game
 *
 * Osnovna igra, ki ima 60 sekund in steje koliko krogcev kliknes
 */
class GameView extends View implements View.OnTouchListener {

    // Odstevalnik
    private Timer tmr;

    // Trenutni krogec
    private Ball _ball;

    // Generator krogcev
    private BallFactory ballFactory;

    // Tocke
    private int _score;

    // Cas za prikaz
    private int _time;

    // Meje igre
    private int xMin = 0;
    private int xMax;
    private int yMin = 50;
    private int yMax;

    private boolean processingTouch = false;
    private boolean _gameOver;


    private MainActivity _master;

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // definiramo x in y
        xMax = w-1;
        yMax = h-1;


        // instanciramo generator krogcev z mejami in dobimo prvi krogec
        ballFactory = new BallFactory(xMin+90,xMax-90,yMin+90,yMax-90);
        _ball = ballFactory.getNextBall();
    }

    public GameView(Context context) {
        super(context);

        // Definiramo poslušanje dotikov
        this.setFocusableInTouchMode(true);
        this.setClickable(true);
        this.setOnTouchListener(this);

        _master = (MainActivity) context;

        // instanciramo Odstevalnik
        _time = 60;
        tmr = new Timer(true);
        tmr.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                _time --;
            }
        },0, 1000);

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

        // Preverimo konec igre
        if(_time < 1){
            _gameOver = true;
            tmr.cancel();

            stringPaint.setTextSize(84);
            canvas.drawText(String.format("Game Over", _score), xMax/4, yMax/2-40, stringPaint);
            canvas.drawText(String.format("Score: %d", _score), xMax/4, yMax/2+50, stringPaint);


        }
        else{
            // Izpisemo podatke o igri (tocke in cas, ki nam je preostal)
            stringPaint.setTextSize(42);
            canvas.drawCircle(_ball.x, _ball.y, _ball.r, _ball.paint);
            canvas.drawText(String.format(Locale.ENGLISH, "Score: %d     Time: %d", _score, _time), 0, 50, stringPaint);

            invalidate(); // zaprosimo za nov izris
        }

    }

    // Event za dotik
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        // preverjamo ce ze kalkuliramo dotik ali pa ce je konec igre
        if(!processingTouch && !_gameOver){
            processingTouch = true;
            float x = event.getX();
            float y = event.getY();

            // ce je dotik v krogcu povecamo tocke
            if(x > _ball.x-_ball.r &&
                    x < _ball.x+_ball.r &&
                    y > _ball.y-_ball.r &&
                    y < _ball.y+_ball.r){

                _score += _ball.score;
                // ker nimamo kompleksnih izracunov se lahko zgodi da se 1 dotik izvede 2x zato krogcu invalidiramo tocke
                _ball.score = 0;

                //dobimo naslednji krogec
                _ball = ballFactory.getNextBall();
            }
            processingTouch = false;
        }

        // Preverimo ce je dotik ob konec igre da gremo v Meni
        if(_gameOver){
            MenuView menu = new MenuView(_master);
            menu.setBackgroundColor(Color.BLACK);
            _master.setContentView(menu);
        }
        return true;
    }
}
