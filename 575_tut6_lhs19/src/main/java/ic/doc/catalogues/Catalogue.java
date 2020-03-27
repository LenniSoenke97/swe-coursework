package ic.doc.catalogues;

import ic.doc.Book;

import java.util.List;

public interface Catalogue {

  public List<Book> searchFor(String query);

}
