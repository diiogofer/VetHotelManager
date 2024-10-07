package hva.app.vaccine;

import java.util.List;

import hva.core.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() {
    List<Vaccine> list = _receiver.getAllVaccines();
    for(Vaccine v : list) {_display.addLine(v);}
  }
}
