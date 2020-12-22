package com.example.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DictionaryFragment extends Fragment {

    TextView meaningTxt;
    TextView synmynTxt;
    String meaning;
    String symn;

    public DictionaryFragment(String meaning, String symn) {
        this.meaning = meaning;
        this.symn = symn;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragmentdict, container, false);

        meaningTxt = view.findViewById(R.id.meaning_txtview); //setting it here because this is lauched as this activity called
        synmynTxt = view.findViewById(R.id.synonyms_txtview);

        meaningTxt.setText(""); //to clear previous results after changing words
        synmynTxt.setText("");

        meaningTxt.setText(meaning);
        synmynTxt.setText(symn);



        return  view;
    }

}