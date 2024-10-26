package hva.app.vaccine;

import java.util.List;

import hva.core.Hotel;
import hva.core.VaccineEvent;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
/**
 * Executes the command to show all vaccines applied by all veterinarians in the zoo hotel.
 */
  @Override
  protected final void execute() {
    List<VaccineEvent> list = _receiver.getAllVaccineEvents();
    for(VaccineEvent vaccineEvents : list) {
      _display.addLine(vaccineEvents);
    }
  }
}
