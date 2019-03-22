package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  /**
   * All of these public methods take in the current view @c
   * Then they check if the Corresponding RadioButton has been selected
   * If the RadioButton has been selected the corresponding field in the database is set to true
   */
  public void addSupportListener(View c) {
    radioSupport = (RadioButton) findViewById(R.id.radioSupport);
    if (radioSupport.isChecked()) {
      data.setPersonInRecovery(false);//set to false because not in Recovery
    }
  }

  public void addClientListener(View c) {
    radioClient = (RadioButton) findViewById(R.id.radioClient);
    if (radioClient.isChecked()) {
      data.setPersonInRecovery(true);
    }
  }
  public void addHeroinListener(View c) {
    radioHeroin = (RadioButton) findViewById(R.id.radioHeroin);
    if (radioHeroin.isChecked()) {
      data.setUseHeroin(true);
    }
  }

  public void addMarijuanaListener(View c) {
    radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
    if (radioMarijuana.isChecked()) {
      data.setUseMarijuana(true);
    }
  }
  public void addOpiatesListener(View c) {
    radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
    if (radioOpiates.isChecked()) {
      data.setUseOpiateOrSynth(true);
    }
  }

  public void addAlocholListener(View c) {
    radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
    if (radioAlcohol.isChecked()) {
      data.setUseAlcohol(true);
    }
  }

  public void addCocaineListener(View c) {
    radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
    if (radioCocaine.isChecked()) {
      data.setUseCrackOrCocaine(true);
    }
  }

  public void addMethListener(View c) {
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    if (radioMeth.isChecked()) {
      data.setUseMethamphetamine(true);
    }
  }

  public void addBenListener(View c) {
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    if (radioBen.isChecked()) {
      data.setUseBenzo(true);
    }
  }

  public void addTranqListener(View c) {
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    if (radioTranquilizers.isChecked()) {
      data.setUseNonBeznoTrang(true);
    }
  }
  public void addSedativesListener(View c) {
    radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
    if (radioSedatives.isChecked()) {
      data.setUseOpiateOrSynth(true);
    }
  }

  public void addInhalantsListener(View c) {
    radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
    if (radioInhalants.isChecked()) {
      data.setUseInhalants(true);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_profile);
/*
    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/
  }

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  private TextView mTextMessage;
  private RadioButton radioSupport;
  private RadioButton radioClient;
  private RadioButton radioHeroin;
  private RadioButton radioOpiates;
  private RadioButton radioAlcohol;
  private RadioButton radioCocaine;
  private RadioButton radioMarijuana;
  private RadioButton radioMeth;
  private RadioButton radioBen;
  private RadioButton radioTranquilizers;
  private RadioButton radioSedatives;
  private RadioButton radioInhalants;
  private DemographicDataEntity data = new DemographicDataEntity();
}
