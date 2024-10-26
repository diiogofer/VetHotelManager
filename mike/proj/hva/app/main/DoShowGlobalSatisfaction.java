package hva.app.main;

import hva.core.HotelManager;

import pt.tecnico.uilib.menus.Command;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
  }
  
/**
 * Executes the command to show the global satisfaction of the current zoo hotel.
 */
  @Override
  protected final void execute() {
    long result = Math.round(_receiver.calculateGlobalSatisfaction());
    _display.addLine(result);
  }
}
