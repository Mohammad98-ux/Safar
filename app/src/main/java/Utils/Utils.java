package Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.safar.R;
import com.example.android.safar.model.Attraction;
import com.example.android.safar.model.Country;
import com.example.android.safar.model.SQLiteHelper;
import com.example.android.safar.ui.AttractionMoreInfoActivity;
import com.example.android.safar.ui.CountryDetailActivity;
import com.example.android.safar.ui.FavAttractionsFragment;
import com.example.android.safar.ui.FavCountriesFragment;
import com.example.android.safar.ui.FavouritesActivity;
import com.example.android.safar.ui.MainActivity;

public class Utils {

    private static SQLiteHelper sqLiteHelper;

    public static void showAttrPopupMenu(final Activity activity, View anchor,
                                         final Attraction clickedAttraction) {
        final PopupMenu attractionPopup = new PopupMenu(activity, anchor);
        activity.getMenuInflater().inflate(
                R.menu.attraction_clicked_popup_menu, attractionPopup.getMenu());

        sqLiteHelper = new SQLiteHelper(activity);

        final String clickedAttrName = clickedAttraction.getName();
        final MenuItem favItm = attractionPopup.getMenu().getItem(3);
        if (sqLiteHelper.isAttrFav(clickedAttrName)) {
            favItm.setTitle("Remove From Favourites");
        } else {
            favItm.setTitle("Add To Favourites");
        }

        attractionPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.more_info_item:
                        activity.startActivity(new Intent(activity,
                                AttractionMoreInfoActivity.class));
                        break;

                    case R.id.find_location_item:
                        MapsUtils.findLocationOf(activity,
                                clickedAttraction.getCoordinates());
                        break;

                    case R.id.find_public_places_item:
                        showPublicPlacesDialog(activity, clickedAttraction);
                        break;

                    case R.id.add_or_remove_attr_favourite_itm:
                        String toastMessage;
                        if (favItm.getTitle().toString().contains("Add")) {
                            sqLiteHelper.addAttractionToFavourites(clickedAttrName);

                            toastMessage = clickedAttrName + " added to Favourites!";
                        } else {
                            sqLiteHelper.removeAttractionFromFavourites(clickedAttrName);
                            toastMessage = clickedAttrName + " removed from Favourites!";

                            // Make sure to remove the Item if it is in the Favourites activity.
                            if (activity instanceof FavouritesActivity) {
                                FavAttractionsFragment.attractionAdapter.remove(clickedAttraction);
                            }
                        }

                        Toast.makeText(
                                activity,
                                toastMessage,
                                Toast.LENGTH_SHORT)
                                .show();
                        break;
                }

