package ic.doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CalculatorModel {
  private final List<Updatable> views = new ArrayList<Updatable>();
  public final List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
  public List<Double> numStack = new ArrayList<>();

  public void addObserver(Updatable observer) {
    views.add(observer);
  }

  private void notifyObservers() {
    for (Updatable view : views) {
      view.update(this);
    }
  }

  private void displayError(String s) {
    for (Updatable view : views) {
      view.displayError(s);
    }
  }

  public void evalValue(String value) {
    displayError("");

    if (operators.contains(value)) {
      applyOperator(value);
    } else {
      addToStack(value);
    }

    notifyObservers();
  }

  private void applyOperator(String operator) {
    if (numStack.size() < 2) {
      displayError("Minimum two integers must be entered before performing operation");
    } else {
      Double num1 = numStack.remove(numStack.size() - 1);
      Double num2 = numStack.remove(numStack.size() - 1);
      Double num3 = 0.0;
      if (operator == "*") {
        num3 = num2 * num1;
      } else if (operator == "/") {
        num3 = num2 / num1;
      } else if (operator == "+") {
        num3 = num2 + num1;
      } else if (operator == "-") {
        num3 = num2 - num1;
      }
      numStack.add(num3);
    }
  }

  private void addToStack(String value) {
    try {
      double dbl = Double.parseDouble(value);
      numStack.add(dbl);
    } catch (Exception e) {
      displayError("The value provided must be a mathematical operator or number");
    }
  }


}
