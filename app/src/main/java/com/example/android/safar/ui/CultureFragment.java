package com.example.android.safar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android.safar.R;
import com.example.android.safar.model.CountryCulture;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CultureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CultureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CultureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CultureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CultureFragment newInstance(String param1, String param2) {
        CultureFragment fragment = new CultureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ImageView cultureImage;
    private TextView cultureFirstStatement;
    private TextView cultureSecondStatement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_culture,
                container, false);

        cultureImage = rootView.findViewById(R.id.country_culture_image);
        cultureFirstStatement = rootView.findViewById(R.id.country_culture_first_statement_tv);
        cultureSecondStatement = rootView.findViewById(R.id.country_culture_second_statement_tv);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        CountryCulture clickedCountryCulture = clickedCountry.getCulture();
        cultureImage.setImageResource(clickedCountryCulture.getImageResId());
        cultureFirstStatement.setText(clickedCountryCulture.getFirstStatementCultureStringResId());
        cultureSecondStatement.setText(clickedCountryCulture.getSecondStatementCultureStringResId());

    }
}






















