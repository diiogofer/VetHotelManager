package hva.app.search;

import hva.core.Hotel;
import hva.core.VaccineEvent;
import hva.core.exception.UnknownAnimalException;

import java.util.List;

import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("animalKey", hva.app.animal.Prompt.animalKey());
  }

/**
 * Executes the command to show all medical acts (vaccine events) applied to a given animal.
 *
 * @throws UnknownAnimalKeyException if the specified animal key does not exist.
 */
  @Override
  protected void execute() throws CommandException {
    String animalKey = stringField("animalKey");
    try {
      List<VaccineEvent> list = _receiver.getVaccinesFromAnimal(animalKey);
      for(VaccineEvent vaccineEvents : list) {
        _display.addLine(vaccineEvents);
      }
    } catch (UnknownAnimalException uae) {
      throw new UnknownAnimalKeyException(animalKey);
    }
  }
}
