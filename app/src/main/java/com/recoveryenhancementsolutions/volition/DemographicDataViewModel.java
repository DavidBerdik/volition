package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.Date;

/**
 * ViewModel for "DemographicDataEntity" in the database.
 */
public class DemographicDataViewModel extends AndroidViewModel {

  /**
   * Constructor for the ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public DemographicDataViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Sets a test database for the ViewModel. This should only be used for unit testing this
   * ViewModel.
   *
   * @param db The VolitionDatabase to use for testing the ViewModel
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  /**
   * Inserts a new DemographicDataEntity into the database.
   *
   * @param demographicDataEntity The entity to be inserted.
   */
  public void insertDemographicData(final DemographicDataEntity demographicDataEntity) {
    new insertAsyncTask(db.demographicDataDao()).execute(demographicDataEntity);
  }

  /**
   * Retrieves the last date clean as stored in the database.
   *
   * @return A LiveData object containing a Date representing the last listed date of being clean.
   */
  public LiveData<Date> getLastCleanDate() {
    return db.demographicDataDao().queryLastCleanDate();
  }

  /**
   * Retrieves the name of the patient from the db
   *
   * @return a live data object containing a string which is the patient name
   */
  public LiveData<String> getPatientName() {
    return db.demographicDataDao().queryPatientName();
  }

  /**
   * Retrieves the age of the patient from the db
   *
   * @return a live data object containing a string which is the patient name
   */
  public LiveData<Integer> getPatientAge() {
    return db.demographicDataDao().queryPatientAge();
  }

  /**
   * Retrieves the date of birth as stored in the database.
   *
   * @return A LiveData object containing a Date representing the DoB.
   */
  public LiveData<Date> getDOB() {
    return db.demographicDataDao().queryDoB();
  }

  /**
   * Retrieves the gender of the patient from the db
   *
   * @return a live data object containing a string which is the patient gender
   */
  public LiveData<String> getPatientGender() {
    return db.demographicDataDao().queryPatientGender();
  }

  /**
   * Retrieves the recovery status of the patient from the db
   *
   * @return a live data object containing a string which is the patient recovery status
   */
  public LiveData<Boolean> getIsPersonInRecovery() {
    return db.demographicDataDao().queryIsInRecovery();
  }

  /**
   * Retrieves the heroin status of the patient from the db
   *
   * @return a live data object containing a string which is the patient heroin status
   */
  public LiveData<Boolean> getUseHeroin() {
    return db.demographicDataDao().queryIsUsingHeroin();
  }

  /**
   * Retrieves the opiate status of the patient from the db
   *
   * @return a live data object containing a string which is the patient opiate status
   */
  public LiveData<Boolean> getUseOpiateOrSynth() {
    return db.demographicDataDao().queryIsUsingOpiateOrSynth();
  }

  /**
   * Retrieves the alcohol status of the patient from the db
   *
   * @return a live data object containing a string which is the patient alcohol status
   */
  public LiveData<Boolean> getUseAlcohol() {
    return db.demographicDataDao().queryIsUsingAlcohol();
  }

  /**
   * Retrieves the cocaine status of the patient from the db
   *
   * @return a live data object containing a string which is the patient cocaine status
   */
  public LiveData<Boolean> getUseCrackOrCocaine() {
    return db.demographicDataDao().queryIsUsingCrackOrCo();
  }

  /**
   * Retrieves the marijuana status of the patient from the db
   *
   * @return a live data object containing a string which is the patient marijuana status
   */
  public LiveData<Boolean> getUseMarijuana() {
    return db.demographicDataDao().queryIsUsingMarijuana();
  }

  /**
   * Retrieves the meth status of the patient from the db
   *
   * @return a live data object containing a string which is the patient meth status
   */
  public LiveData<Boolean> getUseMethamphetamine() {
    return db.demographicDataDao().queryIsUsingMeth();
  }

  /**
   * Retrieves the benzo status of the patient from the db
   *
   * @return a live data object containing a string which is the patient benzo status
   */
  public LiveData<Boolean> getUseBenzo() {
    return db.demographicDataDao().queryIsUsingBenzo();
  }

  /**
   * Retrieves the non benzo status of the patient from the db
   *
   * @return a live data object containing a string which is the patient non benzo status
   */
  public LiveData<Boolean> getUseNonBenzo() {
    return db.demographicDataDao().queryIsUsingNonBenzoTranq();
  }

  /**
   * Retrieves the barbitures status of the patient from the db
   *
   * @return a live data object containing a string which is the patient barbitures status
   */
  public LiveData<Boolean> getUseBarbitures() {
    return db.demographicDataDao().queryIsUsingBarbOrHypno();
  }

  /**
   * Retrieves the inhalant status of the patient from the db
   *
   * @return a live data object containing a string which is the patient inhalant status
   */
  public LiveData<Boolean> getUseInhalants() {
    return db.demographicDataDao().queryIsUsingInhalants();
  }

  /**
   * Retrieves the other drug of the patient from the db
   *
   * @return a live data object containing a string which is the patient other drug
   */
  public LiveData<String> getUseOtherDrug() {
    return db.demographicDataDao().queryOtherUsedDrugs();
  }

  /**
   * Retrieves the o disorder status of the patient from the db
   *
   * @return a live data object containing a string which is the patient o disorder status
   */
  public LiveData<Boolean> getOpioidDisorder() {
    return db.demographicDataDao().queryIsHavingOpioidDisorder();
  }

  /**
   * Retrieves the a disorder status of the patient from the db
   *
   * @return a live data object containing a string which is the patient a disorder status
   */
  public LiveData<Boolean> getAlcoholDisorder() {
    return db.demographicDataDao().queryIsHavingAlcoholDisorder();
  }

  /**
   * Retrieves the recovery status of the patient from the db
   *
   * @return a live data object containing a string which is the patient recovery status
   */
  public LiveData<Boolean> getInRecovery() {
    return db.demographicDataDao().queryIsInRecovery();
  }



  private static class insertAsyncTask extends AsyncTask<DemographicDataEntity, Void, Void> {

    insertAsyncTask(final DemographicDataDAO dao) {
      demographicDataDao = dao;
    }

    @Override
    protected Void doInBackground(final DemographicDataEntity... params) {
      demographicDataDao.insertDemographicInfo(params[0]);
      return null;
    }

    private DemographicDataDAO demographicDataDao;
  }

  private VolitionDatabase db;
}
