package hva.app.habitat;

import hva.core.Hotel;
import hva.core.SpeciesAdequacy;
import hva.core.exception.UnknownHabitatException;
import hva.core.exception.UnknownSpeciesException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
    addStringField("speciesKey", hva.app.animal.Prompt.speciesKey());
    addOptionField("habitatInfluence", Prompt.habitatInfluence(), "POS", "NEG", "NEU");
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatKey = stringField("habitatKey");
    String speciesKey = stringField("speciesKey");
    String habitatInfluence = stringField("habitatInfluence");
    SpeciesAdequacy adequacy = SpeciesAdequacy.NEUTRAL; // why??????
    switch (habitatInfluence) {
      case "POS" -> adequacy = SpeciesAdequacy.POSITIVE;
      case "NEU" -> adequacy = SpeciesAdequacy.NEUTRAL;
      case "NEG" -> adequacy = SpeciesAdequacy.NEGATIVE;
    }
    try {
      _receiver.setHabitatSpeciesAdequacy(habitatKey, speciesKey, adequacy);
    } catch (UnknownHabitatException uhe) {
      throw new UnknownHabitatKeyException(habitatKey);
    } catch (UnknownSpeciesException uee) {
      throw new UnknownSpeciesKeyException(speciesKey);
    }
  }
}
