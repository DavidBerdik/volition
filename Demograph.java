package com.example.uifunctionality;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Demograph extends AppCompatActivity {

    TextView in1, in2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demograph);

        in1 = (TextView) findViewById(R.id.textView4);
        in2 = (TextView) findViewById(R.id.textView5);

        in1.setText(getIntent().getStringExtra("name"));
        in2.setText(getIntent().getStringExtra("drug"));
    }
}
