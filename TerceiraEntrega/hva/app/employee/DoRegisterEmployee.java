package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeException;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
    addStringField("employeeName", Prompt.employeeName());
    addOptionField("employeeType", Prompt.employeeType(), "VET", "TRT");
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeKey = stringField("employeeKey");
    String employeeName = stringField("employeeName");
    try {
      switch (stringField("employeeType")) {
        case "VET" -> _receiver.registerVet(employeeKey, employeeName);
        case "TRT" -> _receiver.registerKeeper(employeeKey, employeeName);
      }
    } catch (DuplicateEmployeeException dee) {
      throw new DuplicateEmployeeKeyException(dee.getMessage());
    }
  }
}
