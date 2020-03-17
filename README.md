## MINI ATM SIMULATOR v1.0
This is the simple application which simulate how the ATM system works.

### Requirements
* JDK 1.8

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
Type command: `java -jar atm.jar` on your Terminal. And then __*Voila!*__

#### Dummy User
You can use this account below to try this simulation.

| Name         | Account Number | PIN    | Balance |
| ------------ | -------------- | ------ | ------- |
| John Doe     | 112233         | 012108 | $100    |
| Jane Doe     | 112244         | 932012 | $30     |