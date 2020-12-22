package com.example.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SyllablesFragment extends Fragment {
    private String syllables;
    private String count;
    private TextView syllablesTxtView;
    private TextView countTxtView;

    public SyllablesFragment(String syllables, String count) {
        this.syllables = syllables;
        this.count = count;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_syllables, container, false);

        syllablesTxtView = view.findViewById(R.id.syllables_txtview);
        countTxtView = view.findViewById(R.id.count_txtview);

        syllablesTxtView.setText(""); //to clear previous results after changing words
        countTxtView.setText("");

        syllablesTxtView.setText(syllables);
        countTxtView.setText(count);

        return view;
    }

}