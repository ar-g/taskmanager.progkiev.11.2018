package e.ar_g.taskmanager.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TaskDao {

  @Query("SELECT * FROM Task")
  List<Task> getAll();

  @Query("SELECT * FROM Task")
  Flowable<List<Task>> getAllReactively();

  @Insert
  void insert(Task task);

  @Insert
  Completable insertReactively(Task ...task);
}
