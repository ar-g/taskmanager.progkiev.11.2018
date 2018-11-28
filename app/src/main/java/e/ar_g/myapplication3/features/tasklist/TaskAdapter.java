package e.ar_g.myapplication3.features.tasklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import e.ar_g.myapplication3.R;
import e.ar_g.myapplication3.db.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
  private final Context context;
  private final OnTaskClickListener onTaskClickListener;

  private List<Task> tasks;

  TaskAdapter(Context context, OnTaskClickListener onTaskClickListener, List<Task> tasks) {
    this.context = context;
    this.onTaskClickListener = onTaskClickListener;
    this.tasks = tasks;
  }

  @NonNull @Override public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.task_item, parent, false);
    final TaskViewHolder taskViewHolder = new TaskViewHolder(view);

    taskViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        int adapterPosition = taskViewHolder.getAdapterPosition();
        Task task = tasks.get(adapterPosition);
        onTaskClickListener.onClick(task);
      }
    });
    Log.d("TaskAdapter", "onCreateViewHolder with adapter position " + taskViewHolder.getAdapterPosition() + " and layout position " + taskViewHolder.getLayoutPosition());

    return taskViewHolder;
  }

  @Override public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
    Log.d("TaskAdapter", "onBindViewHolder position " + position);

    if (position != RecyclerView.NO_POSITION){
      holder.setData(tasks.get(position));
    }
  }

  @Override public int getItemCount() {
    return tasks.size();
  }

  public void addTask(Task task){
    tasks.add(task);
    notifyItemInserted(tasks.size() - 1);
  }

  public void setTasks(List<Task> tasks){
    this.tasks = tasks;
    notifyDataSetChanged();
  }
}
