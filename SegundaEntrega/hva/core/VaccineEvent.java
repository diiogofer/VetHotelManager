package hva.core;

import java.io.Serializable;

/**
 * Represents a vaccine event, which records the vaccination of an animal by a veterinarian.
 * The event also tracks any potential damage caused by the vaccine.
 * 
 * <p>This class implements {@link Serializable}, allowing vaccine events to be serialized.</p>
 * 
 * <p>Each vaccine event stores references to the animal that received the vaccine, the veterinarian
 * who administered the vaccine, and the damage caused by the vaccine.</p>
 * 
 * @see Animal
 * @see Veterinarian
 * @see VaccineEventDamage
 */
public class VaccineEvent implements Serializable {
    
    /** The animal that received the vaccine. */
    private Animal _animal;

    /** The veterinarian who administered the vaccine. */
    private Veterinarian _vet;

    /** The damage caused by the vaccine. */
    private VaccineEventDamage _damage;

    /**
     * Returns the damage caused by the vaccine in this event.
     * 
     * @return the damage caused by the vaccine
     */
    VaccineEventDamage getDamage() {
        return _damage;
    }

    /**
     * Returns the animal that received the vaccine in this event.
     * 
     * @return the animal that was vaccinated
     */
    Animal getAnimal() {
        return _animal;
    }

    /**
     * Returns the veterinarian who administered the vaccine in this event.
     * 
     * @return the veterinarian who performed the vaccination
     */ 
    Veterinarian getVet() {
        return _vet;
    }
}
