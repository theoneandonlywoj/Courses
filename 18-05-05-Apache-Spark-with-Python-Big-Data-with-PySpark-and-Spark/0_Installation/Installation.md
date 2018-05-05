1) Install Java 8 and Git. You can check your Java version with java -version

2) Download Spark from:
http://it.apache.contactlab.it/spark/spark-2.2.0/spark-2.2.0-bin-hadoop2.7.tgz

3) Unpack the tgz file to a known location. For me it will be E:\ApacheSpark. The path cannot have spaces. Otherwise it will fail!

4) Download winutils.exe from:
https://github.com/jleetutorial/sparkTutorial/blob/winutils/winutils.exe

5) Move the file to E:\Hadoop\bin

6) Type Environment Variable in the Windows program list and go to Edit the system environment variable. -> Environment Variables

7) Add new system variables:
- HADOOP_HOME with value E:\Hadoop
- SPARK_HOME with value E:\ApacheSpark\spark-2.2.0-bin-hadoop2.7 

8) Click on PATH variable and Edit -> New and add %HADOOP_HOME%\bin and repeat with %SPARK_HOME%\bin

9) Close all the windows by clicking OK.

10) Create folder on the local disk (in this case E:) tmp and another one inside names hive

11) Open the command line and execute:
winutils chmod 777 E:\tmp\hive

12) If you wish, change spark log from info to error to clean up the output.
Go to E:\ApacheSpark\spark-2.2.0-bin-hadoop2.7\conf. Make a copy of log4j.properties.template and rename it to log4j.properties.
Open the file and go to line 19: "log4j.rootCategory=INFO, console" and change "INFO" to "ERROR".
The result should look like: "log4j.rootCategory=ERROR, console"
