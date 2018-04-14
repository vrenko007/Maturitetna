package si.vegova.mitja.maturitetna.GameViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import si.vegova.mitja.maturitetna.MenuViews.MenuView;
import si.vegova.mitja.maturitetna.UIElements.*;
import si.vegova.mitja.maturitetna.GlobalSettings;
import si.vegova.mitja.maturitetna.MainActivity;
import si.vegova.mitja.maturitetna.R;

/*
 * View Game
 *
 * Osnovna igra, ki ima 60 sekund in steje koliko krogcev kliknes
 */
public abstract class BaseGameView extends View implements View.OnTouchListener {

    // Odstevalnik
    protected Timer tmr;
    protected boolean firstClick = true;

    // Generator krogcev
    protected BallFactory ballFactory;

    // Cas za prikaz
    protected int _time;

    // Meje igre
    protected int xMin = 0;
    protected int xMax;
    protected int yMin = 50;
    protected int yMax;

    protected boolean _gameOver = false;
    protected Button _backButton;
    protected Button _backButton1;

    protected MainActivity _master;

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        // definiramo x in y
        xMax = w-1;
        yMax = h-1;

        setBackgroundColor(GlobalSettings.colors[GlobalSettings.background_color]);

        // instanciramo generator krogcev z mejami in dobimo prvi krogec
        ballFactory = new BallFactory(xMin+ 130,xMax- 130,yMin+ 130,yMax- 130);

        _backButton = new Button(50, h-500, w-50, "Back", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
        _backButton1 = new Button(w-200, 0, w, 200, "x", ContextCompat.getColor(_master, R.color.buttonBackground), ContextCompat.getColor(_master, R.color.buttonForeground));
    }

    public BaseGameView(Context context) {
        super(context);

        // Definiramo poslušanje dotikov
        this.setFocusableInTouchMode(true);
        this.setClickable(true);
        this.setOnTouchListener(this);

        _master = (MainActivity) context;

        // instanciramo Odstevalnik
        _time = 30;
        tmr = new Timer(true);
    }


    // Event za dotik
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        float x = event.getX(), y = event.getY();

        if(_backButton1.contains(Math.round(x), Math.round(y))){
            _time = 0;
            _gameOver = true;
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
