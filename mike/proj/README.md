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
