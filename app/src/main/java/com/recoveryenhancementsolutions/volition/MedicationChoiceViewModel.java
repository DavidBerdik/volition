package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

public class MedicationChoiceViewModel extends AndroidViewModel {

  public static void populateAsync(final VolitionDatabase db){
    PopulateDbAsync task = new PopulateDbAsync(db);
    task.execute();
  }

  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  public LiveData<MedicationChoiceEntity> getMed(){
    return db.medicationChoiceDAO().getMedication();
  }

  public MedicationChoiceViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  public void insertMedication(MedicationChoiceEntity med){
    db.medicationChoiceDAO().insertMedication(med);
  }

  public static void addMedication(String medAnswer){
    MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
    medicationChoiceEntity.insertMed(medAnswer);
    db.medicationChoiceDAO().insertMedication(medicationChoiceEntity);
  }

  public static void populateWithData(VolitionDatabase db){
    MedicationChoiceActivity medicationChoiceActivity = new MedicationChoiceActivity();
    addMedication(medicationChoiceActivity.medAnswer);
  }

  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    VolitionDatabase db;

    PopulateDbAsync(VolitionDatabase db) {
      db = db;
    }

    @Override
    protected Void doInBackground(final Void... params) {
      populateWithData(db);
      return null;
    }
  }
  private static VolitionDatabase db;
}
