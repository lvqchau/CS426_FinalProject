package com.example.planter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlantFragment extends Fragment  {

    DatabaseHelper db;

    public PlantFragment() {
        // Required empty public constructor
    }

    TextView total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant, container, false);
        db = new DatabaseHelper(getActivity());
        total = view.findViewById(R.id.total_plant_count);
        total.setText(Integer.toString(db.getPlantCount(MainActivity.usernameAccount)));
        return view;
    }

}
