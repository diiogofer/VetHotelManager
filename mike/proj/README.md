## HOTEL
- code must acommodate for multiple instanciations of Hotel as a possibility
## HABITAT
## TREE
- add new Tree types easily with little core code impact
- pattern
- - increase readability of Tree code
- - add new events related to season without touching Tree code
- - add new functionalities related to season
### CADUCA
### PERENE
## ANIMAL
## SPECIES
## EMPLOYEE
- add new Employee types with small impact
- pattern
- - choose between satisfcation calculation algorithms at runtime
- - default algorithm at instanciation
### KEEPER
### VET
## VACCINE

# Habitat

## 4.4.3
#### TODO
- DoChangeArea
- - ? exception handling
- - ? area <= 0
- - ? habitat doesn't exist
- Habitat
- - setArea() 
- - area <= 0 handling
#### Checks
- h12 and H12 are the same id
- changes hotelChanged to true if value was changed 

## 4.4.4
#### TODO
- DoChangeHabitatInfluence
- - ? exception handling
- - ? no habitat
- - ? no species
- - ? default -> IllegalArgumentException
#### Checks
- input asked until NEU || POS || NEG
- case insensitive habitat and species
- uses Adequacy enum for values to change them easily
- changes hotelChanged to true if value was changed

## 4.4.5
#### TODO
- DoAddTreeToHabitat
- - ? IllegalArgumentException
#### Checks
- hotel and tree exists (case insensitive)
- uses TreeType enum for tree types
- changes hotelChanged to true if successful

## 4.4.6
#### TODO
#### Checks
- checks habitat exists
- case insensitive
- returns alfanumeric ordered unmodifiable list

# Animal

## 4.2.2
#### TODO
- ? hva.core.exception.DuplicateSpeciesKeyException | hva.core.exception.DuplicateSpeciesNameException
- ? what exception use in this cases
#### Checks
- case insensitive
- cant add animal with existing id
- cant add species with existing name or/and id
- habitat exists
- hotel state = true at species addition and animal addition