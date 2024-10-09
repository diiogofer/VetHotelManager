package hva.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import hva.app.exception.FileOpenFailedException;
import hva.core.Hotel;
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
  }

  @Override
  protected final void execute() throws CommandException {
    Hotel hotel = _receiver.getHotel();
    if(hotel == null || hotel.getHotelState() == false || Form.confirm(Prompt.saveBeforeExit()) == false){
      _receiver.createHotel();
      return ;
    }
    if(_receiver.getFileName() == null){
      try{
        String filename = Form.requestString(Prompt.newSaveAs());
        _receiver.saveAs(filename);
      } catch (MissingFileAssociationException | IOException ex){
        throw new FileOpenFailedException(ex);
      }
    }
    else {
      try{
        _receiver.save();
      } catch (MissingFileAssociationException | IOException ex) {
        throw new FileOpenFailedException(ex);
      }
    }
    _receiver.createHotel();   
  }
}
  

  //   if(hotel != null && hotel.getHotelState() && Form.confirm(stringField("state"))){
  //     if(_receiver.getFileName() == null){
  //       try{
  //         _receiver.saveAs(stringField("filename"));
  //       } catch (MissingFileAssociationException | IOException ex){
  //         throw new FileOpenFailedException(ex);
  //       }
  //     }
  //     else {
  //       try{
  //         _receiver.save();
  //       } catch (MissingFileAssociationException | IOException ex) {
  //         throw new FileOpenFailedException(ex);
  //       }
  //     }
  //   }
  //   _receiver.createHotel();
  // }
