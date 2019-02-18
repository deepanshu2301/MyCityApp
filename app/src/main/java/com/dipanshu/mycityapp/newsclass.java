package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipanshu.mycityapp.Weather_dir.DetailNewsClass;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class newsclass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        String url="https://newsapi.org/v2/top-headlines?country=in&apiKey=c7d45de0313c4e239c9518b2215c2d09";
        makenewsnetworkcall(url);
    }

    private void makenewsnetworkcall(String newsurl) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(newsurl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();

                final ArrayList<News> adaplist = parseJson(result);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("ADAPLIST SIZE IS ", "" + adaplist.size());
                        final News curr1= adaplist.get(1);
                        final News curr2 = adaplist.get(2);
                        final News curr3 = adaplist.get(3);
                        final News curr4= adaplist.get(4);
                        final News curr5 = adaplist.get(5);
                        final News curr6 = adaplist.get(6);
                        final News curr7 = adaplist.get(7);
                        final News curr8 = adaplist.get(8);
                        final News curr9 = adaplist.get(9);
                        ImageView img1 = findViewById(R.id.imageone),
                                img2 = findViewById(R.id.imagetwo),
                                img3 = findViewById(R.id.imagethree),
                                img4 = findViewById(R.id.imagefour),
                                img5 = findViewById(R.id.imagefive),
                                img6 = findViewById(R.id.imagesix),
                                img7 = findViewById(R.id.imageseven),
                                img8 = findViewById(R.id.imageeight),
                                img9 = findViewById(R.id.imagenine);

                        TextView tx1 =findViewById(R.id.textone),
                                tx2 =findViewById(R.id.texttwo),
                                tx3 =findViewById(R.id.textthree),
                                tx4 =findViewById(R.id.textfour),
                                tx5 =findViewById(R.id.textfive),
                                tx6 =findViewById(R.id.textsix),
                                tx7 =findViewById(R.id.textseven),
                                tx8 =findViewById(R.id.texteight),
                                tx9 =findViewById(R.id.textnine);
                        Picasso.get().load(curr1.getUrlToImage()).into(img1);
                        Picasso.get().load(curr2.getUrlToImage()).into(img2);
                        Picasso.get().load(curr3.getUrlToImage()).into(img3);
                        Picasso.get().load(curr4.getUrlToImage()).into(img4);
                        Picasso.get().load(curr5.getUrlToImage()).into(img5);
                        Picasso.get().load(curr6.getUrlToImage()).into(img6);
                        Picasso.get().load(curr7.getUrlToImage()).into(img7);
                        Picasso.get().load(curr8.getUrlToImage()).into(img8);
                        Picasso.get().load(curr9.getUrlToImage()).into(img9);
                        tx1.setText("" + curr1.getTitle());
                        tx2.setText("" + curr2.getTitle());
                        tx3.setText("" + curr3.getTitle());
                        tx4.setText("" + curr4.getTitle());
                        tx5.setText("" + curr5.getTitle());
                        tx6.setText("" + curr6.getTitle());
                        tx7.setText("" + curr7.getTitle());
                        tx8.setText("" + curr8.getTitle());
                        tx9.setText("" + curr9.getTitle());


                        img1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr1.getTitle());
                                String ab12=curr1.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr1.getAuthor());
                                i.putExtra("Published", curr1.getPublishedAt());
                                i.putExtra("Image", curr1.getUrlToImage());
                                i.putExtra("url", curr1.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr2.getTitle());
                                String ab12=curr2.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr2.getAuthor());
                                i.putExtra("Published", curr2.getPublishedAt());
                                i.putExtra("Image", curr2.getUrlToImage());
                                i.putExtra("url", curr2.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr3.getTitle());
                                String ab12=curr3.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr3.getAuthor());
                                i.putExtra("Published", curr3.getPublishedAt());
                                i.putExtra("Image", curr3.getUrlToImage());
                                i.putExtra("url", curr3.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr4.getTitle());
                                String ab12=curr4.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr4.getAuthor());
                                i.putExtra("Published", curr4.getPublishedAt());
                                i.putExtra("Image", curr4.getUrlToImage());
                                i.putExtra("url", curr4.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr5.getTitle());
                                String ab12=curr5.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr5.getAuthor());
                                i.putExtra("Published", curr5.getPublishedAt());
                                i.putExtra("Image", curr5.getUrlToImage());
                                i.putExtra("url", curr5.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr6.getTitle());
                                String ab12=curr6.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr6.getAuthor());
                                i.putExtra("Published", curr6.getPublishedAt());
                                i.putExtra("Image", curr6.getUrlToImage());
                                i.putExtra("url", curr6.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr7.getTitle());
                                String ab12=curr7.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr7.getAuthor());
                                i.putExtra("Published", curr7.getPublishedAt());
                                i.putExtra("Image", curr7.getUrlToImage());
                                i.putExtra("url", curr7.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr8.getTitle());
                                String ab12=curr8.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr8.getAuthor());
                                i.putExtra("Published", curr8.getPublishedAt());
                                i.putExtra("Image", curr8.getUrlToImage());
                                i.putExtra("url", curr8.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                        img9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), DetailNewsClass.class);
                                i.putExtra("Title", curr9.getTitle());
                                String ab12=curr9.getDescription();
                                if(ab12==null){
                                    ab12="";
                                }
                                i.putExtra("Description", ab12);
                                i.putExtra("Author", curr9.getAuthor());
                                i.putExtra("Published", curr9.getPublishedAt());
                                i.putExtra("Image", curr9.getUrlToImage());
                                i.putExtra("url", curr9.getUrl());
                                getBaseContext().startActivity(i);
                            }
                        });
                    }
                });
            }
        });
    }

    ArrayList<News> parseJson(String json) {

        ArrayList<News> news = new ArrayList<>();

        //Do parsing
        try {
            JSONObject root = new JSONObject(json);

            JSONArray jsonArray = root.getJSONArray("articles");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject currentObject = jsonArray.getJSONObject(i);

                //Prefer optX() instead of getX()
                String author = currentObject.optString("author");
                String title = currentObject.optString("title");
                String description = currentObject.optString("description");
                String url = currentObject.optString("url");
                String urlToImage = currentObject.optString("urlToImage");
                String published = currentObject.optString("publishedAt");

                JSONObject sourceJson = currentObject.getJSONObject("source");

                String id = sourceJson.optString("id");
                String name = sourceJson.optString("name");

                Source source = new Source(id, name);

                News currentNews = new News(author, title, description, url, urlToImage, published, source);


                news.add(currentNews);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("TAG", "parseJson: " + news.size());

        return news;
    }

}
