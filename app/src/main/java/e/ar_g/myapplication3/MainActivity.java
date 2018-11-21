package e.ar_g.myapplication3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
  private ViewPager vpTabs;
  private TabLayout tlTabs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_fragment_manager);

    /*vpTabs = findViewById(R.id.vpTabs);
    tlTabs = findViewById(R.id.tlTabs);

    FragmentManager supportFragmentManager = getSupportFragmentManager();
    TabsFragmentAdapter adapter = new TabsFragmentAdapter(supportFragmentManager);

    vpTabs.setAdapter(adapter);
    tlTabs.setupWithViewPager(vpTabs);*/


    if (savedInstanceState == null) {
      FragmentManager supportFragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
      fragmentTransaction.add(R.id.flContainer, TasksFragment.newInstance());
      fragmentTransaction.commit();
    }
  }


/*
  public static class TabsFragmentAdapter extends FragmentPagerAdapter{
    public TabsFragmentAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      if (position == 0){
        return TasksFragment.newInstance();
      } else if (position == 1){
        return TasksFragment.newInstance();
      }
      return null;
    }

    @Override public int getCount() {
      return 2;
    }

    @Nullable @Override public CharSequence getPageTitle(int position) {
      if (position == 0){
        return "Задачи";
      } else if (position == 1){
        return "Продуктивность";
      }
      return null;
    }
  }
*/
}
