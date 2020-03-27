public class Quote {
  private String quote;
  private String context;

  public Quote(String line) {
    this.reformat(line);
  }

  private void reformat(String line) {
    String[] subLines = line.split(";");
    this.quote = subLines[0];
    this.context = subLines[1];
  }

  public String getQuote() {
    return quote;
  }

  public String getContext() {
    return context;
  }

  @Override
  public String toString() {
    return "`" + quote + "' by " + context;
  }
}
