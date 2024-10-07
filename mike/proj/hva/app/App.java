package hva.app;

import hva.core.exception.*;
import pt.tecnico.uilib.Dialog;

public class App {

  public static void main(String[] args) {
    try (var ui = Dialog.UI) {
      var manager = new hva.core.HotelManager();
      String datafile = System.getProperty("import");
      if (datafile != null) {
        try {
          manager.importFile(datafile);
        } catch (ImportFileException e) {
          e.printStackTrace();
        }
      }
      (new hva.app.main.Menu(manager)).open();
    }
  }
}
