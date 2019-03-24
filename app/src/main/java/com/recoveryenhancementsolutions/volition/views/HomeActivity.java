package com.recoveryenhancementsolutions.volition.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.recoveryenhancementsolutions.volition.R;

/**
 * The HomeActivity that contains functionality and interactions relevant to the activity_home
 * document. Displays a generic welcoming message to the client as well as the number of days that
 * they have been clean. Includes a navigation menu at the bottom.
 */
public class HomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    buttonTestItem = findViewById(R.id.buttonTestItem);
    daysCleanMessage = findViewById(R.id.clean);

    final BottomNavigationView navigation = findViewById(R.id.menubar);
    navigation.setSelectedItemId(R.id.menubar_home);
    navigation.setOnNavigationItemSelectedListener(navigationListener);
  }

  private OnNavigationItemSelectedListener navigationListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.menubar_home:
          buttonTestItem.setText(R.string.menubar_home);
          return true;
        case R.id.menubar_activity:
          buttonTestItem.setText(R.string.menubar_activity);
          return true;
        case R.id.menubar_plan:
          buttonTestItem.setText(R.string.menubar_plan);
          return true;
      }
      return false;
    }
  };

  private TextView buttonTestItem;
  private TextView daysCleanMessage;
}