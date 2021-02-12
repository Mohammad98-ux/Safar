package com.example.android.safar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.android.safar.R;
import com.example.android.safar.adapters.CountryAdapter;
import com.example.android.safar.model.Country;
import com.example.android.safar.model.SQLiteHelper;

import java.util.ArrayList;

import Utils.Utils;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavCountriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavCountriesFragment extends Fragment {

    private SQLiteHelper sqLiteHelper;

    public static Country prevClickedCountry;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavCountriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavCountriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavCountriesFragment newInstance(String param1, String param2) {
        FavCountriesFragment fragment = new FavCountriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqLiteHelper = new SQLiteHelper(getContext());

        // Save the originally clicked country, if the user has came from CountryDetailActivity.
        // See onBackPressed() in the host Activity.
        prevClickedCountry = clickedCountry;

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static CountryAdapter countryAdapter;
    private ListView countriesLV;
    private View emptyStateTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_country,
                container, false);

        RelativeLayout rootRL = rootView.findViewById(R.id.country_activity_root_rl);
        rootRL.setBackgroundColor(getResources().getColor(R.color.background_black));

        countryAdapter = new CountryAdapter(getContext(), new ArrayList<Country>());

        countriesLV = rootView.findViewById(R.id.country_list_view);
        emptyStateTV = rootView.findViewById(R.id.country_empty_state_text_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        countryAdapter.clear();

        final ArrayList<Country> favCountries = sqLiteHelper.getFavCountriesList();
        countryAdapter.addAll(favCountries);

        countriesLV.setAdapter(countryAdapter);
        countriesLV.setEmptyView(emptyStateTV);

        countriesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedCountry = (Country) countriesLV.getItemAtPosition(position);
                Utils.showCountryPopupMenu(getActivity(), view, clickedCountry);
            }
        });
    }


}






















