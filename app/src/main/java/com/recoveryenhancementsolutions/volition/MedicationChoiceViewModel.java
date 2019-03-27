package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;

public class MedicationChoiceViewModel extends AndroidViewModel {

  public MedicationChoiceViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  public void insertMedication(final String med){
    final MedicationChoiceEntity entity = new MedicationChoiceEntity();
    entity.insertMed(med);
  }

  public void insertMedication(final MedicationChoiceEntity medicationChoiceEntity){
    db.medicationChoiceDAO().insertMedication(medicationChoiceEntity);
  }
  private VolitionDatabase db;
}
