package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListsFragment extends Fragment {

    TextView searched;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.activity_list_fragment, container, false );
        searched = view.findViewById(R.id.Searched);
        searched.setText(getArguments().getString("searched"));


        return view;
    }
}