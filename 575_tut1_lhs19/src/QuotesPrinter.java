import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuotesPrinter {

  static final String quotesFile = "Quotes.txt";

  /* Reformats and prints each quote held in the file Quotes.txt */
  public static void main(String[] args) throws Exception {
    File file = new File(quotesFile);
    FileReader reader = null;

    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException e) {
      System.out.println("Source file does not exist: " + e);
      return;
    }

    BufferedReader buffer = new BufferedReader(reader);
    reformat(buffer);
    buffer.close();
  }

  /* takes as input a buffered reader. Then each line is reformatted and printed */
  public static void reformat(BufferedReader input) throws IOException {
    String line;
    while ((line = input.readLine()) != null) {
      String[] subLines = line.split(";");
      for (String s : subLines) {
        System.out.println(s);
      }
    }
  }
}
