package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inputtester extends AppCompatActivity {
    EditText editText;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputtester);


        editText = (EditText) findViewById(R.id.editText);

        send = (Button) findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String clientname = editText.getText().toString();
                String drug = editText.getText().toString();
                Intent intent = new Intent(inputtester.this, Demograph.class);
                intent.putExtra("name", clientname);
                intent.putExtra("drug", drug );
                startActivity(intent);
            }
        });
    }
}
