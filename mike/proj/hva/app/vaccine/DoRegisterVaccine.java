package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DuplicateVaccineException;
import hva.core.exception.UnknownSpeciesException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new vaccine.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("vaccineKey", Prompt.vaccineKey());
    addStringField("vaccineName", Prompt.vaccineName());
    addStringField("speciesKeys", Prompt.listOfSpeciesKeys());
  }

/**
 * Executes the command to register a new vaccine.
 * 
 * @throws DuplicateVaccineKeyException if a vaccine with the specified key already exists.
 * @throws UnknownSpeciesKeyException if one of the specified species keys does not exist.
 */
  @Override
  protected final void execute() throws CommandException {
    String vaccineKey = stringField("vaccineKey");
    String vaccineName = stringField("vaccineName");
    String speciesKeys = stringField("speciesKeys");
    String[] speciesKeyArray = speciesKeys.length() > 0 ? speciesKeys.split(",") : new String[0];
    try {
      _receiver.registerVaccine(vaccineKey, vaccineName, speciesKeyArray);
    } catch (DuplicateVaccineException dve) {
      throw new DuplicateVaccineKeyException(dve.getMessage());
    } catch (UnknownSpeciesException use) {
      throw new UnknownSpeciesKeyException(use.getMessage());
    }
  }
}
