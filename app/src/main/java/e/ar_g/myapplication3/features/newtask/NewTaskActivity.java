package e.ar_g.myapplication3.features.newtask;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import e.ar_g.myapplication3.R;

public class NewTaskActivity extends AppCompatActivity implements PriorityDialogListener {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_container);

    if (savedInstanceState == null){
      FragmentManager supportFragmentManager = getSupportFragmentManager();
      FragmentTransaction transaction = supportFragmentManager.beginTransaction();
      transaction.add(R.id.flContainer, NewTaskFragment.newInstance(), NewTaskFragment.TAG);
      transaction.commit();
    }
  }

  @Override public void onPriorityChosen(int priority) {
    NewTaskFragment fragment = (NewTaskFragment) getSupportFragmentManager().findFragmentByTag(NewTaskFragment.TAG);
    fragment.onPriorityChosen(priority);
  }
}
