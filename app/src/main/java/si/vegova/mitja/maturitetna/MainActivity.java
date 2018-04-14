package si.vegova.mitja.maturitetna;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import si.vegova.mitja.maturitetna.MenuViews.MenuView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        // nastavimo zacetni zaslon na meni
        View menuView = new MenuView(this);
        setContentView(menuView);
    }
}
