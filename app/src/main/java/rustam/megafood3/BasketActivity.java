package rustam.megafood3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

import rustam.megafood3.model.BasketAdapter;
import rustam.megafood3.model.MenuData;

public class BasketActivity extends AppCompatActivity {

    private TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getString(R.string.basket));
        }

        total = (TextView) findViewById(R.id.basket_total);
    }

    public void onCheckout(View view) {
        //TODO
    }
}
