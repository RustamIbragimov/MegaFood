package rustam.megafood3.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rustam.megafood3.MainFragment;
import rustam.megafood3.R;

/**
 * Created by Rustam on 2/27/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {


    private final LayoutInflater inflater;

    public MainAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text;

        public MainViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.main_image);
            text = (TextView) itemView.findViewById(R.id.main_text);
        }
    }
}
