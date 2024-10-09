package hva.app.main;

import java.io.IOException;
import hva.app.exception.FileOpenFailedException;
import hva.core.Hotel;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
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

  @Override
protected final void execute() throws CommandException {
    Hotel hotel = _receiver.getHotel();
    
    // If no hotel exists or hotel hasn't changed or saving is not confirmed, create a new hotel and return.
    if (hotel == null || !hotel.getHotelState() || !Form.confirm(Prompt.saveBeforeExit())) {
        _receiver.createHotel();
        return;
    }
    try {
        // Save with or without an existing filename
        if (_receiver.getFileName() == null) {
            String filename = Form.requestString(Prompt.newSaveAs());
            _receiver.saveAs(filename);
        } else {
            _receiver.save();
        }
    } catch (MissingFileAssociationException | IOException ex) {
        throw new FileOpenFailedException(ex);
    }

    _receiver.createHotel();  // Create a new hotel after saving
}
}
