package com.example.android.safar.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.android.safar.R;
import com.example.android.safar.adapters.AttractionAdapter;
import com.example.android.safar.model.Attraction;
import com.example.android.safar.model.SQLiteHelper;

import java.util.ArrayList;

import Utils.QueryUtils;
import Utils.Utils;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttractionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttractionsFragment extends Fragment {

    public static Attraction clickedAttraction;

    private SQLiteHelper sqLiteHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttractionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttractionsFragment newInstance(String param1, String param2) {
        AttractionsFragment fragment = new AttractionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AttractionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private AttractionAdapter attractionAdapter;
    private ListView attractionsLV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attraction,
                container, false);

        sqLiteHelper = new SQLiteHelper(getContext());

        attractionAdapter = new AttractionAdapter(
                getContext(), new ArrayList<Attraction>());
        attractionsLV = rootView.findViewById(R.id.attractions_list_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        attractionAdapter.clear();

        final ArrayList<Attraction> clickedCountryAttractions =
                QueryUtils.getSupportedAttractionsOf(clickedCountry);

        attractionAdapter.addAll(clickedCountryAttractions);
        attractionsLV.setAdapter(attractionAdapter);

        attractionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedAttraction = (Attraction) attractionsLV.getItemAtPosition(position);
                Utils.showAttrPopupMenu(getActivity(), view,
                        clickedAttraction);
            }
        });
    }
}



















