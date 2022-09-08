# Provisio Hotels
CSD-460 Green Team Capstone Project Mirror Repository

Original Repository: https://github.com/Jacob-Breault/Provisio

## Application Functionality Screen Shots
README.md screen shot section under construction...


Provisio Landing Page with Slide Show -> |  Expanded Collapsable Nav Bar
:-------------------------:|:-------------------------:
![ScreenShot](/images/provisioHome.PNG)  |  ![ScreenShot](/images/provisioHome2.PNG)

<hr>

Reservation Booking Form ->|  Reservation Summary
:-------------------------:|:-------------------------:
![ScreenShot](/images/provisioBook.PNG)  |  ![ScreenShot](/images/provisioBook2.PNG)

<hr>

Reservation Confirmation ->                 |  Reservation Look Up Form ->               |  Information Retrieved from Database
:-------------------------:|:-------------------------:|:-------------------------:
![ScreenShot](/images/provisioConfirm.PNG)  |  ![ScreenShot](/images/provisioLookup.PNG) | ![ScreenShot](/images/provisioLookup2.PNG)

<hr>




## Setup and Compilation

### MacOS & Linux

#### Create MySQL User
```bash
$ mysql -u root -p
mysql> USE mysql;
mysql> CREATE USER 'user'@'localhost' IDENTIFIED BY 'root@1234';
mysql> GRANT ALL ON *.* TO 'user'@'localhost';
mysql> FLUSH PRIVILEGES;
```

#### Change Credentials In DBHelper.java (If Applicable)
```java
private final static String db_username = "user";
private final static String db_password = "root@1234";
```

#### Compile
```bash
$CATALINA_HOME/webapps/Provisio $ find . -name "*.java" > sources.txt
$CATALINA_HOME/webapps/Provisio $ javac -cp ".:$CATALINA_HOME/lib/servlet-api.jar" @sources.txt -d WEB-INF/classes
```

### Windows

#### Install Tomcat 10

1. https://tomcat.apache.org/download-10.cgi

2. Click on `zip` under the `Core` section. **NOT Windows Installer**

3. Unzip in Downloads in File Explorer

4. Open command prompt as administrator (*WARNING: This will run cmd as admin, use with caution*).

5. Move apache tomcat 10 onto your Destkop:
```batch
C:\Users\username> move Downloads\apache-tomcat-10.0.20\ Desktop\
```

6. Create a symbolic link to tomcat for easier use:
```batch
C:\Users\username> mklink /D C:\Users\username\Tomcat10 C:\Users\username\Desktop\apache-tomcat-10.0.20\apache-tomcat-10.0.20\
```

7. Continue to next steps

#### Install OpenJDK 11

1. https://docs.microsoft.com/en-us/java/openjdk/download

2. Scroll down to the OpenJDK 11 portion, and select `Windows x64 - zip`.

3. Unzip it in the Downloads with File Explorer

4. Move it to your Desktop and create a symbolic link for easier use (run cmd as administrator--see previous warning):
```batch
C:\Users\username> move Downloads\microsoft-jdk-11.0.14.1_1-31205-windows-x64 Desktop\
C:\Users\username> mklink /D C:\Users\username\Java11 "C:\Users\username\Desktop\microsoft-jdk-11.0.14.1_1-31205-windows-x64\jdk-11.0.14.1+1"
```

5. To create ease-of-use aliases for the `javac.exe` and `java.exe`, create a text file called `aliases.txt` on your Desktop

6. Open it in notepad and add the following:
```
@echo off
doskey java = %userprofile%\Desktop\microsoft-jdk-11.0.14.1_1-31205-windows-x64\jdk-11.0.14.1+1\bin\java $*
doskey javac = %userprofile%\Desktop\microsoft-jdk-11.0.14.1_1-31205-windows-x64\jdk-11.0.14.1+1\bin\javac $*
```

7. Rename the text file to a batch script file:
```batch
C:\Users\username\Desktop> rename aliases.txt aliases.bat
```

8. Although you can set it up to be persisent, whenever you want to use `java` and `javac` without knowing the file path, just run `aliases.bat`:
```batch
C:\Users\username\Desktop> aliases
```

9. Now, `java -version` and `javac -version` should work from anywhere in that cmd session.

10. Continue to next steps.

#### Setup Tomcat With OpenJDK 11

1. Set the JAVA_HOME and CATALINA_HOME:

```batch
C:\Users\username> setx JAVA_HOME "C:\Users\username\Java11"
C:\Users\username> setx CATALINA_HOME "C:\Users\username\Tomcat10"
```

