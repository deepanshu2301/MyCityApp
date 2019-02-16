package com.dipanshu.mycityapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WritingActivity extends AppCompatActivity{

//    Context ctx = getParent();
    EditText city;
    EditText message;
    TextView submit;
    ImageButton button1;
    String ans="Not Available",heading="Not Available";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writingactivity);

        city=findViewById(R.id.input_city);
        message=findViewById(R.id.input_message);


        submit=findViewById(R.id.button);
        button1=findViewById(R.id.button1);

        final DatabaseReference dbref= FirebaseDatabase.getInstance().getReference();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(WritingActivity.this)
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
                                startActivity(new Intent(WritingActivity.this,MENU.class));

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

}
