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
  
  @Override
  protected final void execute() {
    List<VaccineEvent> list = _receiver.getAllVaccineEvents();
    for(VaccineEvent ve : list) {
      _display.addLine(ve);
    }
  }
}
