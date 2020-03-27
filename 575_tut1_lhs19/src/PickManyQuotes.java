import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickManyQuotes {

  /* Takes as input command line arguments. The first argument should be the filename for a file
  containing quotes. The next arguments should be integers relating to the index of the quotes
  to be printed. The corresponding quotes are reformatted and printed.*/
  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("Please enter two or more arguments");
      return;
    }

    int[] choices = new int[args.length - 1];
    for (int i = 1; i < args.length; i++) {
      try {
        choices[i - 1] = Integer.parseInt(args[i]);
      } catch (Exception e) {
        System.out.println("All arguments except first should be integers: " + e);
      }
    }

    String filename = args[0];
    File file = new File(filename);
    FileReader reader = null;
    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException e) {
      System.out.println("Source file does not exist: " + e);
      return;
    }
    BufferedReader buffer = new BufferedReader(reader);
    List<Quote> quotes = loadQuotes(buffer);
    buffer.close();

    reformat(quotes, choices);

  }

  /* Takes as input a list of Quote objects, and an array of integers denoting the quotes from
  the list to be printed. The selected formatted quotes are printed. */
  public static void reformat(List<Quote> quotes, int[] choices) {
    Arrays.sort(choices);
    for (int i : choices) {
      if (i < 1) {
        System.out.println(i + " is not a valid line number (too low)");
        return;
      }
      if (i > quotes.size()) {
        System.out.println(i + " is not a valid line number (too high)");
        return;
      }
      System.out.println(quotes.get(i - 1).toString() + "\n");
    }
  }

  /* Takes a buffered reader as input and outputs a list of Quote objects */
  public static List<Quote> loadQuotes(BufferedReader in) throws IOException {
    List<Quote> quotes = new ArrayList<Quote>();
    String line;
    while ((line = in.readLine()) != null) {
      quotes.add(new Quote(line));
    }
    return quotes;
  }

}
