package si.vegova.mitja.maturitetna;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class MenuView extends View implements View.OnTouchListener  {

    MainActivity _master;

    public MenuView(Context context) {
        super(context);


        this.setFocusableInTouchMode(true);
        this.setClickable(true);

        this.setOnTouchListener(this);
        _master = (MainActivity) context;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // Ob dotiko nastavimo zaslon na igro
        GameView gameView = new GameView(_master);
        gameView.setBackgroundColor(Color.WHITE);
        _master.setContentView(gameView);
        return true;
    }
}
