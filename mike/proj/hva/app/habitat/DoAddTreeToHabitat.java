package hva.app.habitat;

import hva.core.Hotel;
import hva.core.TreeType;
import hva.core.exception.UnknownTreeTypeException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("treeId", Prompt.treeKey());
    addStringField("treeName", Prompt.treeName());
    addIntegerField("treeAge", Prompt.treeAge());
    addIntegerField("treeDifficulty", Prompt.treeDifficulty());
    addOptionField("treeType", Prompt.treeType(), "PERENE", "CADUCA");
  }
  
  @Override
  protected void execute() throws CommandException {
    TreeType treeType;
    switch (optionField("treeType")) {
      case "PERENE" -> treeType = TreeType.PERENE;
      case "CADUCA" -> treeType = TreeType.CADUCA;
      default -> throw new IllegalArgumentException("Tree type input not valid");
    }
    try {
      _receiver.registerTree(stringField("habitatId"), stringField("treeId"),
      stringField("treeName"), integerField("treeAge"), integerField("treeDifficulty"),
      treeType);
    } catch (hva.core.exception.DuplicateTreeKeyException dtke) {
      throw new DuplicateTreeKeyException(stringField("treeId"));
    } catch (hva.core.exception.UnknownHabitatKeyException uhke){
      throw new UnknownHabitatKeyException(stringField("habitatId"));
    } catch (UnknownTreeTypeException utte) {
      throw new IllegalArgumentException("Tree input not valid");
    }
  }
}
