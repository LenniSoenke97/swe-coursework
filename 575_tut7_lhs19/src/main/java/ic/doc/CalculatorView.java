package ic.doc;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

class CalculatorView implements Updatable {
  private List<JButton> numberButtons = new ArrayList<>();
  public final List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
  private List<JButton> operatorButtons = new ArrayList<>();
  private final JTextField textField = new JTextField(15);
  private final JLabel errorLabel = new JLabel();

  public CalculatorView(ActionListener controller) {
    JFrame frame = new JFrame("Reverse Polish Calculator");
    frame.setSize(800, 800);


    JPanel panel = new JPanel();

    for (int num = 0; num < 10; num++) {
      JButton btn = new JButton(String.valueOf(num));
      btn.addActionListener(controller);
      numberButtons.add(btn);
      panel.add(btn);
    }
    for (String ops : operators) {
      JButton btn = new JButton(ops);
      btn.addActionListener(controller);
      operatorButtons.add(btn);
      panel.add(btn);
    }

    textField.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(textField);
    panel.add(errorLabel);

    frame.getContentPane().add(panel);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void update(CalculatorModel model) {
    Double value = model.numStack.get(model.numStack.size() - 1);
    String stringToDisplay = String.valueOf(value);
    textField.setText(stringToDisplay);
  }

  public void displayError(String s) {
    errorLabel.setText(s);
  }

}
