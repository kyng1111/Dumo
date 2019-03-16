package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SecondFragment extends Fragment {

    Button searchButton;
    LinearLayout ll;
    EditText searchText;
    InputMethodManager imm;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.second_fragment, container, false );
        searchButton = view.findViewById(R.id.Search_button);
        searchText = view.findViewById(R.id.Search_text);
        ll = view.findViewById(R.id.ll);

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        searchButton.setOnClickListener(myClickListener);
        ll.setOnClickListener(myClickListener);

        return view;
    }

    View.OnClickListener myClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            hideKeyboard();
            switch(v.getId()){

                case R.id.Search_button:
                    Fragment fragment = new Search_Activity();

                    Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
                    bundle.putString("searched", searchText.getText().toString()); // key , value
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_place, fragment);
                    fragmentTransaction.commit();

                    break;
                case R.id.ll:
                    break;
            }
        }

    };


    public void hideKeyboard()
    {
        imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
    }



}