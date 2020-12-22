package com.example.dictionary;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Data {
    private String url, word;
    private String apiKey = "2e9d1450ddmsh12a3ed54dc52855p1396bdjsnd3838ea0f569";


    public Data(String word) {
        this.word = word;
    }

    public Response fetchJsonData() throws IOException {
        url = "https://wordsapiv1.p.rapidapi.com/words/"+word.toLowerCase();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", apiKey)
                .addHeader("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response;
    }

}
