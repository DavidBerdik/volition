package com.recoveryenhancementsolutions.volition;

    import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
    import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

    import android.arch.lifecycle.LiveData;
    import android.arch.persistence.room.Dao;
    import android.arch.persistence.room.Insert;
    import android.arch.persistence.room.Query;
    import android.arch.persistence.room.Update;

@Dao
public interface TeaDAO {

  @Insert(onConflict = IGNORE)
  void insertTEA(final TEAEntity teaEntity);

  @Update(onConflict = REPLACE)
  void updateTEA(final TEAEntity teaEntity);

  @Query("SELECT * FROM TEAEntity")
  LiveData<TEAEntity> getTEA();
}
