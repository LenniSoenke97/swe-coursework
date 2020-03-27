import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


public class PickManyQuotesTest {

  @Test
  public void test() throws Exception {

    final String quote = "What a test!;Anonymous";
    final String reformattedQuote = "`What a test!' by Anonymous";

    String input = String.join("\n", quote + " Line 1", quote + " Line 2", quote + " Line 3");

    BufferedReader in = new BufferedReader(new StringReader(input));

    List<Quote> quotes = PickManyQuotes.loadQuotes(in);

    assertThat(quotes.size(), is(3));
    for (int i = 0; i < 3; i++) {
      assertThat(quotes.get(i).toString(), containsString(reformattedQuote + " Line " + (i + 1)));
    }

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    PickManyQuotes.reformat(quotes, new int[]{1});

    String output = outstream.toString();

    assertThat(output, containsString(reformattedQuote + " Line " + 1));

  }

  @Test
  public void wrongNumberOfArguments() throws Exception {

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    String[] args = {};
    PickManyQuotes.main(args);

    String output = outstream.toString();

    assertThat(output, containsString("Please enter two or more arguments"));
  }

  @Test
  public void fileNotFound() throws Exception {
    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    String[] args = {"NotAFile.txt", "1", "2"};
    PickManyQuotes.main(args);

    String output = outstream.toString();

    assertThat(output, containsString("Source file does not exist"));
  }

  @Test
  public void argsAreIntegers() throws Exception {
    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    String[] args = {"NotAFile.txt", "A", "2"};
    PickManyQuotes.main(args);

    String output = outstream.toString();

    assertThat(output, containsString("All arguments except first should be integers"));
  }

  @Test
  public void validQuoteNumbersGiven() throws Exception {
    final String quote = "What a test!;Anonymous";
    final String reformattedQuote = "`What a test!' by Anonymous";

    String input = String.join("\n",
        quote + " Line 1", quote + " Line 2", quote + " Line 3");

    BufferedReader in = new BufferedReader(new StringReader(input));

    List<Quote> quotes = PickManyQuotes.loadQuotes(in);

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    PickManyQuotes.reformat(quotes, new int[]{99});

    String output = outstream.toString();

    assertThat(output, containsString(" is not a valid line number (too high)"));

    ByteArrayOutputStream outstream2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream2));

    PickManyQuotes.reformat(quotes, new int[]{-22});

    String output2 = outstream2.toString();

    assertThat(output2, containsString(" is not a valid line number (too low)"));


  }

}
