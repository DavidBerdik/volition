package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

  private TextView mButtonTestItem;
  private TextView mDaysCleanMessage;

  private OnNavigationItemSelectedListener mNavigationListener = new OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.menubar_home:
          mButtonTestItem.setText(R.string.menubar_home);
          return true;
        case R.id.menubar_activity:
          mButtonTestItem.setText(R.string.menubar_activity);
          return true;
        case R.id.menubar_plan:
          mButtonTestItem.setText(R.string.menubar_plan);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    mButtonTestItem = findViewById(R.id.buttonTestItem);
    mDaysCleanMessage = findViewById(R.id.clean);

    BottomNavigationView navigation = findViewById(R.id.menubar);
    navigation.setOnNavigationItemSelectedListener(mNavigationListener);
  }

}