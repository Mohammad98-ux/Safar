package com.example.android.safar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.android.safar.R;
import com.example.android.safar.adapters.CountryAdapter;
import com.example.android.safar.adapters.CountrySearchAdapter;
import com.example.android.safar.model.Country;
import com.example.android.safar.model.SQLiteHelper;

import java.util.ArrayList;

import Utils.QueryUtils;
import Utils.Utils;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;
import static com.example.android.safar.ui.MainActivity.signedInUser;

public class CountryActivity extends AppCompatActivity {

    private ListView countriesLV;
    private CountrySearchAdapter countrySearchAdapter;

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        sqLiteHelper = new SQLiteHelper(this);

        RelativeLayout rootRL = findViewById(R.id.country_activity_root_rl);
        rootRL.setBackgroundColor(getResources().getColor(R.color.background_black));

        /* Make a list of supported countries. */
        final ArrayList<Country> countriesList = QueryUtils.getSupportedCountries();

        countrySearchAdapter = new CountrySearchAdapter(this, countriesList);

        CountryAdapter countryAdapter = new CountryAdapter(this, countriesList);

        countriesLV = (ListView) findViewById(R.id.country_list_view);
        countriesLV.setAdapter(countryAdapter);

        countriesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedCountry = (Country) countriesLV.getItemAtPosition(position);
                Utils.showCountryPopupMenu(CountryActivity.this, view, clickedCountry);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        clickedCountry = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_country_options_menu, menu);

        MenuItem searchItm = menu.findItem(R.id.search_itm);
        SearchView searchView = (SearchView) searchItm.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    countrySearchAdapter.filter("");
                    countriesLV.clearTextFilter();
                } else {
                    countrySearchAdapter.filter(newText);
                }

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_favourites_itm:
                startActivity(new Intent(CountryActivity.this, FavouritesActivity.class));
                break;

            case R.id.logout_itm:
                Utils.showLogoutDialog(this, signedInUser.getUserID());
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Utils.showLogoutDialog(this, signedInUser.getUserID());
    }
}














