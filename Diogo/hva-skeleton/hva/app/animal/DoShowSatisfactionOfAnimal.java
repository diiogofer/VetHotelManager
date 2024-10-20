package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalException;
import hva.core.exception.UnknownAnimalException;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("animalKey", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      int value = Math.round(_receiver.getAnimalSatisfaction(stringField("animalKey")));
      _display.addLine(value);
    } catch (UnknownAnimalException uae) {
      throw new UnknownAnimalKeyException(uae.getMessage());
    }
  }
}
