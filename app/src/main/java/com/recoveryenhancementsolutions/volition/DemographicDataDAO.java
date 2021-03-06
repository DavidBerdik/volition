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
   * Inserts a patient into the database, on conflict, replaces the patient information.
   *
   * @param demographicDataEntity an instance of the DateDemographicEntity class to be inserted
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertDemographicInfo(final DemographicDataEntity demographicDataEntity);

  /**
   * Modifies a patient in the database
   *
   * @param demographicDataEntity an instance of the DateDemographicEntity class to be inserted
   */
  @Update
  void updateDemographicInfo(final DemographicDataEntity demographicDataEntity);


    /*
    Other components of the volition application may need to access the data of the patient.
    Rather than having to access the object created and use the get method on them, since this
    will be a table of one, they can call the generic query on this class to receive it which
    should allow for  easier access of this information for the other functions of this application
    */

  String genericQuery = "FROM DemographicDataEntity WHERE fetchID = 1"; // commonly used component of query

  /**
   * Retrieves all of the patient's demographic data
   *
   * @return A LiveData object containing a DemographicDataEntity containing all of the patient's
   * demographic data
   */
  @Query("SELECT * " + genericQuery)
  LiveData<DemographicDataEntity> getAllDemographicData();

  /**
   * Deletes all demographic data from the database.
   */
  @Query("DELETE " + genericQuery)
  void deleteDemographicInfo();

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
  int queryPatientAge();

  /**
   * Retrieves the patient DoB
   *
   * @return a Date with the Date of Birth of the patient
   */
  @Query("SELECT dateOfBirth " + genericQuery)
  Date queryDoB();

  /**
   * Retrieves the patient gender
   *
   * @return a String with the gender of the patient
   */
  @Query("SELECT gender " + genericQuery)
  String queryPatientGender();

  /**
   * Retrieves the patient recovery status
   *
   * @return a boolean indicating the patient status, with true indicating in recovery
   */
  @Query("SELECT isPersonInRecovery " + genericQuery)
  boolean queryIsInRecovery();

  /**
   * Retrieves the patient use of heroin
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useHeroin " + genericQuery)
  boolean queryIsUsingHeroin();

  /**
   * Retrieves the patient usage of Opiates/Synthetics
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useOpiateOrSynth " + genericQuery)
  boolean queryIsUsingOpiateOrSynth();

  /**
   * Retrieves the patient usage of Alcohol
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useAlcohol " + genericQuery)
  boolean queryIsUsingAlcohol();

  /**
   * Retrieves the patient usage of Crack/Cocaine
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useCrackOrCocaine " + genericQuery)
  boolean queryIsUsingCrackOrCo();

  /**
   * Retrieves the patient usage of Marijuana
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useMarijuana " + genericQuery)
  boolean queryIsUsingMarijuana();

  /**
   * Retrieves the patient usage of Meth
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useMethamphetamine " + genericQuery)
  boolean queryIsUsingMeth();

  /**
   * Retrieves the patient usage of Benzodiazepines
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useBenzo " + genericQuery)
  boolean queryIsUsingBenzo();

  /**
   * Retrieves the patient usage of Tranquilizers
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useNonBeznoTrang " + genericQuery)
  boolean queryIsUsingNonBenzoTranq();

  /**
   * Retrieves the patient usage of Barbiturates or Hypnotics
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useBarbituresOrHypno " + genericQuery)
  boolean queryIsUsingBarbOrHypno();

  /**
   * Retrieves the patient usage of Inhalants
   *
   * @return a boolean indicating true or false
   */
  @Query("SELECT useInhalants " + genericQuery)
  boolean queryIsUsingInhalants();

  /**
   * Retrieves the patient's other drugs (Ones that do not fall in the categories above)
   *
   * @return a String containing the other drug(s) used
   */
  @Query("SELECT useOther " + genericQuery)
  String queryOtherUsedDrugs();

  /**
   * Retrieves whether patient is suffering from alcohol disorder
   *
   * @return a boolean describing whether the patient has an alcohol disorder
   */
  @Query("SELECT disorderAlcohol " + genericQuery)
  boolean queryIsHavingAlcoholDisorder();

  /**
   * Retrieves whether patient is suffering from an opioid disorder
   *
   * @return a boolean describing whether the patient has an opioid disorder
   */
  @Query("SELECT disorderOpioid " + genericQuery)
  boolean queryIsHavingOpioidDisorder();

  /**
   * Retrieves the patient's last clean date
   *
   * @return Date of their last clean
   */
  @Query("SELECT lastClean " + genericQuery)
  LiveData<Date> queryLastCleanDate();

  @Query("SELECT lastUseReport " +genericQuery)
  LiveData<Date> queryLastReportDate();

  /**
   * Updates the client's last clean date and the date of their last usage report
   *
   * @param cleanDay A Date object representing the date of last use
   * @param reportDay A Date object representing the date of the report
   */
  @Query("UPDATE DemographicDataEntity SET lastClean = :cleanDay, lastUseReport = :reportDay WHERE fetchID = 1")
  void queryUpdateLastCleanDate(final Date cleanDay, final Date reportDay);

  /**
   * Updates the date of the client's last usage report
   *
   * @param reportDay A Date object representing the date of the report
   */
  @Query("UPDATE DemographicDataEntity SET lastUseReport = :reportDay WHERE fetchID = 1")
  void queryUpdateLastReportDate(final Date reportDay);
}
