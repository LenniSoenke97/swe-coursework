package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CalculatorModelTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  public Updatable view = context.mock(Updatable.class);

  @Test
  public void canAddNumbersToStack() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("9");
    model.evalValue("3");
    assertThat(model.numStack.size(), is(2));
  }

  @Test
  public void canPerformAddOperation() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("9");
    model.evalValue("3");
    model.evalValue("+");
    assertThat(model.numStack.size(), is(1));
    assertThat(model.numStack.get(0), is(12.0));
  }

  @Test
  public void canPerformTimesOperation() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("9");
    model.evalValue("3");
    model.evalValue("*");
    assertThat(model.numStack.size(), is(1));
    assertThat(model.numStack.get(0), is(27.0));
  }

  @Test
  public void canPerformMinusOperation() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("9");
    model.evalValue("3");
    model.evalValue("-");
    assertThat(model.numStack.size(), is(1));
    assertThat(model.numStack.get(0), is(6.0));
  }

  @Test
  public void canPerformDivideOperation() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("9");
    model.evalValue("3");
    model.evalValue("/");
    assertThat(model.numStack.size(), is(1));
    assertThat(model.numStack.get(0), is(3.0));
  }

  @Test
  public void mostRecentNumsUsedForCalc() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("5");
    model.evalValue("9");
    model.evalValue("3");
    model.evalValue("+");
    assertThat(model.numStack.size(), is(2));
    assertThat(model.numStack.get(1), is(12.0));
  }

  @Test
  public void callsUpdateMethodIfOperationPerformed() {
    CalculatorModel model = new CalculatorModel();
    context.checking(new Expectations() {{
      allowing(view).displayError("");
      oneOf(view).update(model);
    }});
    model.addObserver(view);
    model.evalValue("9");
    context.assertIsSatisfied();
  }

  @Test
  public void displaysErrorIfTooFewIntsOnStack() {
    CalculatorModel model = new CalculatorModel();
    context.checking(new Expectations() {{
      allowing(view).update(model);
      allowing(view).displayError("");
      oneOf(view).displayError("Minimum two integers must be entered before performing operation");
    }});
    model.addObserver(view);
    model.evalValue("9");
    model.evalValue("*");
    context.assertIsSatisfied();
  }

  @Test
  public void displaysErrorIfIncorrectValuePassed() {
    CalculatorModel model = new CalculatorModel();
    context.checking(new Expectations() {{
      allowing(view).update(model);
      allowing(view).displayError("");
      oneOf(view).displayError("The value provided must be a mathematical operator or number");
    }});
    model.addObserver(view);
    model.evalValue("F");
    context.assertIsSatisfied();
  }

  @Test
  public void doesNotAppendIfIncorrectValueIsPassed() {
    CalculatorModel model = new CalculatorModel();
    model.evalValue("F");
    assertThat(model.numStack.size(), is(0));
  }
}
