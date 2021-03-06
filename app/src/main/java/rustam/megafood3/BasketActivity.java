package rustam.megafood3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rustam.megafood3.model.Basket;
import rustam.megafood3.model.BasketAdapter;
import rustam.megafood3.model.MenuData;
import rustam.megafood3.model.Pair;
import rustam.megafood3.model.Request;

public class BasketActivity extends AppCompatActivity {

    private static final String TAG = BasketActivity.class.getSimpleName();
    private TextView total;
    private Map<String, Integer> map;
    private List<MenuData> list;
    private RecyclerView recyclerView;
    private List<Pair<MenuData, Integer>> data;
    private BasketAdapter adapter;


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            String totalStr = total.getText().toString();
            int cur = Integer.valueOf(data.get(position).first.getPrice().substring(0, data.get(position).first.getPrice().indexOf(".")));
            int amount = map.get(data.get(position).first.getId());
            totalStr = String.valueOf(Integer.valueOf(totalStr) - cur * amount);
            total.setText(totalStr);

            data.remove(position);
            recyclerView.getAdapter().notifyItemRemoved(position);
        }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);


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
                    int amount = map.get(item.getId());
                    totalInt += Integer.valueOf(price) * amount;
                }
            } else {
                List<MenuData> lst = item.getList();
                for (MenuData item2 : lst) {
                    if (item2.getId() == null) continue;
                    if (map.containsKey(item2.getId())) {
                        data.add(new Pair<MenuData, Integer>(item2, map.get(item2.getId())));
                        String price = item2.getPrice().substring(0, item2.getPrice().indexOf("."));
                        int amount = map.get(item2.getId());
                        totalInt += Integer.valueOf(price) * amount;
                    }

                }
            }
        }

        adapter = new BasketAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemTouchHelper.attachToRecyclerView(recyclerView);

        total.setText(String.valueOf(totalInt));
    }

    public void onCheckout(View view) {

        AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                BasketActivity.this.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                BasketActivity.this.findViewById(R.id.content).setVisibility(View.GONE);
            }

            @Override
            protected String doInBackground(Void... params) {
                return Request.sendOrderRequest(map);
            }

            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(BasketActivity.this, CheckOutActivity.class);
                intent.putExtra("ord_id",aVoid);
                startActivity(intent);
                BasketActivity.this.findViewById(R.id.progressBar).setVisibility(View.GONE);
                BasketActivity.this.findViewById(R.id.content).setVisibility(View.VISIBLE);
            }
        };
        task.execute((Void)null);
    }
}
