package e.ar_g.myapplication3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
