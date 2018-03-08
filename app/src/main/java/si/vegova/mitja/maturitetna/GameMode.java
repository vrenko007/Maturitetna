package si.vegova.mitja.maturitetna;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameMode extends View implements View.OnTouchListener  {

    MainActivity _master;

    Button _GameMode;
    Button _GameMode1;
    Button _GameMode2;
    Button _GameMode3;
    Button _back_mode;

    boolean _clicked = false;


    ArrayList<Ball> balls;

    public GameMode (Context context) {
        super(context);

        setBackgroundColor(Color.WHITE); //Barva začetnega menija

        this.setFocusableInTouchMode(true);
        this.setClickable(true);

        this.setOnTouchListener(this);

        _master = (MainActivity) context;
    }

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        _GameMode = new Button(50, h/2-800, w-600 , "1", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _GameMode1 = new Button(600, h/2-800, w-50, "2",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _GameMode2 = new Button(50, h/2-300, w-600, "3",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _GameMode3 = new Button(600, h/2-300, w-50, "4",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _back_mode = new Button(50, h/2+300, w-400, "Back",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));



        BallFactory factory = new BallFactory(0, w, 0, h);
        balls = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            balls.add(factory.getNextBall());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Ball ball: balls) {
            ball.drawOn(canvas);
        }

        _GameMode.drawOn(canvas);
        _GameMode1.drawOn(canvas);
        _GameMode2.drawOn(canvas);
        _GameMode3.drawOn(canvas);
        _back_mode.drawOn(canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // Zaznamo samo spust gumba
        if(motionEvent.getAction() != MotionEvent.ACTION_UP){
            return true;
        }

        if(_GameMode.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){

            GameView gameView = new GameView(_master);

            gameView.setBackgroundColor(Color.WHITE);
            _master.setContentView(gameView);

        }
        else if (_GameMode1.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            GameView1 gameView1 = new GameView1(_master);

            gameView1.setBackgroundColor(Color.WHITE);
            _master.setContentView(gameView1);
        }
        else if (_GameMode2.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            GameView2 gameView2 = new GameView2(_master);

            gameView2.setBackgroundColor(Color.WHITE);
            _master.setContentView(gameView2);
        }
        else if (_GameMode3.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            GameView3 gameView3 = new GameView3(_master);

            gameView3.setBackgroundColor(Color.WHITE);
            _master.setContentView(gameView3);
        }
        else if (_back_mode.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            // Ob dotiko nastavimo zaslon na igro
            MenuView menuView = new MenuView(_master);
            menuView.setBackgroundColor(Color.WHITE);
            _master.setContentView(menuView);

        }
        return true;
    }
}