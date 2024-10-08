package hva.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
    if(receiver.getHotelState()) {
      addBooleanField("state" , Prompt.saveBeforeExit());
    }
  }

  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getHotelState() && booleanField("state")) {
      String filename = Form.requestString(Prompt.newSaveAs());
      try{
      _receiver.saveAs(filename);
      } catch(MissingFileAssociationException | IOException ex){
        throw new FileOpenFailedException(ex); //não é a excepção ideal
      }
    }

    _receiver.setFileName(null); // erro de privacidade setFileName Publico?
    _receiver.createHotel();
    // posso fazer createHotel e lá dentro meter filename = null
    // posso literalmente criar outro hotelmanager e mandar este passear (perguntar ao prof) se sim como fazer se _receiver é final
    // perguntar ainda se preciso se fazer a cena do "Guardar Como" e "guardar normal"
  }
}
