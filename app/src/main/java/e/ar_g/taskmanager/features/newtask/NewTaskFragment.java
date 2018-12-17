package e.ar_g.taskmanager.features.newtask;

import androidx.room.Room;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import e.ar_g.taskmanager.db.AppDatabase;
import e.ar_g.taskmanager.R;
import e.ar_g.taskmanager.db.Task;

public class NewTaskFragment extends Fragment {
  public static final String TAG ="NewTaskFragment";
  private EditText etTaskName;
  private ImageButton ibNewItem;
  private TextView tvPriority;
  private int priority = 0;

  @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_new_task, container, false);
    return root;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    etTaskName = view.findViewById(R.id.etTaskName);
    ibNewItem = view.findViewById(R.id.ibNewItem);
    tvPriority = view.findViewById(R.id.tvPriority);


    ibNewItem.setEnabled(false);


    tvPriority.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(getContext(), "Нажали на приоритет", Toast.LENGTH_LONG).show();

        PriorityDialogFragment priorityDialogFragment = PriorityDialogFragment.newInstance();
        priorityDialogFragment.show(getChildFragmentManager(), PriorityDialogFragment.TAG);
      }
    });

    ibNewItem.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String taskName = etTaskName.getText().toString();
        Task task = new Task(taskName, 0);

        FragmentActivity activity = getActivity();
        if (activity != null) {
          final AppDatabase db = Room
            .databaseBuilder(activity, AppDatabase.class, "database-name")
            .allowMainThreadQueries()
            .build();

          db.taskDao().insert(task);
        }
      }
    });

    etTaskName.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }
      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }
      @Override public void afterTextChanged(Editable s) {
        ibNewItem.setEnabled(!TextUtils.isEmpty(s));
      }
    });
    Toast.makeText(getContext(), "" + priority, Toast.LENGTH_SHORT).show();
  }

  public void onPriorityChosen(int priority) {
    this.priority = priority;
    Toast.makeText(getContext(), "" + priority, Toast.LENGTH_SHORT).show();
  }

  public static NewTaskFragment newInstance() {
    NewTaskFragment fragment = new NewTaskFragment();
    return fragment;
  }
}
