package com.recoveryenhancementsolutions.volition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class MenuBarMock extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu_bar_mock);
    final Button send;
    final Button send2;
    final Button send3;
    send = findViewById(R.id.button);
    send2 = findViewById(R.id.button2);
    send3 = findViewById(R.id.button3);

    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick( final View v) {
        toDailyWellness();
      }

      private void toDailyWellness() {
        //Jenna's and Steph's code goes here
      }


    });

    send2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick( final View v) {
        toCleanTracker();
      }

      private void toCleanTracker() {
        //Jenna's and Steph's code goes here
      }
    });

    send3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick( final View v) {
        toTEA();
      }

      private void toTEA() {
        //Jenna's and Steph's code goes here
      }
    });
}
}
