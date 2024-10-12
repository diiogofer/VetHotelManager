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
  
  /**
   * Constructs a new command to save the current hotel to a file.
   * 
   * @param receiver the HotelManager that will handle saving the hotel
   */
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  /**
   * Executes the command to save the hotel. 
   * If the hotel has no associated file, the user is prompted to provide a file name.
   * Otherwise, the hotel is saved to the currently associated file.
   * 
   * @throws FileOpenFailedException if an error occurs while saving the hotel
   */
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
