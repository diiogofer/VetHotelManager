package hva.app.search;

import java.util.List;

import hva.core.Hotel;
import hva.core.VaccineEvent;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

/**
 * Executes the command to show all vaccines applied to animals belonging to an invalid species.
 */
  @Override
  protected void execute() throws CommandException {
      List<VaccineEvent> list = _receiver.getBadVaccineEvents();
      for(VaccineEvent vaccineEvents : list) {
        _display.addLine(vaccineEvents);
      }
  }
}
