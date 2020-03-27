import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.Test;

public class PickYourQuoteTest {

  @Test
  public void exampleText() throws Exception {

    String input =
      String.join(
        "\n",
        "What a test!;Anonymous",
        "Another test!;Anonymous",
        "The last test!;Anonymous");

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    PickYourQuote.reformat(2, new BufferedReader(new StringReader(input)));

    String output = outstream.toString();
    assertThat(
      "Selected line 2 but got a different line.",
      output,
      containsString("Another test!" + "\n" + "Anonymous"));
  }

  @Test
  public void overstepText() throws Exception {
    String input =
      String.join(
        "\n",
        "What a test!;Anonymous",
        "Another test!;Anonymous",
        "The last test!;Anonymous");

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    PickYourQuote.reformat(4, new BufferedReader(new StringReader(input)));

    String output = outstream.toString();
    assertThat(
      "Line 4 does not exist, thus function should not return a quote",
      output,
      containsString("Quote not found."));
  }

  @Test
  public void incorrectFileName() throws Exception {
    String[] args = {"Quotes_False.txt", "2"};

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    PickYourQuote.main(args);

    String output = outstream.toString();
    assertThat(
      "Given file should not exist, thus function should not execute.",
      output,
      containsString("Source file does not exist:"));
  }

  @Test
  public void incorrectLineNumber() throws Exception {
    String input =
      String.join("\n",
        "What a test!;Anonymous",
        "Another test!;Anonymous",
        "The last test!;Anonymous");

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    PickYourQuote.reformat(-1, new BufferedReader(new StringReader(input)));

    String output = outstream.toString();
    assertThat(
      "Line Numbers must be larger or equal to one.",
      output,
      containsString("Please enter positive number"));
  }

  @Test
  public void wrongNumberArguments() throws Exception {
    String input =
      String.join(
        "\n",
        "What a test!;Anonymous",
        "Another test!;Anonymous",
        "The last test!;Anonymous");

    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));

    String[] args = {};
    PickYourQuote.main(args);

    String output = outstream.toString();
    assertThat(
      "Exactly two arguments seem to be entered.",
      output,
      containsString("Please enter two arguments"));
  }
}
