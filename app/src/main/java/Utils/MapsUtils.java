package Utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.android.safar.model.Coordinates;

public class MapsUtils {

    /**
     * Gets the directions from the user's current location to the 'destination'
     * geographical place.
     *
     * @param activity    the calling the activity.
     * @param destination the destination where the user needs to go.
     */
    public static void findLocationOf(Activity activity, Coordinates destination) {
        Uri destUri =
                Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" +
                        destination.getLatitude() + "," + destination.getLongitude());

        Intent intent = new Intent(Intent.ACTION_VIEW, destUri);
        intent.setPackage("com.google.android.apps.maps");

        activity.startActivity(intent);
    }

    /**
     * Search nearby places near the 'location' coordinates provided.
     *
     * @param activity    the calling the activity.
     * @param location    the location of which the user wants to search for nearby places.
     * @param searchQuery the keyword of the search query.
     */
    public static void searchFor(Activity activity, Coordinates location, String searchQuery) {
        Uri uri = Uri.parse("geo:" + location.getLatitude() +
                "," + location.getLongitude() + "?q=" + searchQuery);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");

        activity.startActivity(intent);
    }

    public static void searchForDish(Activity activity, String searchQuery) {
        searchQuery  = searchQuery.concat(" restaurant");
        searchFor(activity,new Coordinates(0,0),searchQuery);
    }

}





















