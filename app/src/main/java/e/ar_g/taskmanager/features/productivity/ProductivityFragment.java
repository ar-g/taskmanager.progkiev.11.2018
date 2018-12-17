package e.ar_g.taskmanager.features.productivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import e.ar_g.taskmanager.R;

public class ProductivityFragment extends Fragment {
  public static ProductivityFragment newInstance() {
    ProductivityFragment fragment = new ProductivityFragment();
    return fragment;
  }

  @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    GraphView graphView = new GraphView(inflater.getContext());
    graphView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    return graphView;
  }

  public static class GraphView extends View {
    private final List<Integer> tasksPerDay = Arrays.asList(10, 6, 5, 4, 5, 1, 33);
    private int measuredHeight = 0;
    private int measuredWidth = 0;
    private Path graphPath;
    private Paint paint;

    public GraphView(Context context) {
      super(context);

      paint = new Paint();
      paint.setColor(getContext().getResources().getColor(R.color.graph_blue));
    }

    private void init() {
      Integer max = Collections.max(tasksPerDay);

      int heightChartDivision = measuredHeight / max;
      int widthChartDivision = measuredWidth / tasksPerDay.size();


      graphPath = new Path();
      graphPath.reset();

      graphPath.moveTo(0, measuredHeight);

      for (int i = 0; i < tasksPerDay.size(); i++) {
        Integer tasksToday = tasksPerDay.get(i);

        graphPath.lineTo((i + 1) * widthChartDivision, measuredHeight - (tasksToday * heightChartDivision));
      }

      graphPath.lineTo(measuredWidth, measuredHeight);
      graphPath.lineTo(0, measuredHeight);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
      measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
      init();
      setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);

      canvas.drawPath(graphPath, paint);
    }
  }
}
