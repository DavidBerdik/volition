package com.recoveryenhancementsolutions.volition;
/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * CHANGED BY Duquesne University Spring 2019 COSC 445W Systems Analysis and Software Design class.
 * This is a modification of the "Room with a View" class WordRoomDatabase obtained from
 * https://github.com/googlecodelabs/android-room-with-a-view/blob/master/app/src/main/java/com/example/android/roomwordssample/WordRoomDatabase.java
 * Modifications are largely to change the entities and DAO methods as well as the class name.
 * Also modified to conform with project coding standards.
 */

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Class in which database entities and DAO instantiation methods are specified (see the TO-DO
 * lines, which should not be removed) and that provides a static method for obtaining an instance
 * of the database.  It is also possible to modify the provided code so that the app begins with
 * test data pre-populated in the database.
 */

// TODO: Place entity class references here, one class per line (to facilitate merges).

@Database(
    entities = {
        DemographicDataEntity.class
        UserActivityEntity.class
    },
    version = 1)
@TypeConverters(DateConverter.class)

public abstract class VolitionDatabase extends RoomDatabase {

  // TODO: Place DAO instantiation method calls here, as in the following commented-out example
  // public abstract WordDao wordDao();
  public abstract UserActivitiesDao userActivitiesDao();
  public abstract DemographicDataDAO demographicDataDAO();

  /**
   * Factory method implementing Singleton design pattern for VolitionDatabase class.
   *
   * @param context Object providing access to application context.
   * @return Instance of VolitionDatabase (same instance returned on every call).
   */
  static VolitionDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (VolitionDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              VolitionDatabase.class, "volition_database")
              // Wipes and rebuilds instead of migrating if no Migration object.
              // Migration is not part of this codelab.
              .fallbackToDestructiveMigration()
              .addCallback(volitionDatabaseCallback)
              .build();
        }
      }
    }
    return INSTANCE;
  }

  /**
   * Object providing methods that are called if an existing database is opened or a new database is
   * created.
   */
  private static RoomDatabase.Callback volitionDatabaseCallback = new RoomDatabase.Callback() {

    /**
     * Method called when an existing database is opened.
     * @param db Object representing database that has been opened.
     */
    @Override
    public void onOpen(@NonNull final SupportSQLiteDatabase db) {
      super.onOpen(db);
      // If you want to clear and repopulate data when the app restarts, keep the following
      // line uncommented and fill in the PopulateDbAsync skeleton code below.
      new PopulateDbAsync(INSTANCE).execute();
    }

    /**
     * Method called when a database is first created.
     * @param db Object representing database that is being created.
     */
    @Override
    public void onCreate(@NonNull final SupportSQLiteDatabase db) {
      super.onCreate(db);
      // If you want to populate data when the database is created for the first time,
      // keep the following line uncommented and fill in the PopulateDbAsync skeleton code below.
      new PopulateDbAsync(INSTANCE).execute();
    }
  };

  /**
   * Skeleton code that does nothing but could be filled in to clear the database and populate it
   * with test data in the background.
   */
  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    // If you want to clear and initialize the database, add variables to hold DAOs here as shown in the following comment
    // private final WordDao mDao;
    private final UserActivitiesDao userActivitiesDao;
    private final DemographicDataDAO demographicDataDAO;

    PopulateDbAsync(final VolitionDatabase db) {
      // If you want to clear and initialize the database, call the DAO instantiation methods here as shown in the following comment
      // mDao = db.wordDao();
      userActivitiesDao = db.userActivitiesDao();
      demographicDataDAO = db.demographicDataDAO();
    }

    @Override
    protected Void doInBackground(final Void... params) {
      // If you want to clear and initialize the database, place code here such as in the following commented-out example:
      /*
      // Start the app with a clean database every time.
      // Not needed if you only populate on creation.
      mDao.deleteAll();

      Word word = new Word("Hello");
      mDao.insert(word);
      word = new Word("World");
      mDao.insert(word);
      */
      return null;
    }
  }
  // marking the instance as volatile to ensure atomic access to the variable
  private static volatile VolitionDatabase INSTANCE;

}