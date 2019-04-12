package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Entity for storing demographic information
 */

@Entity
@SuppressWarnings({"unused", "WeakerAccess", "FieldCanBeLocal"})
/*
unused
Currently many methods are unused (mainly the getMethods(), it is expected
more and more will come into use as more components are finished and added

WeakerAccess
Currently set and get can be called by any class provided they have access to
the object.  I am unaware of how other components may need this so I will hold
off on setting these methods package-private
*/

public class DemographicDataEntity {

  /**
   * get patient name
   *
   * @return String name of patient
   */
  @NonNull
  public String getPatientName() {
    return patientName;
  }

  /**
   * set name of patient
   *
   * @param patientName name of patient
   */
  public void setPatientName(final @NonNull String patientName) {
    this.patientName = patientName;
  }

  /**
   * get age of patient
   *
   * @return int patient age
   */
  public int getAge() {
    return age;
  }

  /**
   * set age of patient
   *
   * @param age age of patient
   */
  public void setAge(final int age) {
    this.age = age;
  }

  /**
   * @return Date of birth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * @param dateOfBirth the date of birth
   */
  public void setDateOfBirth(final Date dateOfBirth) {
    //Strips time and passes calendar date
    final Calendar cal = Calendar.getInstance();
    cal.setTime(dateOfBirth);
    setDateOfBirth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Sets the activity's date.
   *
   * @param year birth year
   * @param month birth month
   * @param day birth day
   */
  public void setDateOfBirth(final int year, final int month, final int day) {
    try {
      this.dateOfBirth = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(year + "-"
          + month + "-" + day);
    } catch (final ParseException e) {
      Log.e("DemographicDataEntity", Log.getStackTraceString(e));
    }
  }

  /**
   * @return gender of patient
   */
  public String getGender() {
    return gender;
  }

  /**
   * @param gender gender of patient
   */
  public void setGender(final String gender) {
    this.gender = gender;
  }

  /**
   * @return is the patient in recovery
   */
  public boolean isPersonInRecovery() {
    return isPersonInRecovery;
  }

  /**
   * @param personInRecovery sets if the person is in recovery
   */
  public void setPersonInRecovery(final boolean personInRecovery) {
    isPersonInRecovery = personInRecovery;
  }

  /**
   * @return does the patient use heroin
   */
  public boolean isUseHeroin() {
    return useHeroin;
  }

  /**
   * @param useHeroin sets the use of heroin
   */
  public void setUseHeroin(final boolean useHeroin) {
    this.useHeroin = useHeroin;
  }

  /**
   * @return does the patient use synthetics or other opiates
   */
  public boolean isUseOpiateOrSynth() {
    return useOpiateOrSynth;
  }

  /**
   * @param useOpiateOrSynth sets use of synthetics or other opiates
   */
  public void setUseOpiateOrSynth(final boolean useOpiateOrSynth) {
    this.useOpiateOrSynth = useOpiateOrSynth;
  }

  /**
   * @return does the patient use
   */
  public boolean isUseAlcohol() {
    return useAlcohol;
  }

  /**
   * @param useAlcohol sets use of alcohol
   */
  public void setUseAlcohol(final boolean useAlcohol) {
    this.useAlcohol = useAlcohol;
  }

  /**
   * @return does the patient use crack/cocaine
   */
  public boolean isUseCrackOrCocaine() {
    return useCrackOrCocaine;
  }

  /**
   * @param useCrackOrCocaine sets use of crack or cocaine
   */
  public void setUseCrackOrCocaine(final boolean useCrackOrCocaine) {
    this.useCrackOrCocaine = useCrackOrCocaine;
  }

  /**
   * @return does the patient use Marijuana
   */
  public boolean isUseMarijuana() {
    return useMarijuana;
  }

  /**
   * @param useMarijuana sets use of Marijuana
   */
  public void setUseMarijuana(final boolean useMarijuana) {
    this.useMarijuana = useMarijuana;
  }

  /**
   * @return does the patient use methamphetamine
   */
  public boolean isUseMethamphetamine() {
    return useMethamphetamine;
  }

  /**
   * @param useMethamphetamine sets use of methamphetamine
   */
  public void setUseMethamphetamine(final boolean useMethamphetamine) {
    this.useMethamphetamine = useMethamphetamine;
  }

  /**
   * @return does the patient use benzodiazepines
   */
  public boolean isUseBenzo() {
    return useBenzo;
  }

  /**
   * @param useBenzo sets use of benzodiazepines
   */
  public void setUseBenzo(final boolean useBenzo) {
    this.useBenzo = useBenzo;
  }

  /**
   * @return does the patient use tranquilizers
   */
  public boolean isUseNonBeznoTrang() {
    return useNonBeznoTrang;
  }

  /**
   * @param useNonBeznoTrang set use of tranquilizers
   */
  public void setUseNonBeznoTrang(final boolean useNonBeznoTrang) {
    this.useNonBeznoTrang = useNonBeznoTrang;
  }

  /**
   * @return does the patient use Barbitures or Hypno
   */
  public boolean isUseBarbituresOrHypno() {
    return useBarbituresOrHypno;
  }

  /**
   * @param useBarbituresOrHypno set use of barbitures or hypnotics
   */
  public void setUseBarbituresOrHypno(final boolean useBarbituresOrHypno) {
    this.useBarbituresOrHypno = useBarbituresOrHypno;
  }

  /**
   * @return does the patient use inhalants
   */
  public boolean isUseInhalants() {
    return useInhalants;
  }

  /**
   * @param useInhalants sets use of inhalants
   */
  public void setUseInhalants(final boolean useInhalants) {
    this.useInhalants = useInhalants;
  }

  /**
   * @return other drugs the patient may use
   */
  public String getUseOther() {
    return useOther;
  }

  /**
   * @param useOther sets the use of other drugs
   */
  public void setUseOther(final String useOther) {
    this.useOther = useOther;
  }

  /**
   * @return does the patient have an opioid disorder
   */
  public boolean isDisorderOpioid() {
    return disorderOpioid;
  }

  /**
   * @param disorderOpioid sets if the patient has an opioid disorder
   */
  public void setDisorderOpioid(final boolean disorderOpioid) {
    this.disorderOpioid = disorderOpioid;
  }

  /**
   * @return does the patient have an alcohol disorder
   */
  public boolean isDisorderAlcohol() {
    return disorderAlcohol;
  }

  /**
   * sets whether the patient has an alcohol disorder
   *
   * @param disorderAlcohol has alcohol disorder
   */
  public void setDisorderAlcohol(final boolean disorderAlcohol) {
    this.disorderAlcohol = disorderAlcohol;
  }

  /**
   * returns last clean date
   *
   * @return Date last clean
   */
  @NonNull
  public Date getLastClean() {
    return lastClean;
  }

  /**
   * returns the date of the last use report (Yes/No I have(n't) used since my last check-in)
   *
   * @return A Date object of the last usage report
   */
  @NonNull
  public Date getLastUseReport() {
    return lastUseReport;
  }

  /**
   * sets last clean date and last usage report date
   *
   * @param lastClean Date last clean
   * @param lastUseReport Date of the report
   */
  public void setLastClean(final @NonNull Date lastClean, final @NonNull Date lastUseReport) {
    //Strips time and passes Calendar Date
    final Calendar cal = Calendar.getInstance();
    final Calendar useRCal = Calendar.getInstance();
    cal.setTime(lastClean);
    useRCal.setTime(lastUseReport);
    setLastClean(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
    setLastUseReport(useRCal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Sets the date of last clean
   *
   * @param year last clean year
   * @param month last clean month
   * @param day last clean day
   */
  public void setLastClean(final int year, final int month, final int day) {
    try {
      this.lastClean = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(year + "-"
          + month + "-" + day);
    } catch (final ParseException e) {
      Log.e("DemographicDataEntity", "Last clean set");
    }
  }

  //Here because the other methods are not recognized as setters
  public void setLastClean(final @NonNull Date lastClean) {
    setLastClean(lastClean, new Date(0));
  }

  /**
   * Sets the date of the last usage report (called when the user answers "No")
   *
   * @param lastUseReport Date of the report
   */
  public void setLastUseReport(final @NonNull Date lastUseReport) {
    //Strips time and passes Calendar Date
    final Calendar cal = Calendar.getInstance();
    cal.setTime(lastUseReport);
    setLastUseReport(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Sets the date of the last usage report
   *
   * @param year The year of the report
   * @param month The month of the report
   * @param day The day of the report
   */
  public void setLastUseReport(final int year, final int month, final int day) {
    try {
      this.lastUseReport = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(year + "-"
          + month + "-" + day);
    } catch (final ParseException e) {
      Log.e("DemographicDataEntity", "lastUseReport set");
    }
  }

  /**
   * @param fetchID id for fetching
   */
  public void setFetchID(final int fetchID) {
    //intentionally left blank, fetchID should not be changed
  }

  /**
   * returns FetchID
   *
   * @return FetchID as int
   */
  public int getFetchID() {
    return fetchID;
  }

  @PrimaryKey
  @SuppressWarnings("NullableProblems")
  @NonNull
  private String patientName;
  private Date lastClean;
  private Date lastUseReport;

  private int age;
  private Date dateOfBirth;
  private String gender;
  private boolean isPersonInRecovery;//True - person in recovery FALSE - Family/Support

  //Booleans regarding the usage of a drug
  private boolean useHeroin;
  private boolean useOpiateOrSynth;
  private boolean useAlcohol;
  private boolean useCrackOrCocaine;
  private boolean useMarijuana;
  private boolean useMethamphetamine;
  private boolean useBenzo;
  private boolean useNonBeznoTrang;
  private boolean useBarbituresOrHypno;
  private boolean useInhalants;

  private String useOther;
  private boolean disorderOpioid;
  private boolean disorderAlcohol;
  private final int fetchID = 1;

}