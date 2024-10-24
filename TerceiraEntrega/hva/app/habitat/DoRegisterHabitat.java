package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.DuplicateHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
    addStringField("habitatName", Prompt.habitatName());
    addIntegerField("habitatArea", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.registerHabitat(stringField("habitatKey"),
                                stringField("habitatName"),
                                integerField("habitatArea"));
    } catch (hva.core.exception.DuplicateHabitatException dhe) {
      throw new DuplicateHabitatKeyException(stringField("habitatKey"));
    }
  }
}
