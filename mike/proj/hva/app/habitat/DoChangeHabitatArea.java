package hva.app.habitat;

import hva.core.Hotel;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addIntegerField("newArea", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.setHabitatArea(stringField("habitatId"), integerField("newArea"));
    } catch (hva.core.exception.UnknownHabitatKeyException ex) {
      //EXCEPTION HANDLING - 
      //ERROR HANDLING
    }
  }
}
