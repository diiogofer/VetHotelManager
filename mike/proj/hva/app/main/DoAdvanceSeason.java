package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
  }

/**
 * Executes the command to advance the season in the system.
 */
  @Override
  protected final void execute() {
    _display.addLine(_receiver.advanceSeason());
  }
}
