package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SecondFragment extends Fragment {

    Button searchButton;
    EditText searchText;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.second_fragment, container, false );
        searchButton = view.findViewById(R.id.Search_button);
        searchText = view.findViewById(R.id.Search_text);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Search_Activity();

                Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
                bundle.putString("searched", searchText.getText().toString()); // key , value
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}