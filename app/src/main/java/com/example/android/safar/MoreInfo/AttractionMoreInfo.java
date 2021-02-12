package com.example.android.safar.MoreInfo;

public class AttractionMoreInfo {

    private final int quickInfoStringResId;
    private final int[] morePicsResIds;

    public AttractionMoreInfo(int quickInfoStringResId, int[] morePicsResIds) {
        this.quickInfoStringResId = quickInfoStringResId;
        this.morePicsResIds = morePicsResIds;
    }

    public int getQuickInfoStringResId() {
        return quickInfoStringResId;
    }

    public int[] getMorePicsResIds() {
        return morePicsResIds;
    }
}










