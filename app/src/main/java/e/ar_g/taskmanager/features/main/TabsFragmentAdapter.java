package e.ar_g.taskmanager.features.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import e.ar_g.taskmanager.features.tasklist.TasksFragment;

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
