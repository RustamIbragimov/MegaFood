package rustam.megafood3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rustam.megafood3.model.CartListener;
import rustam.megafood3.model.ExpMenuAdapter;
import rustam.megafood3.model.MenuData;
import rustam.megafood3.model.Request;


public class MenuFragment extends Fragment implements CartListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = MenuFragment.class.getSimpleName();
    private static final String RESTAURANT_NAME_PARAM = "restaurant_name";
    private String mRestaurantName;
    public static List<MenuData> mList;

    private ExpandableListView listView;
    private ProgressBar progressBar;

    private Toolbar mToolbar;

    static public Map<String, Integer> order;

    public MenuFragment() {
        // Required empty public constructor
        order = new HashMap<>();
    }


    public static MenuFragment newInstance(String restaurantName) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(RESTAURANT_NAME_PARAM, restaurantName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRestaurantName = getArguments().getString(RESTAURANT_NAME_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mToolbar = ((MainActivity) getActivity()).getToolbar();
        mToolbar.setTitle(mRestaurantName);

        listView = (ExpandableListView) view.findViewById(R.id.menu_list_view);
        progressBar = (ProgressBar) view.findViewById(R.id.loading_progress_bar);

        listView.setVisibility(View.INVISIBLE);

        MenuLongOperation operation = new MenuLongOperation(listView);
        operation.execute();


        return view;
    }

    @Override
    public void onCartAdd(String item_id) {
        if(order.get(item_id)!=null){
            order.put(item_id, order.get(item_id)+1);
        } else {
            order.put(item_id, 1);
        }
        Log.v(TAG, item_id);
    }


    class MenuLongOperation extends AsyncTask<Void, Void, Void> {

        public MenuLongOperation(ExpandableListView view) {
            this.lv = view;
        }

        ExpandableListView lv;
        ExpMenuAdapter adapter;

        @Override
        protected Void doInBackground(Void... params) {
            mList = Request.sendMenuRequest(mRestaurantName);
            adapter = new ExpMenuAdapter(mList, MenuFragment.this.getContext(), MenuFragment.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.v(TAG, mList.get(0).getName());
            lv.setAdapter(adapter);

            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
