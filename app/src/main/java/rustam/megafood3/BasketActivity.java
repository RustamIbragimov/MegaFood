package rustam.megafood3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rustam.megafood3.model.BasketAdapter;
import rustam.megafood3.model.MenuData;
import rustam.megafood3.model.Pair;

public class BasketActivity extends AppCompatActivity {

    private static final String TAG = BasketActivity.class.getSimpleName();
    private TextView total;
    private Map<String, Integer> map;
    private List<MenuData> list;
    private RecyclerView recyclerView;
    private List<Pair<MenuData, Integer>> data;
    private BasketAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getString(R.string.basket));
        }

        total = (TextView) findViewById(R.id.basket_total);
        map = MenuFragment.order;
        list = MenuFragment.mList;
        recyclerView = (RecyclerView) findViewById(R.id.basket_recycler_view);


        data = new ArrayList<>();

        int totalInt = 0;
        for (MenuData item : list) {
            if (item.isSingle()) {
                if (item.getId() == null) continue;
                if (map.containsKey(item.getId())) {
                    data.add(new Pair<MenuData, Integer>(item, map.get(item.getId())));
                    String price = item.getPrice().substring(0, item.getPrice().indexOf("."));
                    totalInt += Integer.valueOf(price);
                }
            } else {
                List<MenuData> lst = item.getList();
                for (MenuData item2 : lst) {
                    if (item2.getId() == null) continue;
                    if (map.containsKey(item2.getId())) {
                        data.add(new Pair<MenuData, Integer>(item2, map.get(item2.getId())));
                        String price = item2.getPrice().substring(0, item2.getPrice().indexOf("."));
                        totalInt += Integer.valueOf(price);
                    }

                }
            }
        }

        adapter = new BasketAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        total.setText(String.valueOf(totalInt));
    }

    public void onCheckout(View view) {
        //TODO
    }
}
