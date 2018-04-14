package si.vegova.mitja.maturitetna.UIElements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;


public class Button {

    public Button(int left, int top, int right, String text, int buttonColor, int textColor){
        this(left, top, right, 400, text, buttonColor, textColor);
    }

    public Button(int left, int top, int right, int height, String text, int buttonColor, int textColor){

        _rectangle = new Rect(left, top, right, top + height);
        _text = text;

        _paintRect = new Paint();
        _paintRect.setColor(buttonColor);
        _paintRect.setStyle(Paint.Style.FILL);
        _paintRect.setAlpha(8*255/10);


        _paintString = new Paint();
        _paintString.setTextSize(200);
        _paintString.setTextAlign(Paint.Align.CENTER);
        _paintString.setColor(textColor);
        _paintString.setStyle(Paint.Style.FILL_AND_STROKE);
        _paintString.setTypeface(Typeface.DEFAULT_BOLD);
        _paintString.setAlpha(8*255/10);
    }


    public void drawOn(Canvas canvas){

        canvas.drawRect(_rectangle, _paintRect);
        canvas.drawText(_text, _rectangle.centerX(), _rectangle.centerY() - ((_paintString.descent() + _paintString.ascent()) / 2), _paintString);

    }

    public boolean contains(int x, int y){
        return _rectangle.contains(x, y);
    }

    private Rect _rectangle;
    private String _text;
    private Paint _paintRect;
    private Paint _paintString;
}
