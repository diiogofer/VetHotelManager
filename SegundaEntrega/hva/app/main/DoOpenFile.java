package hva.app.main;

import hva.core.HotelManager;

import java.io.FileNotFoundException;
import java.io.IOException;

import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    
    // if(_receiver.getHotelState()) { // state = true -> quer guardar?  
    //   addBooleanField("state" , Prompt.saveBeforeExit());
    //     // sim && _filename == null -> Form SaveAs
    //     if(booleanField("state") && _receiver.getFileName() == null){
    //       addStringField("saveAs", Prompt.newSaveAs());
    //     }
    // }
    // // ficheiro a abrir
    // addStringField("filename", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    // 1 -> Lógica de guardar antes de dar load
    // if(_receiver.getHotelState() && booleanField("state")) { //se estiver modificado e quiser guardar
    //   if(_receiver.getFileName() == null){ //se não tiver ficheiro associado -> save as
    //     try{
    //     _receiver.saveAs(stringField("saveAs"));
    //     } catch (MissingFileAssociationException | IOException ex){
    //       throw new FileOpenFailedException(ex);
    //     }
    //   }
    //   else{ // ficheiro não associado -> save normal
    //     try {
    //       _receiver.save();
    //     } catch (MissingFileAssociationException | IOException ex){
    //       throw new FileOpenFailedException(ex);
    //     }
    //   }
    // }
    // // 2 -> dar load
    // try {
    //   _receiver.load(stringField("filename"));
    //   _receiver.setFileName(stringField("filename")); // associação do ficheiro
    // } catch (UnavailableFileException ufe){
    // throw new FileOpenFailedException(ufe);
    // }
  }
}
// perguntar ao prof se a lógica está certa ou se fui cozinhado~
// perguntar ao prof se o boolean field no contrutor está necessariamente inicializado quando é usado.
// (não estourou no que testei mas quero confirmar)