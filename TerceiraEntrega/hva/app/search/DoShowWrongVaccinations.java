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

  @Override
  protected void execute() throws CommandException {
      List<VaccineEvent> list = _receiver.getBadVaccineEvents();
      for(VaccineEvent ve : list) {
        _display.addLine(ve);
      }
  }
}
