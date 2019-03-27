package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

public class MedicationChoiceViewModel extends AndroidViewModel {

  public MedicationChoiceViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  public void insertMedication(MedicationChoiceEntity med){
    db.medicationChoiceDAO().insertMedication(med);
  }
  private VolitionDatabase db;
}
