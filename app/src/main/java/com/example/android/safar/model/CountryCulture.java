package com.example.android.safar.model;

public class CountryCulture {

    private final int imageResId;

    private final int firstStatementCultureStringResId;

    private final int secondStatementCultureStringResId;

    public CountryCulture(int imageResId,
                          int firstStatementCultureStringResId,
                          int secondStatementCultureStringResId) {
        this.imageResId = imageResId;
        this.firstStatementCultureStringResId = firstStatementCultureStringResId;
        this.secondStatementCultureStringResId = secondStatementCultureStringResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getFirstStatementCultureStringResId() {
        return firstStatementCultureStringResId;
    }

    public int getSecondStatementCultureStringResId() {
        return secondStatementCultureStringResId;
    }
}












