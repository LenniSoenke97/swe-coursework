package ic.doc;

public class QueryBuilder {

  private String name1 = null;
  private String name2 = null;
  private String title = null;
  private Integer date1 = null;
  private Integer date2 = null;

  private QueryBuilder() {}

  public static QueryBuilder queryBuilder() {
    return new QueryBuilder();
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(this.name1, this.name2, this.title, this.date1, this.date2);
  }

  public QueryBuilder withFirstname(String s) {
    this.name1 = s;
    return this;
  }

  public QueryBuilder withLastname(String s) {
    this.name2 = s;
    return this;
  }

  public QueryBuilder withTitle(String s) {
    this.title = s;
    return this;
  }

  public QueryBuilder withPublishedAfter(Integer i) {
    this.date1 = i;
    return this;
  }

  public QueryBuilder withPublishedBefore(Integer i) {
    this.date2 = i;
    return this;
  }


}
