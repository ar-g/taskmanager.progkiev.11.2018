package e.ar_g.myapplication3.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

  @Query("SELECT * FROM Task")
  List<Task> getAll();

  @Insert
  void insert(Task task);
}
