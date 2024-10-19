package hva.core;

import java.util.Map;

public class Vaccine extends Identified{
  private String _name;
  private Map<String, Species> _speciesMap;
  Vaccine(String id, String name, Map<String, Species> map) {
    super(id);
    _name = name;
    _speciesMap = map;
  }
}
