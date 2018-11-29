package e.ar_g.taskmanager.features.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import e.ar_g.taskmanager.R;

public class MainActivity extends AppCompatActivity {
  private ViewPager vpTabs;
  private TabLayout tlTabs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    vpTabs = findViewById(R.id.vpTabs);
    tlTabs = findViewById(R.id.tlTabs);

    FragmentManager supportFragmentManager = getSupportFragmentManager();
    TabsFragmentAdapter adapter = new TabsFragmentAdapter(supportFragmentManager);

    vpTabs.setAdapter(adapter);
    tlTabs.setupWithViewPager(vpTabs);
  }
}
