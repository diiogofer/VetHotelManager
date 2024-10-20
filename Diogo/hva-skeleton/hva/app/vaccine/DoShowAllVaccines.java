package hva.app.vaccine;

import java.util.*;
import hva.core.Hotel;
import hva.core.Vaccine;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Vaccine> vaccineList = _receiver.getAllVaccines();
    for (Vaccine vaccine: vaccineList) {
      _display.addLine(vaccine);
    } 
  }
}
