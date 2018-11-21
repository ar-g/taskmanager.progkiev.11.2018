package e.ar_g.myapplication3;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
  private final String name;
  private final int priority;

  public Task(String name, int priority) {
    this.name = name;
    this.priority = priority;
  }

  public String getName() {
    return name;
  }

  public int getPriority() {
    return priority;
  }

  @Override public int describeContents() { return 0; }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeInt(this.priority);
  }
  protected Task(Parcel in) {
    this.name = in.readString();
    this.priority = in.readInt();
  }
  public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
    @Override public Task createFromParcel(Parcel source) {return new Task(source);}
    @Override public Task[] newArray(int size) {return new Task[size];}
  };
}
