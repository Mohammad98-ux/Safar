package com.example.android.safar.model;

import com.example.android.safar.MoreInfo.AttractionMoreInfo;

public class Attraction {

    /* Name of the attraction. */
    private final String name;

    /* Image resource ID of the attraction. */
    private final int imageResId;

    private final AttractionMoreInfo attractionMoreInfo;

    private final Coordinates coordinates;

    /**
     * Make an attraction by providing a name and an image resource ID.
     *
     * @param name        of the attraction.
     * @param imageResId  of the attraction.
     * @param coordinates of the attraction on the map.
     */
    public Attraction(String name, int imageResId,
                      AttractionMoreInfo attractionMoreInfo,
                      Coordinates coordinates) {
        this.name = name;
        this.imageResId = imageResId;
        this.attractionMoreInfo = attractionMoreInfo;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public AttractionMoreInfo getAttractionMoreInfo() {
        return attractionMoreInfo;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
