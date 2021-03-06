package e.ar_g.taskmanager.features.productivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      getSupportFragmentManager()
        .beginTransaction()
        .add(android.R.id.content, ProductivityFragment.newInstance())
        .commit();
    }
  }
}
