package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  /*
   *Makes AdminMenu
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_admin_menu_drawer, menu);
    return true;
  }

  /*
   *Adds Functionality to AdminMenu
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    if(item.getItemId() == R.id.edit_profile){
      Intent profile = new Intent(this, ProfileActivity.class);
      startActivity(profile);
    }
    if(item.getItemId() == R.id.edit_treatment){
      Intent treatment = new Intent(this, TreatmentPlanActivity.class);
      startActivity(treatment);
    }
    if(item.getItemId() == R.id.classification){
      Intent classification = new Intent(this, ClassificationScreenActivity.class);
      startActivity(classification);
    }
    if(item.getItemId() == R.id.retake_ques){
      Intent questionarre = new Intent(this, QuestionnaireActivity.class);
      startActivity(questionarre);
    }
    if(item.getItemId() == R.id.clinical_overview){
      Intent clinical = new Intent(this, ClinicalOverviewActivity.class);
      startActivity(clinical);
    }
    return true;
  }




  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.core_navigation_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.core_navigation_plan:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.core_navigation_activity:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }
  private TextView mTextMessage;
}
