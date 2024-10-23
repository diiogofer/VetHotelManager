package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeKey = stringField("employeeKey");
    try {
     long result = Math.round(_receiver.getEmployeeSatisfaction(employeeKey));
      _display.addLine(result);
    } catch (UnknownEmployeeException uee) {
      throw new UnknownEmployeeKeyException(employeeKey);
    }
  }
}
