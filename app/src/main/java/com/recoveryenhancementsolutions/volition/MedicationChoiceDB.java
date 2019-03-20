package com.recoveryenhancementsolutions.volition;


public class MedicationChoiceDB {

  private static void addMedicaton(
      final VolitionDatabase db, final String medication)
  {
    MedicationChoiceEntity mc = new MedicationChoiceEntity();
    mc.medication = medication;
    db.medicationChoiceDAO().insertMedication(mc);
  }


  private static String findMedication(
      final VolitionDatabase db
  )
  {
    return db.medicationChoiceDAO().getMedication().getValue();
  }

}
