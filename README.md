# Java-Proj
# Requirements
PlantUML extension in VSCode to format .puml files properly

# PlantUML docs
https://plantuml.com/class-diagram

# View UML graph online
https://www.plantuml.com/plantuml/uml/</br>
Replace the UML code in the box with the code in the po.puml file.

# Compilar
javac -cp po-uilib.jar:. `find hva -name "*.java"`  

# Run
java -cp po-uilib.jar:. hva.app.App  

# Clear
find . -name \*.class -exec rm {} \;
