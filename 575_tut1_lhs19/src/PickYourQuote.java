import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PickYourQuote {

  /* takes as input to arguments, the filename of a file containing quotes and the number
  corresponding the quote number to be reformatted and printed */
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Please enter two arguments");
      return;
    }

    String quotesFile = args[0];
    int lineNum = Integer.parseInt(args[1]);

    File file = new File(quotesFile);
    FileReader reader = null;
    try {
      reader = new FileReader(file);
    } catch (FileNotFoundException e) {
      System.out.println("Source file does not exist: " + e);
      return;
    }

    BufferedReader buffer = new BufferedReader(reader);
    reformat(lineNum, buffer);
    buffer.close();
  }

  /* Reformats and prints line lineNum from buffered reader input */
  public static void reformat(int lineNum, BufferedReader input) throws IOException {
    if (lineNum < 1) {
      System.out.println("Please enter positive number");
      return;
    }

    String line;
    int i = 1;
    while ((line = input.readLine()) != null) {
      if (i == lineNum) {
        String[] subLines = line.split(";");
        for (String s : subLines) {
          System.out.println(s);
        }
        return;
      }
      i++;
    }
    System.out.println("Quote not found.");
  }

}
