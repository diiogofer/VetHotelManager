package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeException;
import hva.core.exception.UnknownResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
    addStringField("responsibilityKey", Prompt.responsibilityKey());
  }
  
/**
 * Executes the command to add a new responsibility to an employee.
 * 
 * @throws UnknownEmployeeKeyException if the specified employee key does not exist.
 * @throws NoResponsibilityException if the specified responsibility key does not exist.
 */
  @Override
  protected void execute() throws CommandException {
    String employeeKey = stringField("employeeKey");
    String responsibilityKey = stringField("responsibilityKey");
    try {
      _receiver.addEmployeeResponsibility(employeeKey, responsibilityKey);
    } catch (UnknownEmployeeException uee) {
      throw new UnknownEmployeeKeyException(employeeKey);
    } catch (UnknownResponsibilityException ure) {
      throw new NoResponsibilityException(employeeKey, responsibilityKey);
    }
  }
}
