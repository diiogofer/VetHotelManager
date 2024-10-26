package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeException;
import hva.core.exception.UnknownResponsibilityException;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
    addStringField("responsibilityKey", Prompt.responsibilityKey());
  }
  
/**
 * Executes the command to remove a responsibility from a given employee in the zoo hotel.
 * 
 * @throws UnknownEmployeeKeyException if the specified employee key does not exist.
 * @throws NoResponsibilityException if the specified responsibility key does not exist for the employee.
 */
  @Override
  protected void execute() throws CommandException {
    String employeeKey = stringField("employeeKey");
    String responsibilityKey = stringField("responsibilityKey");
    try {
      _receiver.removeEmployeeResponsibility(employeeKey, responsibilityKey);
    } catch (UnknownEmployeeException uee) {
      throw new UnknownEmployeeKeyException(employeeKey);
    } catch (UnknownResponsibilityException ure) {
      throw new NoResponsibilityException(employeeKey, responsibilityKey);
    }
  }
}
