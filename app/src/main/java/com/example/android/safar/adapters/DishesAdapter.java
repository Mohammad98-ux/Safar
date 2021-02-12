package com.example.android.safar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.safar.R;
import com.example.android.safar.model.Dish;

import java.util.ArrayList;

public class DishesAdapter extends ArrayAdapter<Dish> {

    public DishesAdapter(Context context, ArrayList<Dish> dishes) {
        super(context, 0, dishes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View dishItemLayout = convertView;
        if (dishItemLayout == null) {
            dishItemLayout = LayoutInflater.from(getContext())
                    .inflate(R.layout.dish_item_layout, parent, false);
        }

        final Dish currentDish = getItem(position);

        ImageView dishImage = dishItemLayout.findViewById(R.id.dish_image);
        dishImage.setImageResource(currentDish.getImageResId());

        TextView dishName = dishItemLayout.findViewById(R.id.dish_name_tv);
        dishName.setText(currentDish.getName());

        return dishItemLayout;
    }


}















