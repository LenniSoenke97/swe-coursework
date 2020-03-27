package ic.doc;

public class CalculatorApp {

  public CalculatorApp() {
    CalculatorModel model = new CalculatorModel();
    CalculatorView view = new CalculatorView(new CalculatorController(model));
    model.addObserver(view);
  }

  public static void main(String[] args) {
    new CalculatorApp();
  }

}
