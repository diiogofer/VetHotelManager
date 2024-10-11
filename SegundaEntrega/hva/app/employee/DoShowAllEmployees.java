package hva.app.employee;

import hva.core.Hotel;
import hva.core.Employee;
import pt.tecnico.uilib.menus.Command;
import java.util.List;
//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  /**
   * Constructs a new command to show all employees in the zoo hotel.
   * 
   * @param receiver the hotel from which employees will be retrieved
   */
  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  /**
   * Executes the command by retrieving all employees from the hotel and displaying them.
   * Each employee is displayed on a new line.
   */
  @Override
  protected void execute() {
    List<Employee> list = _receiver.getAllEmployees();
    for(Employee employee : list) {
      _display.addLine(employee);
    }
  }
}
