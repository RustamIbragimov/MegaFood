package rustam.megafood3.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rustam.megafood3.MenuFragment;
import rustam.megafood3.R;

/**
 * Created by Dima on 28.02.2016.
 */
public class ExpMenuAdapter extends BaseExpandableListAdapter {

    public List<MenuData> mList;
    public Context mContext;
    CartListener lstner;

    public ExpMenuAdapter(List<MenuData> mList, Context mContext, CartListener lstner) {
        this.mList = mList;
        this.mContext = mContext;
        this.lstner =lstner;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int count = 0;
        try {
            count = mList.get(groupPosition).getList().size();
        } catch (Exception e) {
        }
        return count;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (mList.get(groupPosition).isSingle()) {
                convertView = inflater.inflate(R.layout.menu_item, null);
            } else {
                convertView = inflater.inflate(R.layout.menu_group_item, null);
            }
        }

        if (isExpanded) {

        } else {

        }

        if (mList.get(groupPosition).isSingle()) {
            ((TextView) convertView.findViewById(R.id.menu_item_title))
                    .setText(mList.get(groupPosition).getName());
            ((TextView) convertView.findViewById(R.id.menu_item_desc))
                    .setText(mList.get(groupPosition).getDesc());
            ((TextView) convertView.findViewById(R.id.price_text_view))
                    .setText(mList.get(groupPosition).getPrice() + "₸");
            ((ImageView) convertView.findViewById(R.id.menu_item_image))
                    .setImageBitmap(mList.get(groupPosition).getImage());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lstner.onCartAdd(Integer.parseInt(mList.get(groupPosition).getId()));
                }
            });
        } else {
            ((TextView) convertView.findViewById(R.id.menu_item_title))
                    .setText(mList.get(groupPosition).getName());
            ((TextView) convertView.findViewById(R.id.menu_item_desc))
                    .setText(mList.get(groupPosition).getDesc());
            ((ImageView) convertView.findViewById(R.id.menu_item_image))
                    .setImageBitmap(mList.get(groupPosition).getImage());
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item, null);
        }
        ((TextView) convertView.findViewById(R.id.menu_item_title))
                .setText(mList.get(groupPosition).getList().get(childPosition).getName());
        ((TextView) convertView.findViewById(R.id.menu_item_desc))
                .setText(mList.get(groupPosition).getList().get(childPosition).getDesc());
        ((TextView) convertView.findViewById(R.id.price_text_view))
                .setText(mList.get(groupPosition).getList().get(childPosition).getPrice() + "₸");
        convertView.findViewById(R.id.menu_item_image)
                .setVisibility(View.GONE);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstner.onCartAdd(Integer.parseInt(mList.get(groupPosition).getList().get(childPosition).getId()));
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
