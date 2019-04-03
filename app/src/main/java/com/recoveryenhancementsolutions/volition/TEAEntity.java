package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = "patientId")
public class TEAEntity {

  public void setId(final int id){patientId=id;}
  public void setSubstanceInt(final int substanceI){substanceInt=substanceI;}
  public void setHealthInt(final int healthI){healthInt=healthI;}
  public void setLifestyleInt(final int lifestyleI){lifestyleInt=lifestyleI;}
  public void setCommunityInt(final int communityI){communityInt=communityI;}
  public void setSubstanceString(final String substanceS){substanceString=substanceS;}
  public void setHealthString(final String healthS){healthString=healthS;}
  public void setLifestyleString(final String lifestyleS){lifestyleString=lifestyleS;}
  public void setCommunityString(final String communityS){communityString=communityS;}

  public int getSubstanceInt(){return substanceInt;}
  public int getHealthInt(){return healthInt;}
  public int getLifestyleInt(){return lifestyleInt;}
  public int getCommunityInt(){return communityInt;}
  public String getSubstanceString(){return substanceString;}
  public String getHealthString(){return healthString;}
  public String getLifestyleString(){return lifestyleString;}
  public String getCommunityString(){return communityString;}



  @SuppressWarnings("NullableProblems")
  @NonNull
  private int patientId;

  private int substanceInt, healthInt, lifestyleInt, communityInt;
  private String substanceString, healthString, lifestyleString, communityString;

}
