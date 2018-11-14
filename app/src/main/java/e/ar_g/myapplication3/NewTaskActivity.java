package e.ar_g.myapplication3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity {

  private EditText etTaskName;
  private ImageButton ibNewItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_task);

    etTaskName = findViewById(R.id.etTaskName);
    ibNewItem = findViewById(R.id.ibNewItem);
    ibNewItem.setEnabled(false);


    ibNewItem.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(NewTaskActivity.this, "User clicked", Toast.LENGTH_SHORT).show();
      }
    });

    etTaskName.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }
      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }
      @Override public void afterTextChanged(Editable s) {
        ibNewItem.setEnabled(!TextUtils.isEmpty(s));
      }
    });
  }


}
