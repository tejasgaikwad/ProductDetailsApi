# ProductDetailsApi

Softwares Required:
1. Jdk 1.8
2. apache maven 
3. eclipse

Instructions :

1. Clone the project to local disk using git. 
2. Import the project to eclipse to see the code.
3. To build the project, execute mvn clean install. Building the app will create fat jar to run from cmd.
4. To run the project, click run as java application rom eclipse or execute java -jar ProductDetails-0.0.1-SNAPSHOT.jar from cmd.


Source code details:
Application's main class is com.springboot.productDetails.ProductDetailsApp.java
Static data(Color code + RGB values) placed in ProductDetails.properties under resources folder.
Error handling done using @ErrorHandler in spring framework.
Test cases are under src/test/java folder.


