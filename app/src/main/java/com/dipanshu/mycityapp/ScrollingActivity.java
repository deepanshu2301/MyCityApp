package com.dipanshu.mycityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ScrollingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Button fab = findViewById(R.id.walldirect);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        if(firebaseUser!=null){
    if(firebaseUser.getUid().equals("f0DjieAtWPSLyNVZFl8Jd3gYGYO2")){
        Toast.makeText(getBaseContext(),"ADMIN LOGGED IN !",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(),admindelete.class);
        startActivity(intent);
    }
    else{
        Intent intent = new Intent(getBaseContext(),Wall_activity.class);
        startActivity(intent);
    }
}
else{
            Toast.makeText(getBaseContext(),"please Login",Toast.LENGTH_SHORT).show();

        }


            }
        });

        Button govtbtn=findViewById(R.id.govtwall);
        govtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),govtwall.class);
                startActivity(i);
            }
        });

    }

}
