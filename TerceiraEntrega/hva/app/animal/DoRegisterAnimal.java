package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.*;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("animalKey", Prompt.animalKey());
    addStringField("animalName", Prompt.animalName());
    addStringField("speciesKey", Prompt.speciesKey());
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
  }
  
  /**
   * Executes the command to register a new animal. 
   * Attempts to register the animal in the hotel. If the species is unknown, it prompts the user to register a new species.
   * 
   * @throws DuplicateAnimalKeyException if an animal with the given key already exists.
   * @throws UnknownHabitatKeyException if the habitat key does not exist.
   * @throws UnknownSpeciesException if the species key does not exist, prompting user to input a species name.
   */
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerAnimal( stringField("animalKey"), 
                                stringField("animalName"), 
                                stringField("speciesKey"), 
                                stringField("habitatKey"));
    } catch (DuplicateAnimalException dae) {
      throw new DuplicateAnimalKeyException(dae.getMessage());
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(uhe.getMessage());
    } catch (UnknownSpeciesException use) {
      try {
        _receiver.registerSpecies(stringField("speciesKey"), 
                                  Form.requestString(Prompt.speciesName()));
				execute();
      } catch (InvalidInputException iie) {
        System.out.println(iie.getMessage());
      }
    }
  }
}
