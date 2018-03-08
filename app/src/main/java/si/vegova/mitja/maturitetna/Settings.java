package si.vegova.mitja.maturitetna;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Settings extends View implements View.OnTouchListener  {

    MainActivity _master;

    Button _background_color;
    Button _circle_color;
    Button _back_button;

    boolean _clicked = false;
    int color_circle = 0;
    int color_beckground = 0;


    ArrayList<Ball> balls;

    public Settings (Context context) {
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
        _background_color = new Button(50, h/2-800, w-600 , "SET", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _circle_color = new Button(50, h/2-300, w-600, "SET",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _back_button = new Button(50, h/2+300, w-400, "Back",ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));



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

        _background_color.drawOn(canvas);
        _circle_color.drawOn(canvas);
        _back_button.drawOn(canvas);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // Zaznamo samo spust gumba
        if(motionEvent.getAction() != MotionEvent.ACTION_UP){
            return true;
        }

        if(_background_color.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){


        }
        else if (_circle_color.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            Ball ball = new Ball();
            Paint paint = new Paint();
            if (color_circle >= 5) color_circle = 0;
            else if (color_circle == 0)  paint.setColor(Color.BLUE);
            else if (color_circle == 1)  paint.setColor(Color.RED);
            else if (color_circle == 2)  paint.setColor(Color.GREEN);
            else if (color_circle == 3)  paint.setColor(Color.YELLOW);
            else if (color_circle == 4)  paint.setColor(Color.BLACK);

        }
        else if (_back_button.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()))){
            // Ob dotiko nastavimo zaslon na igro
            MenuView menuView = new MenuView(_master);
            menuView.setBackgroundColor(Color.WHITE);
            _master.setContentView(menuView);

        }
        return true;
    }
}