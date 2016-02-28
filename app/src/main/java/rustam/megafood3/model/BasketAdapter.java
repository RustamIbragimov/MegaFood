package rustam.megafood3.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rustam.megafood3.R;

/**
 * Created by Rustam on 2/27/2016.
 */
public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private final LayoutInflater inflater;
    private List<Pair<MenuData, Integer>> list;



    public BasketAdapter(Context context, List<Pair<MenuData, Integer>> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.basket_item, parent, false);
        BasketViewHolder viewHolder = new BasketViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BasketViewHolder holder, int position) {
        Pair<MenuData, Integer> pair = list.get(position);
        MenuData item = pair.first;
        int amount = pair.second;

        holder.title.setText(item.getName());
        holder.desc.setText(item.getDesc());
        holder.price.setText(item.getPrice());
        holder.amount.setText(Integer.toString(amount));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class BasketViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private TextView price;
        private TextView amount;

        public BasketViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.basket_item_title);
            desc = (TextView) itemView.findViewById(R.id.basket_item_desc);
            price = (TextView) itemView.findViewById(R.id.basket_item_price);
            amount = (TextView) itemView.findViewById(R.id.basket_amount_text);
        }

    }
}
