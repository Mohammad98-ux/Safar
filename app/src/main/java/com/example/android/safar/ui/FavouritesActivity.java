package com.example.android.safar.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.android.safar.R;
import com.example.android.safar.adapters.FavouritesFragmentAdapter;
import com.example.android.safar.model.SQLiteHelper;
import com.google.android.material.tabs.TabLayout;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;
import static com.example.android.safar.ui.FavCountriesFragment.prevClickedCountry;

public class FavouritesActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        sqLiteHelper = new SQLiteHelper(this);

        this.setTitle("Favourites");

        FavouritesFragmentAdapter favouritesFragmentAdapter =
                new FavouritesFragmentAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.favourites_view_pager);
        viewPager.setAdapter(favouritesFragmentAdapter);

        TabLayout tabLayout = findViewById(R.id.favourites_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_activity_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_fav_countries_itm:
                showConfirmDeleteAllFavItems("countries");
                break;

            case R.id.clear_fav_attractions_itm:
                showConfirmDeleteAllFavItems("attractions");
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Retrieve the originally clickedCountry if the user has came from CountryDetailActivity.
        clickedCountry = prevClickedCountry;
    }

    private void showConfirmDeleteAllFavItems(final String favItems) {
        String dialogMessage = "Are you sure you want to delete all favourite ";
        if (favItems.equals("countries")) {
            dialogMessage = dialogMessage.concat("Countries?");
        } else {
            dialogMessage = dialogMessage.concat("Attractions?");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogMessage)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (favItems.equals("countries")) {
                            sqLiteHelper.deleteAllFavCountries();
                            FavCountriesFragment.countryAdapter.clear();
                        } else {
                            sqLiteHelper.deleteAllFavAttractions();
                            FavAttractionsFragment.attractionAdapter.clear();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}


















