package com.example.android.safar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.safar.R;
import com.example.android.safar.model.Country;
import com.example.android.safar.ui.CountryDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountrySearchAdapter extends BaseAdapter {

    private final Context context;
    private final List<Country> countriesList;
    private final LayoutInflater layoutInflater;
    private final ArrayList<Country> arrayList;

    public CountrySearchAdapter(Context context, List<Country> countriesList) {
        this.context = context;
        this.countriesList = countriesList;
        layoutInflater = LayoutInflater.from(context);

        arrayList = new ArrayList<>();
        arrayList.addAll(countriesList);
    }

    public static class ViewHolder {
        ImageView countryImage;
        TextView countryName;
        ImageView countryFlag;
    }

    @Override
    public int getCount() {
        return countriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return countriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.country_item_layout, null);

            viewHolder.countryImage = convertView.findViewById(R.id.country_image);
            viewHolder.countryName = convertView.findViewById(R.id.country_name);
            viewHolder.countryFlag = convertView.findViewById(R.id.country_flag);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Country currentCountry = countriesList.get(position);
        viewHolder.countryImage.setImageResource(currentCountry.getImageResId());
        viewHolder.countryName.setText(currentCountry.getName());
        viewHolder.countryFlag.setImageResource(currentCountry.getFlagImageResId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryDetailActivity.clickedCountry = currentCountry;
                context.startActivity(
                        new Intent(context, CountryDetailActivity.class));
            }
        });

        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        countriesList.clear();

        if (charText.isEmpty()) {
            countriesList.addAll(arrayList);
        } else {
            for (Country country : arrayList) {
                if (country.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    countriesList.add(country);
                }
            }
        }

        notifyDataSetChanged();
    }

}
















