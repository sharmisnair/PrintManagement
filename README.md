# PrintManagement
PrintManagement is a Java app used by systems administrator of a printing management software company to calculate the printing costs.

## Problem description

Print Management app helps the system administrator by calculating the print costs. A school using the software has the following costing structure for printing:
* Paper size A4, job type single-sided:
** 15 cents per black and white page
** 25 cents per colour page
* Paper size A4, job type double-sided:
** 10 cents per black and white page
** 20 cents per colour page

The input to the app is a CSV file with a header and each row representing a printing job as follows:
```
Total Pages, Color Pages, Double Sided
25, 10, false
```

The app should output details of each print job and calculated cost to the console and also output the total cost of all jobs to the console.
Support for othre paper sizes will be added in the future.

## Assumptions

* The program is not optimised for huge filesizes and takes input as file path from the local computer
* The program is optimised for time complexity and hence look up times to get the configured cost given printing job details is kept efficient as possible
* The program currently supports School as a client but can be extended to different clients easily in the future. Each client will implement their own costing logic as there may be special discounts or offers for some clients.

## How to run the app

### Pre-requisites

* Maven 3.1+
* Java 11+

### Step by step instructions

Run the below commands from terminal after cloning this repository:
1. mvn package
2. Run the below command to execute the program with the file path as input: 
```java -cp target/PrintManagement-1.0-jar-with-dependencies.jar com.print.Main src/main/resources/samplePrintJobs.csv```

One argument to com.print.Main program:
* Path to CSV file with print jobs for the school client (src/main/resources/samplePrintJobs.csv in this example)
By default, if no input is given, [the file stored in resources](src/main/resources/samplePrintJobs.csv) is automatically picked up by the program

## Solution

### Design Overview

##### UML Diagram of Classes and relationships
![UML Diagram of core classes](https://github.com/sharmisnair/PrintManagement/blob/master/src/main/java/PrintManagementUML.png)

##### Core classes

* **PrinterAdminProgram** class is the entry point and core program that handles administrative function (cost calculation in this instance). It takes a *PrintClient* for which the costing is done as input and also a *FileProcessor* to be used to parse the input file.
* **PrintClient** is a generic interface for all printing clients for the  company. 
  * **PrintSchoolClient** is a concrete implementation of **PrintClient** and represents the school client for which printing cost is to be calculated. This class: 
    * stores the cost mapping inputs for this client
    * given a list of print jobs, calculates the cost of printing
* **FileProcessor** is a generic interface for processing a file with printing jobs as input
  * **CsvProcessor** is a concrete implementation of **FileProcessor** and specifically parses CSV File of format described in the problem and converts the records into List of Printing Jobs that is consumable by the program.

##### Other classes 
* **Logger** is a generic interface for printing output
  * **ConsoleLogger** is a concrete implementation of **Logger** and handles printing output or errors to the console
* **PrintJob** represents a specific printing job record and contains:
  * **PrintPageSize** enum for page size type with value A4 for now and can be extended to other sizes
  * **PrintInkType** enum of values BLACK_AND_WHITE and COLOR
  * **PrintJobType** enum for job types single sided or double sided

##### Key design decisions
* Using dependency injection to **PrinterAdminProgram** passing in any implementation of **PrintClient**, **Logger** and **FileProcessor**
  * This design will make it easily extensible when a new client other than the school is integrated. For example, a new *PrintCorporateClient* that implements PrintClient and can be passed into the same **PrinterAdminProgram** to get the same outcome for the new client. Main class can take in an additional parameter to choose which client the program is being run for and can send the relevant object into PrinterAdminProgram.
  * This design will make it easily extensible if the file format for input printing jobs gets changed from CSV to say Json as well. A new *JsonProcessor* to implement FileProcessor and can be passed into the same **PrinterAdminProgram**.
* Each client will store and handle its own cost calculation logic. 
  * **PrintSchoolClient** currently stores the given cost structure for printing and handles the cost calculation given a list of printing jobs. When a different client *PrintCorporateClient* is added, it would handle the same - the reason behind this way of designing was to ensure that each client had their own business logic for cost implementation. For example, if CorporateClient had discounts for 100 black and white pages printing, it can be a logic within PrintCorporateClient. 
  * If there are more than one clients with similar logic, an easier abstraction would be to create a separate interface **PrintingCalculation** and have **SimplePrintingCalculation** implement it with simple cost calculation and **DiscountedPrintingCalculation** for special discount logic if needed. Different clients may implement one or the other PrintingCalculation type. Given the current requirement is only for school implementation, this level of complexity was not introduced in the program and the aim was to keep it simple. When additional clients are added, this may be the approach to go with.

