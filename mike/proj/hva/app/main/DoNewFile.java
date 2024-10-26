package hva.app.main;

import java.io.IOException;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.Hotel;
import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

/**
 * Executes the command to create a new zoo hotel.
 *
 * @throws FileOpenFailedException if saving the current hotel fails due to a missing file association or an IO error.
 */
  @Override
  protected final void execute() throws CommandException {
    Hotel hotel = _receiver.getHotel();
    
    if (hotel == null || !hotel.getHotelState() || !Form.confirm(Prompt.saveBeforeExit())) {
      _receiver.createHotel();
      return;
    }
    
    try {
      if (_receiver.getFileName() == null) {
        String filename = Form.requestString(Prompt.newSaveAs());
        _receiver.saveAs(filename);
      } else {
        _receiver.save();
      }
    } catch (MissingFileAssociationException | IOException ex) {
      throw new FileOpenFailedException(ex);
    }
    _receiver.createHotel();
  }
}
