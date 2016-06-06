package com.sudasell.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sudasell.R;

import java.util.ArrayList;
import java.util.List;

public final class AdapterGridviewHome extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public AdapterGridviewHome(Context context) {

        mInflater = LayoutInflater.from(context);

        for (int i = 0; i < 100; i++) {
            mItems.add(new Item("Amazon", R.drawable.image_grid_one));
            mItems.add(new Item("FlipKart", R.drawable.image_grid_two));
            mItems.add(new Item("Just Deal", R.drawable.image_grid_three));
            mItems.add(new Item("OLX", R.drawable.image_grid_four));
            mItems.add(new Item("Paytm", R.drawable.image_grid_five));
            mItems.add(new Item("Horror", R.drawable.image_grid_six));

        }

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View AdapterView = view;
        ImageView picture;
        TextView name;

        if (AdapterView == null) {

            AdapterView= mInflater.inflate(R.layout.custom_grid_item, viewGroup, false);
            AdapterView.setTag(R.id.picture, AdapterView.findViewById(R.id.picture));
            AdapterView.setTag(R.id.text, AdapterView.findViewById(R.id.text));
        }

        picture = (ImageView) AdapterView.getTag(R.id.picture);
        name = (TextView) AdapterView.getTag(R.id.text);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return AdapterView;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}