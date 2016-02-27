package rustam.megafood3.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
    private List<MenuData> list;

    public BasketAdapter(Context context, List<MenuData> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_item, parent, false);
        BasketViewHolder viewHolder = new BasketViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BasketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class BasketViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView desc;
        private TextView price;

        public BasketViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.basket_item_image);
            title = (TextView) itemView.findViewById(R.id.basket_item_title);
            desc = (TextView) itemView.findViewById(R.id.basket_item_desc);
            price = (TextView) itemView.findViewById(R.id.basket_item_price);
        }

    }
}
