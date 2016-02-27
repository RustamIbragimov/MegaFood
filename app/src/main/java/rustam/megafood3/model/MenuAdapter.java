package rustam.megafood3.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rustam.megafood3.MenuFragment;
import rustam.megafood3.R;

/**
 * Created by Rustam on 2/27/2016.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    private final LayoutInflater inflater;
    private List<MenuData> list;
    private Context context;

    public MenuAdapter(Context context, List<MenuData> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_item, parent, false);



        MenuViewHolder viewHolder = new MenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuData item = list.get(position);
        holder.title.setText(item.getName());
        holder.desc.setText(item.getDesc());
        holder.price.setText(item.getPrice() == null ? "" : item.getPrice());

        Picasso.with(context).load(item.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MenuViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView desc;
        private TextView price;

        public MenuViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.menu_item_image);
            title = (TextView) itemView.findViewById(R.id.menu_item_title);
            desc = (TextView) itemView.findViewById(R.id.menu_item_desc);
            price = (TextView) itemView.findViewById(R.id.menu_item_price);
        }
    }
}
