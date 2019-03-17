package com.recoveryenhancementsolutions.volition;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import java.util.List;

@Dao
public interface DemographicDataDAO {
    @Insert
    void insertActivity(DemographicDataEntity demographicDataEntity);
    @Delete
    void deleteActivity(DemographicDataEntity demographicDataEntity);

}
