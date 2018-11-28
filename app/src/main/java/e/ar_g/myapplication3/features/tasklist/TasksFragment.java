package e.ar_g.myapplication3.features.tasklist;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import e.ar_g.myapplication3.R;
import e.ar_g.myapplication3.db.AppDatabase;
import e.ar_g.myapplication3.db.Task;
import e.ar_g.myapplication3.features.newtask.NewTaskActivity;

public class TasksFragment extends Fragment {
  public static final int NEW_TASK_ACTIVITY = 101;

  private RecyclerView rv;
  private FloatingActionButton fabAddTask;
  private SwipeRefreshLayout srTasks;

  private final List<Task> tasks = new ArrayList<>();
  private TaskAdapter taskAdapter;

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

      insertTasks();

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


  private void insertTasks() {
    FragmentActivity activity = getActivity();
    if (activity != null) {
      final AppDatabase db = Room
        .databaseBuilder(activity, AppDatabase.class, "database-name")
        .allowMainThreadQueries()
        .build();

      taskAdapter.setTasks(db.taskDao().getAll());

      for (int i = 0; i < 20000; i++) {
        db.taskDao().insert(new Task(i + "", i));
      }
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
