package com.recoveryenhancementsolutions.volition;

/**
 * Methods called by the activity to set and display the medication choice from the user
 */

public class MedicationChoiceDB {

  /**
   * Called by a button to insert a medication choice into the database
   *
   * @param db volition database needed to store the choice
   * @param medication String passed which represents the selection of a medication
   */

  private static void addMedicaton(
      final VolitionDatabase db, final String medication) {
    MedicationChoiceEntity mc = new MedicationChoiceEntity();
    mc.medication = medication;
    db.medicationChoiceDAO().insertMedication(mc);
  }

  /**
   * Returns a String to be displayed in an activity when called
   *
   * @param db volition database where medication choice was stored
   * @return String representing the users medication choice
   */

  private static String findMedication(
      final VolitionDatabase db
  ) {
    return db.medicationChoiceDAO().getMedication().getValue();
  }

}
