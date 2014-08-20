echo "[+] Setuping stegtool...";
echo "[+] Compiling all source files...";
javac -d . *.java
echo "[+] Generating manifest file and adding contents...";
echo "Manifest-Version: 1.0\nSpecification-Title: \"StegTool\"\nCreated-By: 2.0 (0xC0d3r,J0rd3n,Anil)\nMain-Class: stegtool.gui.MainFrame" > MyManifest.txt
echo "[+] Creating .jar file...";
jar cfm St3gT00l-v1.0.jar MyManifest.txt stegtool/
chmod a+x St3gT00l-v1.0.jar
echo "[+] St3gT00l-v1.0.jar has created successfully...";
