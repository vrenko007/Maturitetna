package si.vegova.mitja.maturitetna;

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
    private float x1;
    private float y1;
    private float x = 0;
    private float y = 0;


    // Cas za prikaz
    private int _time;

    // Meje igre
    private int xMin = 0;
    private int xMax;
    private int yMin = 50;
    private int yMax;

    private boolean _gameOver = false;
    private Button _backButton;


    private MainActivity _master;

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // definiramo x in y
        xMax = w-1;
        yMax = h-1;


        // instanciramo generator krogcev z mejami in dobimo prvi krogec
        ballFactory = new BallFactory(xMin+ 130,xMax- 130,yMin+ 130,yMax- 130);
        _ball = ballFactory.getNextBall();

        _backButton = new Button(0+50, h-500, w-50, "Back", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
    }

    public GameView(Context context) {
        super(context);

        // Definiramo poslušanje dotikov
        this.setFocusableInTouchMode(true);
        this.setClickable(true);
        this.setOnTouchListener(this);

        _master = (MainActivity) context;

        // instanciramo Odstevalnik
        _time = 30;
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
        stringPaint.setTextAlign(Paint.Align.CENTER);

        // Preverimo konec igre
        if(_time < 1){
            _gameOver = true;
            tmr.cancel();

            stringPaint.setTextSize(130);
            canvas.drawText(String.format("Game Over", _score), xMax/2, yMax/2-100, stringPaint);
            canvas.drawText(String.format("Score: %d", _score), xMax/2, yMax/2, stringPaint);
            _backButton.drawOn(canvas);


        }
        else{
            // Izpisemo podatke o igri (tocke in cas, ki nam je preostal)


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
            if(_ball.contains(x,y)){
                _score += _ball.score;
                _ball = ballFactory.getNextBall();
            }else {
                _score -= _ball.score;
            }
        }

        // Ob koncu upoštevamo samo sprostitev klika
        // Preverimo ce je dotik ob konec igre da gremo v Meni
        if(_gameOver && event.getAction() == MotionEvent.ACTION_UP && _backButton.contains(Math.round(x), Math.round(y))){
            MenuView menu = new MenuView(_master);
            _master.setContentView(menu);
        }
        return true;
    }
}
