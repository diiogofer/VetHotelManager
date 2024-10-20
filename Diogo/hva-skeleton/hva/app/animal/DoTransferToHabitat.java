package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalException;
import hva.core.exception.UnknownHabitatException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("animalKey", Prompt.animalKey());
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.changeAnimalHabitat(stringField("animalKey"), stringField("habitatKey"));
    } catch (UnknownAnimalException uae) {
      throw new UnknownAnimalKeyException(uae.getMessage());
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(uhe.getMessage());
    }
  }
}
