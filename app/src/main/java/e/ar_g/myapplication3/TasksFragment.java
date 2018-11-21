package e.ar_g.myapplication3;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {
  public static final int NEW_TASK_ACTIVITY = 101;

  private RecyclerView rv;
  private FloatingActionButton fabAddTask;
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

    rv = view.findViewById(R.id.rv);
    fabAddTask = view.findViewById(R.id.fabAddTask);

    fabAddTask.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        /*MainActivity.this.startActivityForResult(
          new Intent(MainActivity.this, NewTaskActivity.class),
          NEW_TASK_ACTIVITY);*/
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

    return view;
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
