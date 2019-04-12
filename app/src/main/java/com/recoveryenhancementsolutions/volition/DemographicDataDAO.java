package com.recoveryenhancementsolutions.volition;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.Date;


@Dao
@SuppressWarnings("unused")
/*
Currently many methods are unused, it is expected more and more will
come into use as more components are finished and added
*/

/*
 *  DAO file for the DataDemographic Entity
 */
public interface DemographicDataDAO {

  /**
   * Inserts a patient into the database
   *
   * @param demographicDataEntity an instance of the DateDemographicEntity class to be inserted
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertDemographicInfo(DemographicDataEntity demographicDataEntity);

  /**
   * Modifies a patient in the database
   *
   * @param demographicDataEntity an instance of the DateDemographicEntity class to be inserted
   */
  @Update
  void updateDemographicInfo(DemographicDataEntity demographicDataEntity);


    /*
    Other components of the volition application may need to access the data of the patient.
    Rather than having to access the object created and use the get method on them, since this
    will be a table of one, they can call the generic query on this class to receive it which
    should allow for  easier access of this information for the other functions of this application
    */

  String genericQuery = "FROM DemographicDataEntity WHERE fetchID = 1"; // commonly used component of query

  /**
   * Retrieves the patient name
   *
   * @return a string with the patient name
   */
  @Query("SELECT patientName " + genericQuery)
  LiveData<String> queryPatientName();

  /**
   * Retrieves the patient age
   *
   * @return an int with the patient age
   */
  @Query("SELECT age " + genericQuery)
  LiveData<Integer> queryPatientAge();

  /**
   * Retrieves the patient DoB
   *
   * @return a Date with the Date of Birth of the patient
   */
  @Query("SELECT dateOfBirth " + genericQuery)
  LiveData<Date> queryDoB();

  /**
   * Retrieves the patient gender
   *
   * @return a String with the gender of the patient
   */
  @Query("SELECT gender " + genericQuery)
  LiveData<String> queryPatientGender();

  /**
   * Retrieves the patient recovery status
   *
   * @return a boolean indicating the patient status, with true indicating in recovery
   */
  @Query("SELECT isPersonInRecovery " + genericQuery)
  LiveData<Boolean> queryIsInRecovery();

  /**
   * Retrieves the patient use of heroin
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useHeroin " + genericQuery)
  LiveData<Boolean> queryIsUsingHeroin();

  /**
   * Retrieves the patient usage of Opiates/Synthetics
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useOpiateOrSynth " + genericQuery)
  LiveData<Boolean> queryIsUsingOpiateOrSynth();

  /**
   * Retrieves the patient usage of Alcohol
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useAlcohol " + genericQuery)
  LiveData<Boolean> queryIsUsingAlcohol();

  /**
   * Retrieves the patient usage of Crack/Cocaine
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useCrackOrCocaine " + genericQuery)
  LiveData<Boolean> queryIsUsingCrackOrCo();

  /**
   * Retrieves the patient usage of Marijuana
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useMarijuana " + genericQuery)
  LiveData<Boolean> queryIsUsingMarijuana();

  /**
   * Retrieves the patient usage of Meth
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useMethamphetamine " + genericQuery)
  LiveData<Boolean> queryIsUsingMeth();

  /**
   * Retrieves the patient usage of Benzodiazepines
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useBenzo " + genericQuery)
  LiveData<Boolean> queryIsUsingBenzo();

  /**
   * Retrieves the patient usage of Tranquilizers
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useNonBeznoTrang " + genericQuery)
  LiveData<Boolean> queryIsUsingNonBenzoTranq();

  /**
   * Retrieves the patient usage of Barbiturates or Hypnotics
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useBarbituresOrHypno " + genericQuery)
  LiveData<Boolean> queryIsUsingBarbOrHypno();

  /**
   * Retrieves the patient usage of Inhalants
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useInhalants " + genericQuery)
  LiveData<Boolean> queryIsUsingInhalants();

  /**
   * Retrieves the patient's other drugs (Ones that do not fall in the categories above)
   *
   * @return a String containing the other drug(s) used
   */
  @Query("SELECT useOther " + genericQuery)
  LiveData<String> queryOtherUsedDrugs();

  /**
   * Retrieves whether patient is suffering from alcohol disorder
   *
   * @return a boolean describing whether the patient has an alcohol disorder
   */
  @Query("SELECT disorderAlcohol " + genericQuery)
  LiveData<Boolean> queryIsHavingAlcoholDisorder();

  /**
   * Retrieves whether patient is suffering from an opioid disorder
   *
   * @return a boolean describing whether the patient has an opioid disorder
   */
  @Query("SELECT disorderOpioid " + genericQuery)
  LiveData<Boolean> queryIsHavingOpioidDisorder();

  /**
   * Retrieves the patient last clean date
   *
   * @return Date of their last clean
   */
  @Query("SELECT lastClean " + genericQuery)
  LiveData<Date> queryLastCleanDate();

  /**
   * Retrieves the entire patient, needs more testing
   *
   * @return the patient
   */
  @Query("SELECT * " + genericQuery)
  LiveData<DemographicDataEntity> queryGetPatient();
}