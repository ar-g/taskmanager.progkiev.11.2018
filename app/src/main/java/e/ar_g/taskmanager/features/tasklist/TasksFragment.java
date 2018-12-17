package e.ar_g.taskmanager.features.tasklist;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import e.ar_g.taskmanager.App;
import e.ar_g.taskmanager.R;
import e.ar_g.taskmanager.db.AppDatabase;
import e.ar_g.taskmanager.db.Task;
import e.ar_g.taskmanager.db.TaskDao;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TasksFragment extends Fragment {
  public static final int NEW_TASK_ACTIVITY = 101;
  public static final String TAG = TasksFragment.class.getSimpleName();
  private RecyclerView rv;
  private FloatingActionButton fabAddTask;
  private SwipeRefreshLayout srTasks;

  private TaskAdapter taskAdapter;

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
      }
    });

    rv = view.findViewById(R.id.rv);
    fabAddTask = view.findViewById(R.id.fabAddTask);

    fabAddTask.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        insert500Tasks();
      }
    });

    rv.setLayoutManager(new LinearLayoutManager(getContext()));
    taskAdapter = new TaskAdapter(getContext(), new OnTaskClickListener() {
      @Override public void onClick(Task task) {
        Toast.makeText(getContext(), task.getName(), Toast.LENGTH_LONG).show();
      }
    }, Collections.<Task>emptyList());
    rv.setAdapter(taskAdapter);


    loadTasks();
  }

  private void loadTasks(){
    final FragmentActivity activity = getActivity();
    if (activity != null) {

      final AppDatabase db = App.getApp(activity).getDb();
      TaskDao taskDao = db.taskDao();

      compositeDisposable.add(
        taskDao.getAllReactively()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Task>>() {
          @Override public void accept(List<Task> tasks) throws Exception {
            taskAdapter.setTasks(tasks);
          }
        }));
    }
  }

  private void insert500Tasks() {
    List<Task> tasks = new ArrayList<>();
    for (int i = 0; i < 500; i++) {
      tasks.add(new Task(i + "", i));
    }
    final FragmentActivity activity = getActivity();
    if (activity != null) {
      final AppDatabase db = App.getApp(activity).getDb();

      Task[] arrayOfTasks = tasks.toArray(new Task[500]);

      compositeDisposable.add(
        db.taskDao()
          .insertReactively(arrayOfTasks)
          .subscribeOn(Schedulers.io())
          .subscribe()
      );
    }
  }

  @Override public void onStop() {
    super.onStop();
    compositeDisposable.clear();
  }
}