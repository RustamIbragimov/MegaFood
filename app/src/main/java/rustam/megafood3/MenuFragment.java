package rustam.megafood3;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import rustam.megafood3.model.MenuAdapter;
import rustam.megafood3.model.MenuData;
import rustam.megafood3.model.RecyclerItemClickListener;
import rustam.megafood3.model.Request;


public class MenuFragment extends Fragment implements GroupDialogFragment.GroupDialogListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RESTAURANT_NAME_PARAM = "restaurant_name";
    private String mRestaurantName;
    private List<MenuData> mList;

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;

    public MenuFragment() {
        // Required empty public constructor
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

        mToolbar = ((MainActivity)getActivity()).getToolbar();
        mToolbar.setTitle(mRestaurantName);

        MenuLongOperation operation = new MenuLongOperation();
        operation.execute();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
        mMenuAdapter = new MenuAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(mMenuAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                }));

        return view;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }


    class MenuLongOperation extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            mList = Request.sendMenuRequest(mRestaurantName);
            return null;
        }
    }

}
