package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView Nimg = findViewById(R.id.Nimg);

        OkHttpClient client = new OkHttpClient();
        String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            private Call call;
            private IOException e;

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                this.call = call;
                this.e = e;
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
//                    System.out.println("Successsfullll");
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ListView myList = findViewById(R.id.lv1);
                                ArrayList<news> news_list = new ArrayList<>();
                                JSONObject reader = new JSONObject(myResponse);
                                JSONArray data = reader.getJSONArray("data"); // get the whole json array list
//                                System.out.println("json size is : " + data.length());
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject single_news = data.getJSONObject(i);
                                    String heading = single_news.getString("heading");
                                    String description = single_news.getString("description");
                                    String time = single_news.getString("time");
                                    String link = single_news.getString("reference");
                                    String image_uri = single_news.getString("url");
                                    news new_object = new news();
                                    description = description.substring(0,150);
                                    description+="...";
                                    new_object.set_news_heading(heading);
                                    new_object.set_news_link(link);
                                    new_object.set_news_desc(description);
                                    new_object.set_news_url(image_uri);
                                    new_object.set_news_time(time);
                                    news_list.add(new_object);
                                    CustomAdapter mycusAdapter = new CustomAdapter(news_list, MainActivity.this);
                                    myList.setAdapter(mycusAdapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}