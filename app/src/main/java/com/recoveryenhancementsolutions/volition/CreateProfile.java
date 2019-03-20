package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * Class for running only create_profile.xml
 */
public class CreateProfile extends AppCompatActivity {

  private RadioButton support, client, radioHeroin, radioOpiates, radioAlcohol, radioCocaine, radioMarijuana, radioMeth, radioBen, radioTranquilizers, radioSedatives, radioInhalants;
  private DemographicDataEntity data = new DemographicDataEntity();
  public void addSupportListener() {
    support = (RadioButton) findViewById(R.id.radioSupport);
    support.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setPersonInRecovery(false);
        }
      }
    });
  }

  public void addClientListener() {
    client = (RadioButton) findViewById(R.id.radioClient);
    client.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setPersonInRecovery(true);
        }
      }
    });
  }

  public void addHeroinListener() {
    radioHeroin = (RadioButton) findViewById(R.id.radioHeroin);
    radioHeroin.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseHeroin(true);
        }
      }
    });
  }

  public void addOpiatesListener() {
    radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
    radioOpiates.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseOpiateOrSynth(true);
        }
      }
    });
  }

  public void addAlocholListener() {
    radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
    radioAlcohol.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseAlcohol(true);
        }
      }
    });
  }

  public void addCocaineListener() {
    radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
    radioCocaine.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseCrackOrCocaine(true);
        }
      }
    });
  }

  public void addMarijuanaListener() {
    radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
    radioMarijuana.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseMarijuana(true);
        }
      }
    });
  }

  public void addMethListener() {
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    radioMeth.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseMethamphetamine(true);
        }
      }
    });
  }

  public void addBenListener() {
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    radioBen.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseBenzo(true);
        }
      }
    });
  }

  public void addTranqListener() {
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    radioTranquilizers.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseNonBeznoTrang(true);
        }
      }
    });
  }

  public void addSedativesListener() {
    radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
    radioSedatives.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
         // data.setUseSedatives(true);
        }
      }
    });
  }

  public void addInhanlentsListener() {
    radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
    radioInhalants.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseInhalants(true);
        }
      }
    });
  }

  // radioHeroin,radioOpiates,radioAlcohol,radioCocaine, radioMarijuana, radioMeth, radioBen,radioTranquilizers,radioSedatives,radioInhalants;
  public void addAllListeners() {
    support = (RadioButton) findViewById(R.id.radioSupport);
    client = (RadioButton) findViewById(R.id.radioClient);
    radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
    radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
    radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
    radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
    radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
  }

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_profile);
    addSupportListener();
    addClientListener();
    addAlocholListener();
    addBenListener();
    addCocaineListener();
    addHeroinListener();
    addInhanlentsListener();
    addMarijuanaListener();
    addMethListener();
    addOpiatesListener();
    addSedativesListener();
    addTranqListener();
    addAllListeners();
  }

}
