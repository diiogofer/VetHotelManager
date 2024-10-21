package hva.core;

public enum VaccineDamageCategory {
	NORMAL("NORMAL"),
	CONFUSION("CONFUSÃO"),
  ACCIDENT("ACIDENTE"),
  ERROR("ERRO");
  private String _label;

  VaccineDamageCategory(String label) {
      _label = label;
  }
  public String toString() {
      return _label;
  }
}
