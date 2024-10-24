package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import java.util.List;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Animal> list = _receiver.getAllAnimals();
    for(Animal a : list)
      _display.addLine(a);
  }
}
