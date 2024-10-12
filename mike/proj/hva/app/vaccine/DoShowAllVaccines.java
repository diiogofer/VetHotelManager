package hva.app.vaccine;

import java.util.List;

import hva.core.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  /**
   * Constructs a new command to show all vaccines in the zoo hotel.
   * 
   * @param receiver the hotel from which vaccines will be retrieved
   */
  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  /**
   * Executes the command by retrieving all vaccines from the hotel and displaying them.
   * Each vaccine is displayed on a new line.
   */
  @Override
  protected final void execute() {
    List<Vaccine> list = _receiver.getAllVaccines();
    for(Vaccine vaccine : list) {
      _display.addLine(vaccine);
    }
  }
}
