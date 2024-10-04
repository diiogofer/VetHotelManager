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

# Make runtests_both.sh executable on linux
chmod +x runtests_both.sh  

# bash
alias po-proj='cd ~/Documents/Java-Proj/mike/proj'  

alias po-clean='find ~/Documents/Java-Proj/mike/proj -name \*.class -exec rm {} \;'  

alias po-run='java -cp ~/Documents/Java-Proj/mike/proj/po-uilib.jar:. hva.app.App'  

alias po-compile='javac -cp ~/Documents/Java-Proj/mike/proj/po-uilib.jar `find ~/Documents/Java-Proj/mike/proj/hva -name *.java`'  

alias po-test='~/Documents/Java-Proj/mike/proj/runtests_both.sh'  

