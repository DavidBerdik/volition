package com.recoveryenhancementsolutions.volition;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Implements a drawer menu, including functionality for closing the drawer when the back button is
 * pressed or when the screen is tapped. To include a drawer menu in an activity, declare this class
 * as its subclass.
 */
public abstract class DrawerMenuActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  /**
   * Show the UI with the drawer menu.
   */
  @Override
  public void setContentView(final int layoutID) {
    final ConstraintLayout fullLayout = (ConstraintLayout) getLayoutInflater()
        .inflate(R.layout.activity_drawer_menu, null);
    final ConstraintLayout activityContent = fullLayout.findViewById(R.id.activity_content);

    getLayoutInflater().inflate(layoutID, activityContent, true);
    super.setContentView(fullLayout);

    final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final DrawerLayout drawer = findViewById(R.id.drawer_layout);
    final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    final NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  /**
   * Close the drawer menu when the back button is pressed.
   */
  @Override
  public void onBackPressed() {
    final DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  /**
   * Handles the item selection. Functionality will be added by Brad or Rahul
   *
   * @param item The item selected
   * @return Should be changed to true once menu items are handled
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    final int id = item.getItemId();

    return super.onOptionsItemSelected(item);
  }

  /**
   * Send the user to the correct activity depending on which option is selected. Functionality will
   * be added by Brad or Rahul
   *
   * @param item The item selected
   * @return true since the drawer has been closed
   */
  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    final int id = item.getItemId();

    if (id == R.id.edit_profile) {
      Boolean EDIT_MODE2 = true;
      final Intent intent = new Intent(DrawerMenuActivity.this, ProfileActivity.class);
      intent.putExtra("editMode2", EDIT_MODE2);
      startActivity(intent);
      item.setChecked(false);
    } else if (id == R.id.edit_treatment) {
      final Intent intent = new Intent(DrawerMenuActivity.this, TreatmentPlanActivity.class);
      startActivity(intent);
      item.setChecked(false);
    } else if (id == R.id.view_classification) {
      final Intent intent = new Intent(DrawerMenuActivity.this, ViewSeverityLevelActivity.class);
      startActivity(intent);
      item.setChecked(false);
    } else if (id == R.id.retake_questionnaire) {
      Intent intent = new Intent(DrawerMenuActivity.this, QuestionnaireActivity.class);
      startActivity(intent);
      item.setChecked(false);
    } else if (id == R.id.clinical_overview) {
      Intent intent = new Intent(DrawerMenuActivity.this, ClinicalScreenActivity.class);
      startActivity(intent);
      item.setChecked(false);
    }

    final DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return false;
  }
}