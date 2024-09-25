-tem que ser ver se hotel foi alterado
4.2 Animals

4.2.1 Visualize all animals
want: ANIMAL | idAnimal | nameAnimal | idSpecies | healthHistory | idHabitat


4.2.2 Register new animal
Prompt.animalID()
Prompt.animalName()
Prompt.speciesID()
Prompt.speciesName()

4.2.3 Transfer animal to another habitat
Prompt.animalID()
Promp.habitatID()


4.2.4 Calculate satisfaction of animal
Prompt.animalID()

4.3 Employee

4.3.1 Show all employees
want: type | id | name [| idResponsibilities]
type: VET or TRT
idResponsibilites:  VET => speciesID1, speciesID2, ...
                    TRT => habitatID1, habitatID2, ...

4.3.2 Register new employee
Prompt.employeeID() -> String
Prompt.employeeName() -> String
Prompt.employeeType() -> VET or TRT
4.3.3 Give employee new responsibility
Prompt.employeeID()

4.3.4 Remove responsibility from employee
Prompt.employeeID()



4.3.5 Calculate employee satisfaction
Prompt.employeeID()




