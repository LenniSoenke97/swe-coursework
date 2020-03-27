package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CalculatorController implements ActionListener {

  private final CalculatorModel model;

  public CalculatorController(CalculatorModel model) {
    this.model = model;
  }

  public void actionPerformed(ActionEvent actionEvent) {
    JButton source = (JButton) actionEvent.getSource();
    String value = source.getText();
    model.evalValue(value);
  }
}
