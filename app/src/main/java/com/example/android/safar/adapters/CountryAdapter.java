package com.example.android.safar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.safar.R;
import com.example.android.safar.model.Country;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {

    /**
     * Make an adapter of countries by passing the context and the data source of countries.
     */
    public CountryAdapter(Context context, ArrayList<Country> countries) {
        /* The LayoutRes is passed 0 because we want to inflate our own created layout. */
        super(context, 0, countries);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        /*
         * Reuse the View object if possible.
         * Otherwise (i.e. convertView == null), create a new one.
         * */
        View countryItemLayout = convertView;
        if (countryItemLayout == null) {
            countryItemLayout = LayoutInflater.from(getContext())
                    .inflate(R.layout.country_item_layout, parent, false);
        }

        /*
         * Get the current Country object.
         * */
        final Country currentCountry = getItem(position);

        /*
         * Set the country name, flag and image.
         * */
        TextView countryName = countryItemLayout.findViewById(R.id.country_name);
        countryName.setText(currentCountry.getName());

        ImageView countryFlag = countryItemLayout.findViewById(R.id.country_flag);
        countryFlag.setImageResource(currentCountry.getFlagImageResId());

        ImageView countryPicture = countryItemLayout.findViewById(R.id.country_image);
        countryPicture.setImageResource(currentCountry.getImageResId());

        return countryItemLayout;
    }
}







