package com.dipanshu.mycityapp.Weather_dir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipanshu.mycityapp.R;
import com.squareup.picasso.Picasso;

public class DetailNewsClass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlelayout);

        final Intent i = getIntent();
        TextView title=findViewById(R.id.stitle);
        TextView description=findViewById(R.id.scontent);
        TextView author=findViewById(R.id.sauthor);
        TextView published=findViewById(R.id.spublished);

//        final String url=i.getStringExtra("url");

        Picasso.get()
                .load(i.getStringExtra("Image"))
                .into((ImageView) findViewById(R.id.simview));

            title.setText(i.getStringExtra("Title"));
        description.setText(i.getStringExtra("Description"));
        String name=i.getStringExtra("Author");
        if (name.contentEquals("null")){
            name="Sources";
        }
        author.setText(name);
        String ss = i.getStringExtra("Published");
        published.setText(ss.substring(0,10));

    }
}
