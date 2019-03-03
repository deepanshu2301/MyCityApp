package com.dipanshu.mycityapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Wall_activity extends AppCompatActivity {

    long careercount;
    EditText city;
    EditText message;

    String ans="Not Available",heading="Not Available";
    TextView submit;


    FirebaseUser firebaseUser;




    ArrayList<Article> datalist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livewall);

        {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        }


        if(firebaseUser!=null){
            FirebaseApp.initializeApp(this);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

            city=findViewById(R.id.input_city);
            message=findViewById(R.id.input_message);


            submit=findViewById(R.id.button);

//        Data_adapter adapter = new Data_adapter(datalist,new click)

            final Data_adapter adapter = new Data_adapter(datalist, new clicklistner() {
                @Override
                public void onPositionClicked(int position) {
//                Toast.makeText(getBaseContext(), "ITEM PRESSED ="+position, Toast.LENGTH_SHORT).show();

                }
            });
            recyclerView.setAdapter(adapter);

            final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

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

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    new AlertDialog.Builder(Wall_activity.this)
                            .setTitle("MyCityApp")
                            .setMessage("Do you want to post ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if(!city.getText().toString().isEmpty()){
                                        heading=city.getText().toString();
                                        String myC=heading.substring(0,1);
                                        heading=myC +heading.substring(1);

                                    }
                                    if(!message.getText().toString().isEmpty()){

                                        ans=message.getText().toString();
                                    }
                                    if(heading.charAt(heading.length()-1)!='.'){
                                        heading=heading+".";
                                    }
                                    String myC1=ans.substring(0,1);
                                    ans=myC1 +ans.substring(1);
                                    if(ans.charAt(ans.length()-1)!='.'){
                                        ans=ans+".";
                                    }

                                    Article article = new Article(heading,ans);
                                    dbref.push().setValue(article);

                                    Toast.makeText(getBaseContext(), "Submission Successful !)", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Wall_activity.this,ScrollingActivity.class));

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                finish();
                                }
                            }).show();

                }
            });
        }
        else{
            Intent intent = new Intent(getBaseContext(),login.class);
            startActivity(intent);
        }

    }
}
