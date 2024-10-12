package hva.app.animal;

import hva.core.Hotel;
import hva.app.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {
  
  /**
   * Constructs a new command to register a new animal in the zoo hotel.
   * Prompts the user for the animal's ID, name, species ID, and habitat ID.
   * 
   * @param receiver the hotel where the animal will be registered
   */
  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("animalId", Prompt.animalKey());
    addStringField("animalName", Prompt.animalName());
    addStringField("speciesId", Prompt.speciesKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  /**
   * Executes the command to register a new animal.
   * If the species does not exist, the user is prompted to register the species before proceeding.
   * 
   * @throws CommandException if an error occurs during the registration process
   */
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerAnimal(
        stringField("animalId"), 
        stringField("animalName"), 
        stringField("speciesId"), 
        stringField("habitatId")
      );
    } catch (hva.core.exception.DuplicateAnimalKeyException ex) {
        throw new DuplicateAnimalKeyException(stringField("animalId"));
    } catch (hva.core.exception.UnknownHabitatKeyException ex) {
        throw new UnknownHabitatKeyException(stringField("habitatId"));
    } catch (hva.core.exception.UnknownSpeciesKeyException ex) {
      try {
        _receiver.registerSpecies(
          stringField("speciesId"), 
          Form.requestString(Prompt.speciesName())
        );
        execute();
      } catch (hva.core.exception.DuplicateSpeciesKeyException | hva.core.exception.DuplicateSpeciesNameException ex2) {
          throw new DuplicateAnimalKeyException(stringField("speciesId"));
      }
    }
  }
}
