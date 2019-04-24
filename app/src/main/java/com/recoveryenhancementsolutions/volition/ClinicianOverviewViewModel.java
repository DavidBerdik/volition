package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

/**
 * View model for Clinician Overview Activity
 */
 public class ClinicianOverviewViewModel extends AndroidViewModel {
   /**
    *
    * DONE
    *
    * Constructor for the Clinician Overview View Model.
    *
    * @param application Application object for the View Model.
    */
    public ClinicianOverviewViewModel(final Application application) {
      super(application);
      db = VolitionDatabase.getDatabase(this.getApplication());
    }

    /**
     * Set the test database for this View Model.
     *
     * @param db the Volition Database to use for testing.
     */
    public void setTestDatabase(final VolitionDatabase db) {
        this.db = db;
    }

    /**
     *
     * DONE
     *
     * Retrieves the name from the database.
     *
     * @return Returns LiveData of type DemographicDataEntity.
     */
     public LiveData<DemographicDataEntity> getPatientName() {
       return db.queryPatientName().getPatientName();
     }

    /**
     *
     * DONE
     *
     * Retrieves the last clean date from the database
     *
     * @return A LiveData object containing the user's 'last clean' Date
     */
     public LiveData<Date> getLastCleanDate() {
       return db.demographicDataDao().queryLastCleanDate();
     }

      /**
       *
       * Not sure if needed yet
       *
       * Retrieves the number of clean days from the Proper table.
       *
       * @return Returns LiveData of type ProperEntity.
       */
       public LiveData<ProperEntity> getCleanDays() {
         return db.properDAO().getCleanDays();
       }

       /**
       * Class for running update asynchronously
       */
      private static class updateLastUseAsync extends AsyncTask<DemographicDataDAO, Void, Void> {

        private DemographicDataDAO asyncTaskDao;

        updateDosageAsync(final DemographicDataDAO dao) {
          asyncTaskDao = dao;
        }

        /**
         *
         * Needs work
         *
         * Makes the update run on a separate thread
         *
         * @param params Parameters for this method
         * @return returns null
         */
        @Override
        protected Void doInBackground(final DemographicDataDAO... params) {
          params[0] = new ProperEntity();
          final int cleanDate = params[0].cleanDate;
          final String patientName = params[0].patientName;
          asyncTaskDao.updateProper(dose, patientName);
          return null;

        }
      }

   private VolitionDatabase db;
 }
