package hva.app.employee;

import java.util.List;
import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;


/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {
    List<Employee> list = _receiver.getAllEmployees();
    for(Employee employee : list) {
      _display.addLine(employee);
    }
  }
}
