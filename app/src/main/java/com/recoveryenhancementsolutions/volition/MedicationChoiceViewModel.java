package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

public class MedicationChoiceViewModel extends AndroidViewModel {

  public static void populateAsync(final VolitionDatabase db){
    PopulateDbAsync task = new PopulateDbAsync(db);
    task.execute();
  }
  private VolitionDatabase modelDB;
  private static VolitionDatabase mDb;

  public void createDb() {
    modelDB = VolitionDatabase.getDatabase(this.getApplication());
  }

  public MedicationChoiceViewModel(final Application application) {
    super(application);
    createDb();
    mDb = VolitionDatabase.getDatabase(this.getApplication());
  }

  public void insertMedication(MedicationChoiceEntity med){
    db.medicationChoiceDAO().insertMedication(med);
  }

  public static void addMedication(String medAnswer){
    MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
    medicationChoiceEntity.insertMed(medAnswer);

    mDb.medicationChoiceDAO().insertMedication(medicationChoiceEntity);
  }

  public static void populateWithData(VolitionDatabase db){
    MedicationChoiceActivity medicationChoiceActivity = new MedicationChoiceActivity();
    addMedication(medicationChoiceActivity.medAnswer);
  }

  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final VolitionDatabase mDb;

    PopulateDbAsync(VolitionDatabase db) {
      mDb = db;
    }

    @Override
    protected Void doInBackground(final Void... params) {
      populateWithData(mDb);
      return null;
    }
  }
  private VolitionDatabase db;
}
