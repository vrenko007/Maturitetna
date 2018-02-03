package si.vegova.mitja.maturitetna;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        View menuView = new MenuView(this);
        setContentView(menuView);
        menuView.setBackgroundColor(Color.BLACK);
    }
}
