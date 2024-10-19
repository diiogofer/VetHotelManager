package hva.app.main;

import hva.app.exception.FileOpenFailedException;
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
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    try{
      if(_receiver.getFileName() == null) { // sem ficheiro associado 
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
      }
      else _receiver.save(); // Com ficheiro Associado
    } catch (MissingFileAssociationException | IOException ex) {
      throw new FileOpenFailedException(ex);
    }
  }
}
