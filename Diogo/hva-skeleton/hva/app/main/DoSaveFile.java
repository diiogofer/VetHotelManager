package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    addStringField("newSaveAs", Prompt.newSaveAs());
  }

  @Override
  protected final void execute() {
    try {
      if(_receiver.getFileName() == null) {
        _receiver.saveAs(stringField("newSaveAs"));  
      }
      else _receiver.saveAs(_receiver.getFileName());
    } catch (MissingFileAssociationException | IOException ex) {
    }
  }
}
