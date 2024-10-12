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
  
  /**
   * Constructs a new command to create a new zoo hotel.
   * 
   * @param receiver the HotelManager that will handle the creation of the new hotel
   */
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  /**
   * Executes the command to create a new zoo hotel. 
   * If the current hotel has unsaved changes, the user is prompted to save them before proceeding.
   * 
   * @throws CommandException if an error occurs while saving the hotel
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
