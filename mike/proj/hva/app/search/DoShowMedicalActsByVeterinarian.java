package hva.app.search;

import hva.core.Hotel;
import hva.core.VaccineEvent;
import hva.core.exception.NotAVetException;

import java.util.List;

import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("vetKey", hva.app.employee.Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeKey = stringField("vetKey");
    try {
      List<VaccineEvent> list = _receiver.getVaccineEventsOfVet(employeeKey);
      for(VaccineEvent ve : list) {
        _display.addLine(ve);
      }
    } catch (NotAVetException nave) {
      throw new UnknownVeterinarianKeyException(employeeKey);
    }
  }
}
