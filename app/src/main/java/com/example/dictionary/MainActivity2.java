package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    String word;
    String pronun;
    String meanings="";
    String symns="";
    String allsyllables="";
    String counts="";
    Data unformattedJsonData;
    TextView wordTxt;
    TextView pronountiationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        word = receiveIntent();
        unformattedJsonData = new Data(word);

        jsonParsing();
        loadFragment();

        wordTxt.setText(word);
        pronountiationTxt.setText(pronun);
    }

    private String receiveIntent() {
        wordTxt = findViewById(R.id.word_txtview);
        pronountiationTxt = findViewById(R.id.pronouncation_txtview);
        Intent intent = getIntent();
        return intent.getStringExtra("word");
    }

    private void jsonParsing()  {
        try {
            JSONObject jsonObject = new JSONObject(unformattedJsonData.fetchJsonData().body().string());
            Log.i("test",jsonObject.toString());
            word= jsonObject.getString("word");
            JSONObject pronunciation = jsonObject.getJSONObject("pronunciation");
            JSONObject syllables = jsonObject.getJSONObject("syllables");
            String count = syllables.getString("count");
            counts = count;
            Log.i("test",count);
            JSONArray listSyllble = syllables.getJSONArray("list");
            for(int i=0; i<listSyllble.length(); i++){
                //allsyllables += i+1+". "+listSyllble+"\n\n";
                String eachsylable = listSyllble.getString(i);
                Log.i("test",eachsylable);
                allsyllables +=eachsylable+"  ";
            }
            pronun = pronunciation.getString("all");
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i=0; i<results.length(); i++) {
                JSONObject eachResultObj = results.getJSONObject(i);
                String meaning = eachResultObj.getString("definition");
                meanings+=(i+1)+". "+meaning+"\n\n";
            }


            JSONObject firstObjResult = results.getJSONObject(0);
            JSONArray synmArr = firstObjResult.getJSONArray("synonyms");
            for (int i=0; i<synmArr.length(); i++) {
                String symn = synmArr.getString(i);
                symns+=i+1+". "+symn+"\n\n";
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Not Available Or Not Connected To Internet",Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickDict(View view) {
        loadFragment();
    }

    public void onClickSyllables(View view) {
        //DrawableCompat.setTint(view.getBackground(), ContextCompat.getColor(MainActivity2.this, R.color.blue));
        loadSyllableFragment();
    }

    private void loadFragment() {
        //Loading fragment in this activity
        DictionaryFragment dictionaryFragment = new DictionaryFragment(meanings,symns);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dict_frag_space,dictionaryFragment);
        fragmentTransaction.commit();
    }

    private void loadSyllableFragment() {
        SyllablesFragment syllablesFragment = new SyllablesFragment(allsyllables,counts);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dict_frag_space,syllablesFragment);
        fragmentTransaction.commit();
    }
}