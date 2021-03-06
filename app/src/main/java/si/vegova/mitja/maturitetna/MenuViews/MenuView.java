package si.vegova.mitja.maturitetna.MenuViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import si.vegova.mitja.maturitetna.GlobalSettings;
import si.vegova.mitja.maturitetna.MainActivity;
import si.vegova.mitja.maturitetna.R;
import si.vegova.mitja.maturitetna.UIElements.Ball;
import si.vegova.mitja.maturitetna.UIElements.BallFactory;
import si.vegova.mitja.maturitetna.UIElements.Button;

public class MenuView extends View implements View.OnTouchListener  {

    MainActivity _master;

    Button _playButton;
    Button _settingsButton;

    boolean _clicked = false;

    ArrayList<Ball> balls;

    public MenuView(Context context) {
        super(context);

        this.setFocusableInTouchMode(true);
        this.setClickable(true);

        this.setOnTouchListener(this);

        _master = (MainActivity) context;
    }

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        _playButton = new Button(50, h/2-200, w-50, "Play", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _settingsButton = new Button(50, h/2+250, w-50, "Settings",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));

        setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);

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

        _playButton.drawOn(canvas);
        _settingsButton.drawOn(canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // Zaznamo samo spust gumba
        if(motionEvent.getAction() != MotionEvent.ACTION_UP){
            return true;
        }

        if(_playButton.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){

          GameMode gamemode = new GameMode(_master);
            setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);
          _master.setContentView(gamemode);
        }
        else if (_settingsButton.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            si.vegova.mitja.maturitetna.MenuViews.Settings settings = new si.vegova.mitja.maturitetna.MenuViews.Settings(_master);
            setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);
            _master.setContentView(settings);

        }
        return true;
    }
}
