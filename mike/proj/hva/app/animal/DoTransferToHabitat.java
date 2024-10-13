package hva.app.animal;

import hva.core.Hotel;
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
    addStringField("animalId", Prompt.animalKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.changeAnimalHabitat(stringField("animalId"), 
                                    stringField("habitatId"));
    } catch (hva.core.exception.UnknownAnimalKeyException uake) {
      throw new UnknownAnimalKeyException(stringField("animalId"));
    } catch (hva.core.exception.UnknownHabitatKeyException uhke) {
      throw new UnknownHabitatKeyException(stringField("habitatId"));
    }
  }
}
