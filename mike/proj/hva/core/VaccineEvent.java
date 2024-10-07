package hva.core;

import java.io.Serializable;

public class VaccineEvent implements Serializable {
    private Animal _animal;
    private Veterinarian _vet;
    private VaccineEventDamage _damage;

    // private int calculateDamage() {}
    public VaccineEventDamage getDamage() {return _damage;}

}
