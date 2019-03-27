package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


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

/*
Currently many methods are unused (mainly the getMethods(), it is expected
more and more will come into use as more components are finished and added
*/
public class DemographicDataEntity {

  @PrimaryKey
  @NonNull
  private String patientName;

  private int age;
  private String dateOfBirth;//MM-DD-YYYY
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

  private String Other;
  private boolean disorderOpioid;
  private boolean disorderAlcohol;
  private String lastClean;//MM-DD-YYYY
  private int fetchID = 1;


  /*
  A series of set and get methods for the object
  */
  String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public boolean isPersonInRecovery() {
    return isPersonInRecovery;
  }

  public void setPersonInRecovery(boolean personInRecovery) {
    isPersonInRecovery = personInRecovery;
  }

  public boolean isUseHeroin() {
    return useHeroin;
  }

  public void setUseHeroin(boolean useHeroin) {
    this.useHeroin = useHeroin;
  }

  public boolean isUseOpiateOrSynth() {
    return useOpiateOrSynth;
  }

  public void setUseOpiateOrSynth(boolean useOpiateOrSynth) {
    this.useOpiateOrSynth = useOpiateOrSynth;
  }

  public boolean isUseAlcohol() {
    return useAlcohol;
  }

  public void setUseAlcohol(boolean useAlcohol) {
    this.useAlcohol = useAlcohol;
  }

  public boolean isUseCrackOrCocaine() {
    return useCrackOrCocaine;
  }

  public void setUseCrackOrCocaine(boolean useCrackOrCocaine) {
    this.useCrackOrCocaine = useCrackOrCocaine;
  }

  public boolean isUseMarijuana() {
    return useMarijuana;
  }

  public void setUseMarijuana(boolean useMarijuana) {
    this.useMarijuana = useMarijuana;
  }

  public boolean isUseMethamphetamine() {
    return useMethamphetamine;
  }

  public void setUseMethamphetamine(boolean useMethamphetamine) {
    this.useMethamphetamine = useMethamphetamine;
  }

  public boolean isUseBenzo() {
    return useBenzo;
  }

  public void setUseBenzo(boolean useBenzo) {
    this.useBenzo = useBenzo;
  }

  public boolean isUseNonBeznoTrang() {
    return useNonBeznoTrang;
  }

  public void setUseNonBeznoTrang(boolean useNonBeznoTrang) {
    this.useNonBeznoTrang = useNonBeznoTrang;
  }

  public boolean isUseBarbituresOrHypno() {
    return useBarbituresOrHypno;
  }

  public void setUseBarbituresOrHypno(boolean useBarbituresOrHypno) {
    this.useBarbituresOrHypno = useBarbituresOrHypno;
  }

  public boolean isUseInhalants() {
    return useInhalants;
  }

  public void setUseInhalants(boolean useInhalants) {
    this.useInhalants = useInhalants;
  }

  public String getOther() {
    return Other;
  }

  public void setOther(String other) {
    Other = other;
  }

  public boolean isDisorderOpioid() {
    return disorderOpioid;
  }

  public void setDisorderOpioid(boolean disorderOpioid) {
    this.disorderOpioid = disorderOpioid;
  }

  public boolean isDisorderAlcohol() {
    return disorderAlcohol;
  }

  public void setDisorderAlcohol(boolean disorderAlcohol) {
    this.disorderAlcohol = disorderAlcohol;
  }

  public String getLastClean() {
    return lastClean;
  }

  public void setLastClean(String lastClean) {
    this.lastClean = lastClean;
  }

  public void setFetchID(int fetchID) {
    //intentionally left blank, fetchID should not be changed
  }

  public int getFetchID() {
    return fetchID;
  }
}
