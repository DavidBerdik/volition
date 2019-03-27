package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
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
  public void insertDemographicData(DemographicDataEntity demographicDataEntity) {
    db.demographicDataDao().insertDemographicInfo(demographicDataEntity);
  }

  /**
   * Updates a DemographicDataEntity in the database.
   *
   * @param demographicDataEntity The entity to be updated.
   */
  public void updateDemographicData(DemographicDataEntity demographicDataEntity) {
    db.demographicDataDao().updateDemographicInfo(demographicDataEntity);
  }

  /**
   * Retrieves the last date clean as stored in the database.
   *
   * @return A LiveData object containing a Date representing the last listed date of being clean.
   */
  public LiveData<Date> getLastCleanDate() {
    return db.demographicDataDao().queryLastCleanDate();
  }

  private VolitionDatabase db;
}
