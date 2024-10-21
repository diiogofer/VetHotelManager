package hva.app.search;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatException;

import java.util.List;

import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    String habitatKey = stringField("habitatKey");
    try {
      List<Animal> list = _receiver.getAnimalsFromHabitat(habitatKey);
      for(Animal a : list) {
        _display.addLine(a);
      }
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(habitatKey);
    }
  }
}
