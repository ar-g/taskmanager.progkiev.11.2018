package e.ar_g.taskmanager.features.newtask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import e.ar_g.taskmanager.R;

public class PriorityDialogFragment extends DialogFragment {
  public static final String TAG = "PriorityDialogFragment";
  private PriorityDialogListener priorityDialogListener;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof Activity) {
      if (context instanceof PriorityDialogListener){
        priorityDialogListener = ((PriorityDialogListener) context);
      } else {
        throw new UnsupportedOperationException("Активити должно реализовывать интерфейс PriorityDialogListener");
      }
    }
  }

  @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.dialog_priority, container, false);
    return rootView;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    Button btnOk = view.findViewById(R.id.btnOk);
    btnOk.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        priorityDialogListener.onPriorityChosen(10);
      }
    });
  }

  public static PriorityDialogFragment newInstance() {
    PriorityDialogFragment fragment = new PriorityDialogFragment();
    return fragment;
  }
}
