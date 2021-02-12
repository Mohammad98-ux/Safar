package com.example.android.safar.MoreInfo;

public class CountryMoreInfo {

    private final String location;
    private final double population;
    private final float area;
    private final String capital;
    private final String neighbouringCountries;
    private final String currency;
    private final int quickHistoryStrResId;

    public CountryMoreInfo(String location, double population, float area, String capital,
                           String neighbouringCountries, String currency, int quickHistoryStrResId) {
        this.location = location;
        this.population = population;
        this.area = area;
        this.capital = capital;
        this.neighbouringCountries = neighbouringCountries;
        this.currency = currency;
        this.quickHistoryStrResId = quickHistoryStrResId;
    }

    public String getLocation() {
        return location;
    }

    public double getPopulation() {
        return population;
    }

    public float getArea() {
        return area;
    }

    public String getCapital() {
        return capital;
    }

    public String getNeighbouringCountries() {
        return neighbouringCountries;
    }

    public String getCurrency() {
        return currency;
    }

    public int getQuickHistoryStrResId() {
        return quickHistoryStrResId;
    }
}




















