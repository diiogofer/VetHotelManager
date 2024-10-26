package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.NotAVetException;
import hva.core.exception.NotAllowedToVaccinateException;
import hva.core.exception.UnknownAnimalException;
import hva.core.exception.UnknownEmployeeException;
import hva.core.exception.UnknownVaccineException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineKey", Prompt.vaccineKey());
    addStringField("vetKey", Prompt.veterinarianKey());
    addStringField("animalKey", hva.app.animal.Prompt.animalKey());
  }

/**
 * Executes the command to vaccinate a given animal by a given veterinarian with a specified vaccine.
 *
 * @throws UnknownVeterinarianKeyException if the specified veterinarian key does not exist or the employee is not a veterinarian.
 * @throws VeterinarianNotAuthorizedException if the veterinarian is not authorized to vaccinate the animal.
 * @throws UnknownAnimalKeyException if the specified animal key does not exist.
 * @throws UnknownVaccineKeyException if the specified vaccine key does not exist.
 */
  @Override
  protected final void execute() throws CommandException {
    String vaccineKey = stringField("vaccineKey");
    String vetKey = stringField("vetKey");
    String animalKey = stringField("animalKey");
    try{
      boolean goodVaccine = _receiver.vaccinateAnimal(animalKey, vetKey, vaccineKey);
      if(!goodVaccine) _display.addLine(Message.wrongVaccine(vaccineKey, animalKey));
    } catch (UnknownEmployeeException | NotAVetException ex) {
      throw new UnknownVeterinarianKeyException(vetKey);
    } catch (NotAllowedToVaccinateException nae) {
      throw new VeterinarianNotAuthorizedException(vetKey, nae.getMessage());
    } catch (UnknownAnimalException uae) {
      throw new UnknownAnimalKeyException(animalKey);
    } catch (UnknownVaccineException uve) {
      throw new UnknownVaccineKeyException(vaccineKey);
    }
  }
}
