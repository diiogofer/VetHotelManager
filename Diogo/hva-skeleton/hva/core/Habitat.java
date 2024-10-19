package hva.core;

public class Habitat extends Identified {
    private String _name;
    private int _area;

    Habitat(String habitatId, String habitatName, int habitatArea) { 
        super(habitatId);
        _name = habitatName;
        _area = habitatArea;
    }
}
