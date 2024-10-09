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

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    //FIXME add command fields
    addStringField("animalId", Prompt.animalKey());
    addStringField("animalName", Prompt.animalName());
    addStringField("speciesId", Prompt.speciesKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    try {
      _receiver.registerAnimal( stringField("animalId"), 
                                stringField("animalName"), 
                                stringField("speciesId"), 
                                stringField("habitatId"));
    } catch (hva.core.exception.DuplicateAnimalKeyException ex) {
      throw new hva.app.exception.DuplicateAnimalKeyException(stringField("animalId"));
    } catch (hva.core.exception.UnknownHabitatKeyException ex) {
      throw new hva.app.exception.UnknownHabitatKeyException(stringField("habitatId"));
    } catch (hva.core.exception.UnknownSpeciesKeyException ex) {
      try {
        _receiver.registerSpecies(stringField("speciesId"), Form.requestString(Prompt.speciesName()));
        execute();
      } catch (hva.core.exception.DuplicateSpeciesKeyException ex2) {
        //VER ISTO !!!!!!!!!!!!!!!!!!!!!!!!!
        throw new hva.app.exception.DuplicateAnimalKeyException(stringField("speciesId"));
      } catch (hva.core.exception.DuplicateSpeciesNameException ex2) {
        //VER ISTO !!!!!!!!!!!!!!!!!!!!!!!!!
        throw new hva.app.exception.DuplicateAnimalKeyException(stringField("speciesId"));
      }
    }
  }
}
