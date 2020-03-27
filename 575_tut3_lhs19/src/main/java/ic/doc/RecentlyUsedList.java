package ic.doc;

import java.util.ArrayList;

public class RecentlyUsedList<E> {
  private ArrayList<E> items = new ArrayList<>();

  public ArrayList<E> getItems() {
    return items;
  }

  public void add(E item) {
    if (items.contains(item)) {
      items.remove(item);
      items.add(0, item);
      return;
    }
    items.add(0, item);
  }

  public E getItemAt(int index) {
    if (index > items.size() || index < 1) {
      throw new IndexOutOfBoundsException("Index must be between 1 and " + items.size());
    }
    return items.get(index - 1);
  }

  public E getMostRecentItem() {
    if (items.size() == 0) {
      throw new IndexOutOfBoundsException("You cannot fetch most recent item since most recent "
        + "items list is empty.");
    }
    return items.get(0);
  }
}
