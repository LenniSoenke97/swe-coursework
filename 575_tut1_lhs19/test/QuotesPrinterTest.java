import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.Test;

public class QuotesPrinterTest {

  static final String text = "What a test!";
  static final String comment = "Sebastian Uchitel (1971- )";

  // This file contains unit tests for QuotesPrinter. We have only put one test here.
  // You may want to add some more...

  @Test
  public void reformatsSingleLine() {

    String input = text + ";" + comment;
    String expectedOutput = text + "\n" + comment;

    ByteArrayOutputStream outstream = replaceSystemOutStreamForTesting();

    try {
      QuotesPrinter.reformat(readerFor(input));
    } catch (IOException e) {
      fail("An exception occurred when processing single line input:" + e.getMessage());
    }

    String actualOutput = outstream.toString();

    String errorMsg =
      String.format(
        "For \n %s \n the expected output should contain \n %s \n but the output was \n %s",
        input, expectedOutput, actualOutput);

    assertThat(errorMsg, actualOutput, containsString(expectedOutput));
  }

  private BufferedReader readerFor(String input) {
    return new BufferedReader(new StringReader(input));
  }

  private ByteArrayOutputStream replaceSystemOutStreamForTesting() {
    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outstream));
    return outstream;
  }
}
