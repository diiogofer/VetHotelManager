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

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {
    //FIXME implement command
    List<Employee> list = _receiver.getAllEmployees();
    for(Employee e : list) {_display.addLine(e);}
  }
}
