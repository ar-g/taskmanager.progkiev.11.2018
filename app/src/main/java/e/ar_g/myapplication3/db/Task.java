package e.ar_g.myapplication3.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Task implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  public int id;
  public final String name;
  public final int priority;
  

  public Task(String name, int priority) {
    this.name = name;
    this.priority = priority;
  }

  public int getId() {
    return id;
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