                return true;
            }
        });

        attractionPopup.show();
    }

    public static void showCountryPopupMenu(final Activity activity, View anchor,
                                            final Country clickedCountry) {

        final PopupMenu countryPopupMenu = new PopupMenu(activity, anchor);
        activity.getMenuInflater().inflate(R.menu.country_clicked_popup_menu, countryPopupMenu.getMenu());

        sqLiteHelper = new SQLiteHelper(activity);

        MenuItem favItm = countryPopupMenu.getMenu().getItem(1);
        if (sqLiteHelper.isCountryFav(clickedCountry.getSimpleName())) {
            favItm.setTitle("Remove From Favourites");
        } else {
            favItm.setTitle("Add To Favourites");
        }

        countryPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.see_details_itm:
                        activity.startActivity(new Intent(
                                activity, CountryDetailActivity.class));
                        break;

                    case R.id.add_or_remove_country_favourite:
                        String toastMessage;

                        if (item.getTitle().toString().contains("Add")) {
                            sqLiteHelper.addCountryToFavourites(clickedCountry.getSimpleName());
                            toastMessage = clickedCountry.getSimpleName() + " added to Favourites!";
                        } else {
                            sqLiteHelper.removeCountryFromFavourites(clickedCountry.getSimpleName());
                            toastMessage = clickedCountry.getSimpleName() + " removed from Favourites!";

                            if (activity instanceof FavouritesActivity) {
                                FavCountriesFragment.countryAdapter.remove(clickedCountry);
                            }
                        }

                        Toast.makeText(activity, toastMessage, Toast.LENGTH_SHORT).show();

                        break;
                }

                return true;
            }
        });

        countryPopupMenu.show();
    }

    public static void showLogoutDialog(final Context context, final int userId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        sqLiteHelper = new SQLiteHelper(context);

        builder.setTitle("Confirm Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logoutIntent = new Intent(context, MainActivity.class);
                        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        context.startActivity(logoutIntent);

                        sqLiteHelper.logoutUser(userId);
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

    public static void goToUrl(Activity activity, String urlString) {
        Uri websiteUrl = Uri.parse(urlString);

        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, websiteUrl);
        websiteIntent.setPackage("com.android.chrome");

        activity.startActivity(websiteIntent);
    }

    public static void searchWikipedia(Activity activity, String languageAbbr, String query) {
        query = query.replaceAll(" ", "_");
        String wikiUrl = "https://" + languageAbbr + ".wikipedia.org/wiki/" + query;

        goToUrl(activity, wikiUrl);
    }

    public static void showPublicPlacesDialog(final Activity activity,
                                              final Attraction clickedAttraction) {
        final View publicPlacesDialog = LayoutInflater.from(activity)
                .inflate(R.layout.public_places_dialog, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setView(publicPlacesDialog);

        RelativeLayout coffeeShopsRL = publicPlacesDialog.findViewById(R.id.coffee_shops_RL);
        coffeeShopsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsUtils.searchFor(
                        activity,
                        clickedAttraction.getCoordinates(),
                        "Coffee Shop");
            }
        });

        RelativeLayout hairSalonRL = publicPlacesDialog.findViewById(R.id.hair_salon_RL);
        hairSalonRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsUtils.searchFor(
                        activity,
                        clickedAttraction.getCoordinates(),
                        "Hair Salon");
            }
        });

        RelativeLayout restaurantRL = publicPlacesDialog.findViewById(R.id.restaurant_RL);
        restaurantRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsUtils.searchFor(
                        activity,
                        clickedAttraction.getCoordinates(),
                        "Restaurant");
            }
        });

        RelativeLayout parksRL = publicPlacesDialog.findViewById(R.id.parks_RL);
        parksRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsUtils.searchFor(
                        activity,
                        clickedAttraction.getCoordinates(),
                        "Park");
            }
        });

        RelativeLayout gymsRL = publicPlacesDialog.findViewById(R.id.gyms_RL);
        gymsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsUtils.searchFor(
                        activity,
                        clickedAttraction.getCoordinates(),
                        "Gym");
            }
        });

        Button searchBtn = publicPlacesDialog.findViewById(R.id.custom_search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText customSearchText = publicPlacesDialog.findViewById(R.id.custom_search);
                String placeToSearch = customSearchText.getText().toString();

                if (!placeToSearch.isEmpty()) {
                    MapsUtils.searchFor(
                            activity,
                            clickedAttraction.getCoordinates(),
                            placeToSearch);
                } else {
                    Toast.makeText(
                            v.getContext(),
                            "Please, type your keyword in the \"CUSTOM SEARCH\" dialog",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public static boolean isValidUserInfo(Context context, String email, String username,
                                          String password, String confirmPass) {

        sqLiteHelper = new SQLiteHelper(context);

        return isValidEmail(email) &&
                isValidUsername(username) &&
                isValidPassword(password, confirmPass) &&
                !password.equals(username) &&
                !sqLiteHelper.isUserRegistered(username);
    }

    public static boolean isValidEmail(String email) {
        // Only one '@' in an email.
        if (!exactlyOneCharFound(email, '@')) {
            return false;
        }

        // Only one '.' in an email.
        if (!exactlyOneCharFound(email, '.')) {
            return false;
        }

        // Make sure the '@' symbol is not the last char in the email.
        // Without this, the below check my give a NullPointerException.
        if (email.indexOf("@") + 1 >= email.length()) {
            return false;
        }

        // Check that '.' follows '@' symbol.
        return email.substring(email.indexOf("@") + 1).contains(".");
    }

    /**
     * Checks if the 'text' has only one character of 'ch'.
     *
     * @param text the text in.
     * @param ch   the character that should appear only once.
     * @return true if 'ch' exists only one time in 'text', otherwise false.
     */
    public static boolean exactlyOneCharFound(String text, char ch) {
        int chRepetitionCount = 0;
        for (char c : text.toCharArray()) {
            if (c == ch) {
                chRepetitionCount++;
            }
        }

        return chRepetitionCount == 1;
    }

    public static boolean isValidUsername(String username) {
        return username.length() >= 3;
    }

    public static boolean isValidPassword(String password, String confirmPass) {
        return password.length() >= 3 && password.equals(confirmPass);
    }

}

















