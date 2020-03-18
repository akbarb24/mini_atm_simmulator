## MINI ATM SIMULATOR v1.0
This is the simple application which simulate how the ATM system works.

### Requirements
* JDK 1.8

#### Data Source File
Prepare the CSV file for storing the Account Data by following format below:

The delimiter is `;`
```csv
    account_name;pin;balance;account_number
```

Or you can find the example file inside Project Directory.

### Installation
1. Clone the repository [here](https://github.com/akbarb24/mini_atm_simulator.git).
2. Go to the project's directory: `mini_atm_simulator`
3. Compile all the __*java__ files by running this command below:
   ```bash
   javac -d bin src\com\mitrais\atm\*.java src\com\mitrais\atm\model\*.java src\com\mitrais\atm\repository\*.java src\com\mitrais\atm\screen\*.java src\com\mitrais\atm\service\*.java src\com\mitrais\atm\validation\*.java
   ``` 
4. Create __manifest__ file by running: `echo Main-Class: com.mitrais.atm.Main > MANIFEST.mf`
5. Create __jar__ file by running: `jar cfvm atm.jar MANIFEST.mf -C bin/ .`

### Run the Application
Type command: `java -jar atm.jar <data_source_filepath>` on your Terminal. 

*Example*
```bash
    java -jar atm.jar D:\Users\blabla\data.csv
```
And __*Voila!*__