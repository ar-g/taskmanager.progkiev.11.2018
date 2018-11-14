package e.ar_g.myapplication3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
  }

  @Override protected void onStop() {
    super.onStop();
    Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }
}
