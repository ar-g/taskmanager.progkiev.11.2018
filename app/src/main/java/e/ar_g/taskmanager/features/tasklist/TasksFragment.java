package e.ar_g.taskmanager.features.tasklist;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import e.ar_g.taskmanager.App;
import e.ar_g.taskmanager.R;
import e.ar_g.taskmanager.db.AppDatabase;
import e.ar_g.taskmanager.db.Task;

public class TasksFragment extends Fragment {
  public static final int NEW_TASK_ACTIVITY = 101;
  public static final String TAG = TasksFragment.class.getSimpleName();
  private RecyclerView rv;
  private FloatingActionButton fabAddTask;
  private SwipeRefreshLayout srTasks;

  private final List<Task> tasks = new ArrayList<>();
  private TaskAdapter taskAdapter;
  private ThreadPoolExecutor executor;
  private Runnable updateAdapterRunnable;
  private Handler handler;

  public TasksFragment() {
    // Required empty public constructor
  }

  public static TasksFragment newInstance() {
    return new TasksFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_tasks, container, false);
    return view;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    srTasks = view.findViewById(R.id.srTasks);
    srTasks.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        loadTasksFromDb();
      }
    });

    rv = view.findViewById(R.id.rv);
    fabAddTask = view.findViewById(R.id.fabAddTask);

    fabAddTask.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
/*
        FragmentActivity activity = getActivity();
        if (activity != null) {
          activity.startActivityForResult(
            new Intent(getActivity(), NewTaskActivity.class),
            NEW_TASK_ACTIVITY);
        }
*/

      insertTasksThroughAsyncTask();

      }
    });

    rv.setLayoutManager(new LinearLayoutManager(getContext()));
    taskAdapter = new TaskAdapter(getContext(), new OnTaskClickListener() {
      @Override public void onClick(Task task) {
        Toast.makeText(getContext(), task.getName(), Toast.LENGTH_LONG).show();
      }
    }, tasks);
    rv.setAdapter(taskAdapter);

    tasks.add(new Task("Fragments", 0));

    int availableProcessors = Runtime.getRuntime().availableProcessors();
    executor = new ThreadPoolExecutor(
      1, availableProcessors, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
  }

  @Override public void onResume() {
    super.onResume();
    loadTasksFromDb();
  }

  private void loadTasksFromDb() {
    FragmentActivity activity = getActivity();
    if (activity != null) {
      final AppDatabase db = Room
        .databaseBuilder(activity, AppDatabase.class, "database-name")
        .allowMainThreadQueries()
        .build();

      taskAdapter.setTasks(db.taskDao().getAll());

      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          srTasks.setRefreshing(false);
        }
      }, 2000);
    }
  }


  private void insertTasksThroughHandler() {

    final FragmentActivity activity = getActivity();
    if (activity != null){
      final AppDatabase db = ((App) getActivity().getApplication()).getDb();

      handler = new Handler(Looper.getMainLooper());
      updateAdapterRunnable = new Runnable() {
        @Override public void run() {
          Log.d(TAG, Thread.currentThread().getName());
          taskAdapter.setTasks(tasks);
        }
      };

      executor.submit(new QueryTasksRunnable(handler, db, updateAdapterRunnable));
    }
  }

  public static class QueryTasksRunnable implements Runnable {
    private final Handler handler;
    private final AppDatabase db;
    private final Runnable updateAdapterRunnable;

    public QueryTasksRunnable(Handler handler, AppDatabase db, Runnable updateAdapterRunnable) {
      this.handler = handler;
      this.db = db;
      this.updateAdapterRunnable = updateAdapterRunnable;
    }

    @Override public void run() {
      for (int i = 0; i < 5000; i++) {
        db.taskDao().insert(new Task(i + "", i));
      }
      final List<Task> tasks = db.taskDao().getAll();
      Log.d(TAG, Thread.currentThread().getName());

      handler.post(updateAdapterRunnable);
    }
  }

  public void insertTasksThroughAsyncTask(){
    final FragmentActivity activity = getActivity();
    if (activity != null) {
      final AppDatabase db = ((App) getActivity().getApplication()).getDb();
      InsertTasksAsynTasks insertTasksAsynTasks = new InsertTasksAsynTasks(db, this);
      insertTasksAsynTasks.execute();
    }
  }

  public static class InsertTasksAsynTasks extends AsyncTask<Void, Void, List<Task>> {
    private final AppDatabase db;
    private final TasksFragment tasksFragment;

    public InsertTasksAsynTasks(AppDatabase db, TasksFragment tasksFragment) {
      this.db = db;
      this.tasksFragment = tasksFragment;
    }

    @Override protected List<Task> doInBackground(Void... voids) {
      for (int i = 0; i < 5000; i++) {
        db.taskDao().insert(new Task(i + "", i));
      }
      final List<Task> tasks = db.taskDao().getAll();
      Log.d(TAG, Thread.currentThread().getName());

      return tasks;
    }

    @Override protected void onPostExecute(List<Task> tasks) {
      super.onPostExecute(tasks);
      tasksFragment.taskAdapter.setTasks(tasks);
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (handler != null && updateAdapterRunnable != null){
      handler.removeCallbacks(updateAdapterRunnable);
    }
  }

  /*

  @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == NEW_TASK_ACTIVITY && resultCode == Activity.RESULT_OK && data != null){
      Task task = data.getParcelableExtra(NewTaskActivity.NEW_TASK_KEY);

      taskAdapter.addTask(task);
    }
  }

*/

}
