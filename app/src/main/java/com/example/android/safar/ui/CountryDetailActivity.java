package com.example.android.safar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.android.safar.R;
import com.example.android.safar.adapters.CountryDetailFragmentAdapter;
import com.example.android.safar.model.Country;
import com.example.android.safar.model.SQLiteHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import Utils.Utils;

import static com.example.android.safar.ui.MainActivity.signedInUser;

public class CountryDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Country clickedCountry;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        sqLiteHelper = new SQLiteHelper(this);

        toolbar = findViewById(R.id.country_detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.activityTitleColor));

        drawerLayout = findViewById(R.id.country_detail_drawer_layout);
        navigationView = findViewById(R.id.country_detail_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                CountryDetailActivity.this, drawerLayout, toolbar,
                R.string.nav_drawer_open,
                R.string.nav_drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        CountryDetailFragmentAdapter detailFragmentAdapter =
                new CountryDetailFragmentAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.country_detail_viewpager);
        viewPager.setAdapter(detailFragmentAdapter);

        TabLayout tabLayout = findViewById(R.id.country_details_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUIText();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_country_detail_options_menu, menu);

        MenuItem visitCountryItm = menu.findItem(R.id.visit_country_itm);
        visitCountryItm.setTitle("Visit " + clickedCountry.getSimpleName());

        MenuItem aboutItm = menu.findItem(R.id.about_country_itm);
        aboutItm.setTitle("About " + clickedCountry.getSimpleName());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem aboutItm = menu.getItem(0);
        aboutItm.setTitle("About " + clickedCountry.getSimpleName());

        MenuItem visitItm = menu.getItem(1);
        visitItm.setTitle("Visit " + clickedCountry.getSimpleName());

        MenuItem favItm = menu.getItem(2);
        if (sqLiteHelper.isCountryFav(clickedCountry.getSimpleName())) {
            favItm.setTitle("Remove from Favourites");
        } else {
            favItm.setTitle("Add to Favourites");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_country_itm:
                startActivity(new Intent(
                        CountryDetailActivity.this,
                        AboutCountryActivity.class)
                );
                break;

            case R.id.visit_country_itm:
                Utils.goToUrl(CountryDetailActivity.this,
                        clickedCountry.getMinistryOfTourismUrl());
                break;

            case R.id.country_add_to_favourites_itm:
                String countryName = clickedCountry.getSimpleName();

                String toastMessage;
                if (sqLiteHelper.isCountryFav(countryName)) {
                    sqLiteHelper.removeCountryFromFavourites(countryName);
                    toastMessage = countryName + " removed from Favourites";
                } else {
                    sqLiteHelper.addCountryToFavourites(countryName);
                    toastMessage = countryName + " added to Favourites";
                }

                Toast.makeText(
                        this,
                        toastMessage,
                        Toast.LENGTH_SHORT)
                        .show();
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_back_itm:
                Intent intent = new Intent(CountryDetailActivity.this, CountryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                break;

            case R.id.nav_profile_itm:
                startActivity(new Intent(
                        CountryDetailActivity.this, UserProfileActivity.class));
                break;

            case R.id.nav_favourites_itm:
                startActivity(new Intent(this, FavouritesActivity.class));
                break;

            case R.id.nav_logout_itm:
                Utils.showLogoutDialog(CountryDetailActivity.this, signedInUser.getUserID());
                break;
        }

        drawerLayout.close();
        return true;
    }

    private void updateUIText() {
        /* Set the title of the activity to the clicked country's name (without the Continent). */
        this.setTitle(clickedCountry.getSimpleName());

        View navViewHeader = navigationView.getHeaderView(0);
        setUpNavViewHeader(navViewHeader);
    }

    private void setUpNavViewHeader(View headerLayout) {
        TextView navUsernameTV = headerLayout.findViewById(R.id.nav_signed_in_user_name);
        navUsernameTV.setText(signedInUser.getUsername());

        TextView navUserEmailTV = headerLayout.findViewById(R.id.nav_signed_in_user_email);
        navUserEmailTV.setText(signedInUser.getEmail());
    }


}



















