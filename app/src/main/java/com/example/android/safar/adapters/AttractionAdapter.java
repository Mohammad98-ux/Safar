package com.example.android.safar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.safar.model.Attraction;
import com.example.android.safar.R;

import java.util.ArrayList;

public class AttractionAdapter extends ArrayAdapter<Attraction> {

    public AttractionAdapter(Context context, ArrayList<Attraction> attractions) {
        super(context, 0, attractions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View attractionLayout = convertView;
        if (attractionLayout == null) {
            attractionLayout = LayoutInflater.from(getContext())
                    .inflate(R.layout.attraction_item_layout, parent, false);
        }

        Attraction currentAttraction = getItem(position);

        /* Set the attraction name and image. */
        TextView attrName = attractionLayout.findViewById(R.id.attraction_name);
        attrName.setText(currentAttraction.getName());

        ImageView attrImage = attractionLayout.findViewById(R.id.attraction_image);
        attrImage.setImageResource(currentAttraction.getImageResId());

        return attractionLayout;
    }
}












