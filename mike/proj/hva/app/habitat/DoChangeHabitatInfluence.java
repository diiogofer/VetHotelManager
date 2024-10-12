package hva.app.habitat;

import hva.core.SpeciesAdequacy;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("speciesId", hva.app.animal.Prompt.speciesKey());
    addOptionField("influence", Prompt.habitatInfluence(), "POS", "NEG", "NEU");
  }
  
  @Override
  protected void execute() throws CommandException {
    SpeciesAdequacy adequacy;
    switch (optionField("influence")) {
      case "POS" -> adequacy = SpeciesAdequacy.POSITIVE;
      case "NEG" -> adequacy = SpeciesAdequacy.NEGATIVE;
      case "NEU" -> adequacy = SpeciesAdequacy.NEUTRAL;
      default -> throw new IllegalArgumentException("Adequacy input not valid");
    }
    try{
      _receiver.setHabitatSpeciesAdequacy(stringField("habitatId"), 
                                          stringField("speciesId"),
                                          adequacy);
    } catch ( hva.core.exception.UnknownHabitatKeyException |
              hva.core.exception.UnknownSpeciesKeyException ex) {
      //EXCEPTION HANDLING
    }
  }
}
