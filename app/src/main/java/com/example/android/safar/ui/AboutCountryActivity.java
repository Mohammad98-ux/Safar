package com.example.android.safar.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;

import Utils.Utils;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;

public class AboutCountryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_country);

        this.setTitle("About " + clickedCountry.getSimpleName());

        TextView location = findViewById(R.id.country_location);
        location.setText(clickedCountry.getCountryMoreInfo().getLocation());

        TextView population = findViewById(R.id.country_population);
        population.setText(clickedCountry.getCountryMoreInfo().getPopulation() + " M");

        TextView area = findViewById(R.id.country_area);
        area.setText(clickedCountry.getCountryMoreInfo().getArea() + " Square Kilometer");

        TextView capital = findViewById(R.id.country_capital);
        capital.setText(clickedCountry.getCountryMoreInfo().getCapital());

        TextView neighbouringCountries = findViewById(R.id.neighbouring_countries);
        neighbouringCountries.setText(clickedCountry.getCountryMoreInfo().getNeighbouringCountries());

        TextView currency = findViewById(R.id.country_currency);
        currency.setText(clickedCountry.getCountryMoreInfo().getCurrency());

        TextView quickHistory = findViewById(R.id.country_quick_history);
        quickHistory.setText(clickedCountry.getCountryMoreInfo().getQuickHistoryStrResId());

        Button seeWikipediaBtn = findViewById(R.id.country_see_wiki_btn);
        seeWikipediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.searchWikipedia(AboutCountryActivity.this,
                        "en", clickedCountry.getSimpleName());
            }
        });

    }
}






