package hva.app.animal;

import java.util.List;
import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  /**
   * Constructs a new command to show all animals in the zoo hotel.
   * 
   * @param receiver the hotel from which animals will be retrieved
   */
  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  /**
   * Executes the command by retrieving all animals from the hotel and displaying them.
   * Each animal is displayed on a new line.
   */
  @Override
  protected final void execute() {
    List<Animal> list = _receiver.getAllAnimals();
    for(Animal animal : list) {
      _display.addLine(animal);
    }
  }
}
