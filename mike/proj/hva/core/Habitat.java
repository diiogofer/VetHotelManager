package hva.core;

public class Habitat extends Identified{
  private String _name;
  private int _area;
  Habitat(String id, String name, int area) {
    super(id);
    _name = name;
    _area = area;
  }
}
