package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Wall_activity extends AppCompatActivity {

    long careercount;
    ArrayList<Article> datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livewall);

        FirebaseApp.initializeApp(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

//        Data_adapter adapter = new Data_adapter(datalist,new click)

        final Data_adapter adapter = new Data_adapter(datalist, new clicklistner() {
            @Override
            public void onPositionClicked(int position) {
//                Toast.makeText(getBaseContext(), "ITEM PRESSED ="+position, Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adapter);

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

       dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Article art = dataSnapshot.getValue(Article.class);
                datalist.add(art);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
