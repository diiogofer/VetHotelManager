package hva.app.vaccine;

import java.util.*;
import hva.core.Hotel;
import hva.core.Vaccine;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
/**
 * Executes the command to show all registered vaccines.
 */
  @Override
  protected final void execute() {
    List<Vaccine> list = _receiver.getAllVaccines();
    for(Vaccine vaccine : list) {
      _display.addLine(vaccine);
    }
  }
}
