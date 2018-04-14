package si.vegova.mitja.maturitetna.GameViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;

/*
 * View Moving Game
 * Igra z premikajocim krogcem
 */
public class MovingGameView extends GameView {

    public MovingGameView(Context context) {
        super(context);
    }

    // Ko se premaknemo na zaslon
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w,h,oldW,oldH);
        ballFactory.moving = true;
    }
}
