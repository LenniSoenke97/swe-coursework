package ic.doc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class RecentlyUsedListTest {

  @Test
  public void listIsEmptyWhenInitialised() {
    RecentlyUsedList<String> list = new RecentlyUsedList<>();
    assertThat(list.getItems().size(), is(0));
  }

  @Test
  public void canAddItemToList() {
    RecentlyUsedList<String> list = new RecentlyUsedList<>();
    list.add("New item");
    assertThat(list.getItems().size(), is(1));
  }

  @Test
  public void getSpecificItemByRecencyTest() {
    RecentlyUsedList<String> list = new RecentlyUsedList<>();
    list.add("item1");
    list.add("item2");
    list.add("item3");
    assertThat(list.getItemAt(2), is("item2"));
  }

  @Test
  public void getMostRecentItemTest() {
    RecentlyUsedList<String> list = new RecentlyUsedList<>();
    list.add("item1");
    list.add("item2");
    list.add("item3");
    assertThat(list.getMostRecentItem(), is("item3"));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void errorIfGetIndexOutOfBoundTest() {
    new RecentlyUsedList<String>().getItemAt(2);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void errorIfNoMostRecentItemExistsTest() {
    new RecentlyUsedList<String>().getMostRecentItem();
  }

  @Test
  public void sizeDoesNotIncreaseIfDuplicateAddedTest() {
    RecentlyUsedList<String> list = new RecentlyUsedList<>();
    list.add("item 1");
    list.add("item 2");
    list.add("item 1");
    assertThat(list.getItems().size(), is(2));
  }

  @Test
  public void duplicateInsertionsGetMovedToFrontTest() {
    RecentlyUsedList<String> list = new RecentlyUsedList();
    list.add("item 1");
    list.add("item 2");
    list.add("item 1");
    assertThat(list.getMostRecentItem(), is("item 1"));
  }

}
