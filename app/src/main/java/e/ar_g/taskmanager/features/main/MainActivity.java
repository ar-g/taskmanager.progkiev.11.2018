package e.ar_g.taskmanager.features.main;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

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
