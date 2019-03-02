package com.dipanshu.mycityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class ForexMainActivity extends AppCompatActivity {

    Double resultValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forexactivity_main);

        final Button button = (Button) findViewById(R.id.button);
        final TextView text = (TextView) findViewById(R.id.resultView);
        final TextView moneyText = (TextView) findViewById(R.id.moneyText);
        final Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        final Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resultValue= new converter().execute(fromSpinner.getSelectedItem().toString(),toSpinner.getSelectedItem().toString(),moneyText.getText().toString()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                text.setText(resultValue.toString());
            }
        });
    }
}
