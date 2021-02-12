package com.example.android.safar.model;

import com.example.android.safar.MoreInfo.CountryMoreInfo;

import java.util.ArrayList;

/**
 * Create a country object.
 */
public class Country {

    /* Name of the country, i.e. Jordan, Japan...etc. */
    private final String name;

    /* Flag image resource ID of the country. */
    private final int flagImageResId;

    /* An image represents an attraction in the country. */
    private final int imageResId;

    /* More info about the country, including:
     * - location
     * - population
     * - area
     * - capital
     * - neighbouringCountries
     * - currency
     * - quickHistory
     */
    private final CountryMoreInfo countryMoreInfo;

    private final CountryCulture culture;

    private final ArrayList<Dish> dishes;
    
    private final String ministryOfTourismUrl;

    // The attractions supported in this Country.
    private final String[] supportedAttractionsNames;

    /**
     * Create a country by specifying its name, flag and an overview image.
     */
    public Country(String name, int flagImageResId, int imageResId,
                   CountryMoreInfo countryMoreInfo, CountryCulture culture,
                   ArrayList<Dish> dishes, String ministryOfTourismUrl,
                   String[] supportedAttractionsNames) {
        this.name = name;
        this.flagImageResId = flagImageResId;
        this.imageResId = imageResId;
        this.countryMoreInfo = countryMoreInfo;
        this.culture = culture;
        this.dishes = dishes;
        this.ministryOfTourismUrl = ministryOfTourismUrl;
        this.supportedAttractionsNames = supportedAttractionsNames;
    }

    public String getSimpleName() {
        return name.substring(0, name.indexOf(','));
    }

    /**
     * Returns the name of country.
     *
     * @return the name of country.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the flag image resource ID of country.
     *
     * @return the flag image resource ID of country.
     */

    public int getFlagImageResId() {
        return flagImageResId;
    }

    /**
     * Returns the overview image resource ID of the country.
     *
     * @return the overview image resource ID of the country.
     */
    public int getImageResId() {
        return imageResId;
    }

    public CountryMoreInfo getCountryMoreInfo() {
        return countryMoreInfo;
    }

    public CountryCulture getCulture() {
        return culture;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public String getMinistryOfTourismUrl() {
        return ministryOfTourismUrl;
    }

    public String[] getSupportedAttractionsNames() {
        return supportedAttractionsNames;
    }

}














