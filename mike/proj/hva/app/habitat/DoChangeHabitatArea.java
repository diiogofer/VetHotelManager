package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.InvalidInputException;
import hva.core.exception.UnknownHabitatException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
    addIntegerField("area", Prompt.habitatArea());
  }
  
/**
 * Executes the command to change the area of a given habitat in the zoo hotel.
 * 
 * @throws UnknownHabitatKeyException if the specified habitat key does not exist.
 */
  @Override
  protected void execute() throws CommandException, UnknownHabitatKeyException {
    String habitatKey = stringField("habitatKey");
    int area = integerField("area");
    try {
      _receiver.changeHabitatArea(habitatKey, area);
    } catch (InvalidInputException iie) {
      System.out.println(iie.getMessage());
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(habitatKey);
    }
  }
}
