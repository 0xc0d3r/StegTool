echo "[+] Setuping stegtool...";
echo "[+] Compiling all source files...";
javac -d . src/util/*.java
javac -d . src/security/*.java
javac -d . src/algo/*.java
javac -d . src/cmd/*.java
javac -d . src/gui/*.java
echo "[+] Generating manifest file and adding contents...";
echo "Manifest-Version: 1.0\nSpecification-Title: \"StegTool\"\nCreated-By: 2.0 (0xC0d3r,J0rd3n,Anil)\nMain-Class: stegtool.gui.MainFrame" > MyManifest.txt
echo "[+] Creating archive(.jar) file...";
jar cfm St3gT00l-v1.0.jar MyManifest.txt stegtool/
rm -r stegtool
rm MyManifest.txt
chmod a+x St3gT00l-v1.0.jar
echo "[+] St3gT00l-v1.0.jar has created successfully...";
