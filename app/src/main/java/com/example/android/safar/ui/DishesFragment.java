package com.example.android.safar.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.android.safar.R;
import com.example.android.safar.adapters.DishesAdapter;
import com.example.android.safar.model.Dish;

import java.util.ArrayList;

import Utils.MapsUtils;

import static com.example.android.safar.ui.CountryDetailActivity.clickedCountry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DishesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DishesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DishesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DishesFragment newInstance(String param1, String param2) {
        DishesFragment fragment = new DishesFragment();
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

    private DishesAdapter dishesAdapter;
    private GridView dishesGV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_famous_dishes,
                container, false);

        dishesAdapter = new DishesAdapter(getContext(), new ArrayList<Dish>());
        dishesGV = rootView.findViewById(R.id.dishes_grid_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        dishesAdapter.clear();

        ArrayList<Dish> dishesList = clickedCountry.getDishes();
        dishesAdapter.addAll(dishesList);

        dishesGV.setAdapter(dishesAdapter);

        dishesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish clickedDish = (Dish) dishesGV.getItemAtPosition(position);
                showConfirmSearchForDishDialog(clickedDish);
            }
        });

    }

    private void showConfirmSearchForDishDialog(final Dish dish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Do you want to search for " + dish.getName() + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MapsUtils.searchForDish(getActivity(), dish.getName());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}


























