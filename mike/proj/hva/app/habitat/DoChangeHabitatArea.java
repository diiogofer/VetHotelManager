package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Habitat;

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
      Habitat habitat = _receiver.getHabitat(stringField("habitatId"));
      habitat.setArea(integerField("newArea"));
    } catch (hva.core.exception.UnknownFieldException ex) {
      //EXCEPTION HANDLING - 
      //ERROR HANDLING
    }
  }
}
