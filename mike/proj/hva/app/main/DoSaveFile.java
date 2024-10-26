package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

/**
 * Executes the command to save the current hotel to a file.
 * 
 * @throws FileOpenFailedException if the file cannot be saved due to missing file association or IO errors.
 */
  @Override
  protected final void execute() throws FileOpenFailedException {
    try{
      if(_receiver.getFileName() == null) { // No file associated
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
      }
      else _receiver.save(); // File is already associated
    } catch (MissingFileAssociationException | IOException ex) {
      throw new FileOpenFailedException(ex);
    }
  }
}

