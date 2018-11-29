package e.ar_g.taskmanager;

import android.app.Application;
import android.arch.persistence.room.Room;

import e.ar_g.taskmanager.db.AppDatabase;

public class App extends Application {

  private AppDatabase db;

  @Override public void onCreate() {
    super.onCreate();
  }

  public AppDatabase getDb() {
    if (db == null){
      db = Room
        .databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name")
        .allowMainThreadQueries()
        .build();
    }
    return db;
  }
}
