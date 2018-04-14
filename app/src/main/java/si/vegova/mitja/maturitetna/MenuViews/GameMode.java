package si.vegova.mitja.maturitetna.MenuViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


import si.vegova.mitja.maturitetna.GlobalSettings;
import si.vegova.mitja.maturitetna.MainActivity;
import si.vegova.mitja.maturitetna.R;
import si.vegova.mitja.maturitetna.UIElements.*;

import si.vegova.mitja.maturitetna.GameViews.*;

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


        setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);; //Barva začetnega menija

        this.setFocusableInTouchMode(true);
        this.setClickable(true);

        this.setOnTouchListener(this);

        _master = (MainActivity) context;
    }

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        _GameMode = new Button(50, h/2-800, w/2-50 , "1", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _GameMode1 = new Button(w/2, h/2-800, w-50, "2",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _GameMode2 = new Button(50, h/2-300, w/2-50, "3",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _GameMode3 = new Button(w/2+25, h/2-300, w-50, "4",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
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

        BaseGameView gameView;

        if(_GameMode.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){

            gameView = new GameView(_master);

            gameView.setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);
            _master.setContentView(gameView);

        }
        else if (_GameMode1.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            gameView = new TimeGameView(_master);

            gameView.setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);;
            _master.setContentView(gameView);
        }
        else if (_GameMode2.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            gameView = new MultipleGameView(_master);

            gameView.setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);;
            _master.setContentView(gameView);
        }
        else if (_GameMode3.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            gameView = new MovingGameView(_master);

            gameView.setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);;
            _master.setContentView(gameView);
        }
        else if (_back_mode.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            // Ob dotiko nastavimo zaslon na igro
            MenuView menuView = new MenuView(_master);
            menuView.setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);;
            _master.setContentView(menuView);

        }
        return true;
    }
}