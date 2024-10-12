package hva.app.main;

import hva.core.HotelManager;
import java.io.IOException;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  
  /**
   * Constructs a new command to open a file with zoo hotel data.
   * 
   * @param receiver the HotelManager that will handle the file loading
   */
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  /**
   * Executes the command to open a file.
   * If the current hotel has unsaved changes, the user is prompted to save them before proceeding.
   * Then, the new hotel data is loaded from the specified file.
   * 
   * @throws CommandException if an error occurs while saving or loading the file
   */
  protected final void execute() throws CommandException {
    try {
      if (_receiver.getHotel().getHotelState() && Form.confirm(Prompt.saveBeforeExit())) {
        if (_receiver.getFileName() == null) {
          String filename = Form.requestString(Prompt.newSaveAs());
          _receiver.saveAs(filename);
        } else {
          _receiver.save();
        }
      }
      
      String filename = Form.requestString(Prompt.openFile());
      _receiver.load(filename);
    } catch (MissingFileAssociationException | IOException | UnavailableFileException ex) {
      throw new FileOpenFailedException(ex);
    }
  }
}