package com.example.android.safar.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;

import Utils.MapsUtils;
import Utils.Utils;

import static com.example.android.safar.ui.AttractionsFragment.clickedAttraction;

public class AttractionMoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_more_info);

        this.setTitle(clickedAttraction.getName());

        TextView attractionQuickInfo = findViewById(R.id.attraction_quick_info);
        attractionQuickInfo.setText(clickedAttraction.getAttractionMoreInfo().getQuickInfoStringResId());

        int[] attractionMorePicsResIds =
                clickedAttraction.getAttractionMoreInfo().getMorePicsResIds();

        ImageView morePicsNum1 = findViewById(R.id.attraction_more_pic_number_1);
        morePicsNum1.setImageResource(attractionMorePicsResIds[0]);

        ImageView morePicsNum2 = findViewById(R.id.attraction_more_pic_number_2);
        morePicsNum2.setImageResource(attractionMorePicsResIds[1]);

        ImageView morePicsNum3 = findViewById(R.id.attraction_more_pic_number_3);
        morePicsNum3.setImageResource(attractionMorePicsResIds[2]);

        ImageView morePicsNum4 = findViewById(R.id.attraction_more_pic_number_4);
        morePicsNum4.setImageResource(attractionMorePicsResIds[3]);

        Button findAttractionBtn = findViewById(R.id.find_attraction_btn);
        findAttractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsUtils.findLocationOf(AttractionMoreInfoActivity.this,
                        clickedAttraction.getCoordinates());
            }
        });

        Button learnMoreBtn = findViewById(R.id.attraction_see_wiki_btn);
        learnMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.searchWikipedia(AttractionMoreInfoActivity.this,
                        "en", clickedAttraction.getName());
            }
        });
    }


}













