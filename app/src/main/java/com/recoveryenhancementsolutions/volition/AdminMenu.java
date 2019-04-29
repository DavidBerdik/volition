package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class AdminMenu extends AppCompatActivity {
  /*
   *Makes AdminMenu
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_drawer_menu_options, menu);
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
    if(item.getItemId() == R.id.retake_questionnaire){
      Intent questionarre = new Intent(this, QuestionnaireActivity.class);
      startActivity(questionarre);
    }
   /* if(item.getItemId() == R.id.clinical_overview){
      Intent clinical = new Intent(this, ClinicalOverviewActivity.class);
      startActivity(clinical);
    }*/
    return true;
  }


}
