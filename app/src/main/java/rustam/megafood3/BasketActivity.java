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
import rustam.megafood3.model.MenuAdapter;
import rustam.megafood3.model.MenuData;

public class BasketActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BasketAdapter mBasketAdapter;
    private TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            ab.setTitle(getString(R.string.basket));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.menu_recycler_view);
        mBasketAdapter = new BasketAdapter(this, new LinkedList<MenuData>()); //TODO
        mRecyclerView.setAdapter(mBasketAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        total = (TextView) findViewById(R.id.basket_total);
    }

    public void onCheckout(View view) {
        //TODO
    }
}