**NOTE: Only command prompts opened after using `setx` will have those variables set**

Check to make sure it worked (after restarting command prompt):
```batch
C:\Users\username> echo %JAVA_HOME%
C:\Users\username\Java11
C:\Users\username> echo %CATALINA_HOME%
C:\Users\username\Tomcat10
```

2. Startup tomcat and visit it in the browser to make sure it is working:
```batch
C:\Users\username\Tomcat10\bin> startup
Using ...
...
```

**The equivalent for catalina.out is a prompt that opens itself once startup.bat is run successfully: that's where the exceptions get thrown for stdout.**

#### Setup Provisio in Tomcat 10

1. git clone the repo into `C:\Users\username\Tomcat10\webapps`.

2. To compile, first shutdown tomcat:
```batch
C:\Users\username\Tomcat10\webapps\Provisio> ..\..\bin\shutdown
```

Then, **in the provisio directory**, re-find all the java files and put them in sources.txt:
```batch
C:\Users\username\Tomcat10\webapps\Provisio> dir /s /b *.java > sources.txt
```

Then, compile with:
```batch
C:\Users\username\Tomcat10\webapps\Provisio> javac -cp "C:\Users\username\Tomcat10\lib\servlet-api.jar" @sources.txt -d WEB-INF\classes\
```

The `-cp` flag specifies the class path (we are including the built in tomcat servlet api jar).
The `-d` flag specifies for `javac` to handle the packages properly (e.g. billy.java with `package bob;` will be compiled into `WEB-INF\classes\bob\billy.class`).
The `@` tells `javac` to compile everything in the text file.

Finally, start tomcat back up:

```batch
C:\Users\username\Tomcat10\webapps\Provisio> ..\..\bin\startup
```

3. Visit `http://localhost:8080/Provisio` and our project should pull up just fine.

4. continue to the next steps.

#### Install MySQL Community

1. https://dev.mysql.com/downloads/installer/

2. Choose the `Windows (x86, 32-bit), MSI Installer` that is `2.3M` in size (not `435.7M`) (it is version `8.0.28`).

3. Go through the GUI installer selecting default stuff, with these exceptions:

The default for the close port was `-1`, make it `8081`.
Make the root password `root@1234`. 
Make sure that it also says it is going to install the MySQL Shell.

4. Once installed, open up MySQL Shell.

5. You will see something like:

```bash
...
Type '\help' or '\?' for help; '\quit' to exit.
MySQL JS> 
```

Connect to the DB with root user:
```bash
MySQL JS> \connect root@localhost
Creating session to 'root@localhost'
Please provide the password for 'root@localhost':
```

Provide `root@1234` as the password, then you will get:
```bash
MySQL localhost:33060+ ssl JS>
```

Switch to SQL:
```bash
MySQL localhost:33060+ ssl JS> \sql
Switching to SQL mode... Commands end with ;
MySQL localhost:33060+ ssl SQL>
```

Now, source (run the `db_config.sql`) the config file from Provisio:
```bash
MySQL localhost:33060+ ssl SQL> source C:\Users\username\Tomcat10\webapps\Provisio\WEB-INF\sql_scripts\db_config.sql
```

Then, test it:
```bash
MySQL localhost:33060+ ssl SQL> USE proviso;
MySQL localhost:33060+ ssl SQL> SELECT * FROM users;
```

If all is good, setup a new user called `user` with password `root@1234`:
```bash
MySQL localhost:33060+ ssl SQL> USE mysql;
MySQL localhost:33060+ ssl SQL> CREATE USER 'user'@'locahost' IDENTIFIED BY 'root@1234';
MySQL localhost:33060+ ssl SQL> GRANT ALL ON *.* TO 'user'@'localhost';
MySQL localhost:33060+ ssl SQL> FLUSH PRIVILEGES;
```

6. Make sure `WEB-INF/src/classes/DB/DBHelper.java` has the correct credentials:
```java
private final static String db_username = "user";
private final static String db_password = "root@1234";
```

7. Now, recompile and try to login to Provisio/login with `bobmcrib@gmail.com` -> `password1`:
```batch
C:\Users\username\Tomcat10\webapps\Provisio> ..\..\bin\shutdown
C:\Users\username\Tomcat10\webapps\Provisio> dir /s /b *.java > sources.txt
C:\Users\username\Tomcat10\webapps\Provisio> javac -cp "C:\Users\username\Tomcat10\lib\servlet-api.jar" @sources.txt -d WEB-INF\classes\
C:\Users\username\Tomcat10\webapps\Provisio> ..\..\bin\startup
```
