package e.ar_g.myapplication3.features.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import e.ar_g.myapplication3.features.tasklist.TasksFragment;

public class TabsFragmentAdapter extends FragmentPagerAdapter {
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
