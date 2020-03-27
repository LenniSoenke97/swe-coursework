package ic.doc;

import static ic.doc.QueryBuilder.queryBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ic.doc.catalogues.Catalogue;
import ic.doc.catalogues.TestCatalogue;
import java.util.List;
import org.junit.Test;

public class BookSearchQueryTest {

  public final Catalogue catalogue = TestCatalogue.getInstance();

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = queryBuilder().withLastname("dickens").build().execute(catalogue);

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books = queryBuilder().withFirstname("Jane").build().execute(catalogue);

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = queryBuilder().withTitle("Two Cities").build().execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = queryBuilder().withPublishedBefore(1700).build().execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = queryBuilder().withPublishedAfter(1950).build().execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = queryBuilder()
      .withLastname("dickens")
      .withPublishedBefore(1840)
      .build()
      .execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = queryBuilder()
      .withTitle("of")
      .withPublishedAfter(1800)
      .withPublishedBefore(2000)
      .build()
      .execute(catalogue);

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void test() {

    int[] A = {56,-12,4,34,-3,5,35};

    int[] subLen = new int[A.length];
    subLen[0] = 1;
    int totalMaxLen = 1;
    for (int i=1; i<A.length; i++) {
      int localMaxLen = 0;
      for (int j=0;  j<i; j++) {
        if (A[i] > A[j]) {
          localMaxLen = Math.max(localMaxLen, subLen[j]);
        }
      }
      subLen[i] = localMaxLen + 1;
      totalMaxLen = Math.max(totalMaxLen, subLen[i]);
    }


    System.out.println(totalMaxLen);
  }





}
