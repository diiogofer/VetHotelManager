# VetHotelManager

VetHotelManager is a project developed as part of the **Object-Oriented Programming** course in the second year of the **Computer Engineering degree** at the **Instituto Superior Técnico**.
It is a Java-based application designed to manage a veterinary hotel. It organizes habitats, animals, species, employees (caretakers and veterinarians), and vaccines, providing functionalities to track animal health, habitat conditions, employee satisfaction, etc.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Technical Details](#technical-details)
6. [Contributors](#contributors)

---

### Project Overview

VetHotelManager is developed for efficient management of a veterinary hotel environment. It supports interactions between various entities, such as animals, employees, habitats, and vaccines. Through an intuitive menu interface, users can register animals, assign habitats, manage employee responsibilities, and ensure animals receive appropriate healthcare.

---

### Features

- **Animal Management**: Register new animals, assign habitats, and monitor health.
- **Employee Management**: Register caretakers and veterinarians, assign responsibilities, and track satisfaction based on workload.
- **Vaccination Management**: Register vaccines, administer vaccines, and track animal health based on vaccine records.
- **Habitat Management**: Create and manage habitats, including trees and area calculations.
- **Satisfaction Calculation**: Calculate satisfaction levels for both animals and employees, based on various factors.
- **Persistence**: Save and load data through serialization for consistent state management.

---

### Installation

To install and run VetHotelManager:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/diiogofer/VetHotelManager.git
   cd VetHotelManager
   ```

2. **Compile the Project**:
   ```bash
   javac -cp .:po-uilib.jar $(find hva -name "*.java")
   ```

3. **Run the Application**:
   ```bash
   java -cp po-uilib.jar:. hva.app.App
   ```
   
> **Note:** Ensure you have Java 21 or higher installed.

---

### Usage

1. **Starting the Application**: After compiling, the application will open in a menu-driven command-line interface.
2. **Main Menu**:
   - `Create New`: Starts a new session with an empty dataset.
   - `Open`: Loads a previously saved session.
   - `Save`: Saves the current session state.
   - `Advance Season`: Advances the current season, affecting all trees and habitats.
   - `View Satisfaction`: Displays the overall satisfaction of all animals and employees.

3. **Animal Management**:
   - View all animals.
   - Register animals.
   - Transfer animals between habitats.
   - Calculate and display individual animal satisfaction.

4. **Employee Management**:
   - View all employees.
   - Register caretakers and veterinarians.
   - Assign or remove responsibilities.
   - Calculate and display individual employee satisfaction.

5. **Habitat Management**:
   - View all habitats.
   - Register new habitats.
   - Modify habitat area and suitability for specific species.
   - View details of trees within each habitat.

7. **Vaccination Management**:
   - View all Vaccines.
   - Register vaccines and assign them to specific species.
   - Administer vaccines to animals and track any adverse health impacts.
   - View all vaccine events.
  
8. **Consultation Menu**:
   - View details of animals assigned to a specific habitat.
   - View medical procedures performed on a selected animal.
   - View the vaccination history of a specific veterinarian.
   - Identify and list vaccinations that have caused health issues due to incorrect administration.

> Detailed commands for each feature are accessible through the interface.

---

### Technical Details

VetHotelManager is designed using an object-oriented approach, with a focus on modular and extensible code. Here are some key points:

- **Design Patterns**: The application uses design patterns like Strategy for satisfaction calculation and State for managing seasonal effects on trees, etc.
- **Serialization**: Java serialization (using `java.io.Serializable`) is used to save and load the current state of the application.
- **Error Handling**: Custom exceptions are implemented for specific error scenarios, ensuring a robust and user-friendly experience.
- **Modular Architecture**: The application follows a layered architecture, separating the core logic (in `hva.core`) from the user interface (in `hva.app`), enhancing code reusability.

---

### Contributors

- **[Diogo](https://github.com/diiogofer)**
- **[Mike](https://github.com/updatemike)**

---
