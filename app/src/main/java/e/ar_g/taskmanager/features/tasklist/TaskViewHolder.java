package e.ar_g.taskmanager.features.tasklist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import e.ar_g.taskmanager.R;
import e.ar_g.taskmanager.db.Task;

public class TaskViewHolder extends RecyclerView.ViewHolder {
  private TextView tvMarker;
  private TextView tvTask;

  public TaskViewHolder(@NonNull View itemView) {
    super(itemView);
    tvMarker = itemView.findViewById(R.id.tvMarker);
    tvTask = itemView.findViewById(R.id.tvTask);
  }

  public void setData(Task task){
    tvMarker.setText("•");
    tvTask.setText(task.getName());
  }
}
