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
    * Constructor for the Clinician Overview View Model.
    *
    * @param application Application object for the View Model.
    */
    public ClinicianOverviewViewModel(final Application application) {
      super(application);
      db = VolitionDatabase.getDatabase(this.getApplication());
    }

    /**
     * Retrieves the name from the Proper table.
     *
     * @return Returns LiveData of type ProperEntity.
     */
     public LiveData<ProperEntity> getName() {
       return db.properDAO().getName();
     }

     /**
      * Retrieves the date of last use from the Proper table.
      *
      * @return Returns LiveData of type ProperEntity.
      */
      public LiveData<ProperEntity> getLastUse() {
        return db.properDAO().getLastUse();
      }

      /**
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
      private static class updateLastUseAsync extends AsyncTask<ProperEntity, Void, Void> {

        private ProperDAO asyncTaskDao;

        updateDosageAsync(final ProperDAO dao) {
          asyncTaskDao = dao;
        }

        /**
         * Makes the update run on a separate thread
         *
         * @param params Parameters for this method
         * @return returns null
         */
        @Override
        protected Void doInBackground(final ProperDAO... params) {
          params[0] = new ProperEntity();
          final int var = params[0].var;
          final String var = params[0].var;
          asyncTaskDao.updateProper(dose, med);
          return null;

        }
      }

   private VolitionDatabase db;
 }
