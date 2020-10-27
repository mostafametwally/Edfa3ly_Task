# Edfa3ly_Task
Deliverables
1-	Test Cases: 
There are four scenarios each scenario includes one or more test case with details in a separate spreadsheet for each test case theses files are included in the project in “/ZQA_Edfaaly.com/TestScenarios” folder. 
1- Adding automated item Scenario.xls which include fife test case
2- Adding prohibited item Scenario.xlsx which include one test case 
3- Editing automated item scenario.xlsx
4- Editing non automated Item Scenario.xlsx

2-	Automation Test Script
•	The automation code simulates the automated and prohibited products, by using POM, page factory design pattern, extent report, data driven test approach and TestNG framework, written with java OOP language. 
•	This script can be run on many browsers but it’s configured on chrome browser.
•	Extent report is added to the project which includes more details about the test cases that already run and screenshots for the failed test cases.
•	The project is uploaded on GitHub
Project architecture
With page object mode design pattern there are six packages. 
1-	Base package which include one class called the Base Class
2-	Config package which include Config.properties file
3-	Pages package which include ShoppingCartPage.java class
4-	TestData package which include TestData.xls file 
5-	Utilities package which include ReadExcelFile.java class
6-	Test package which include 2 test classes (AutomatedProductsTest.java, ProhibitedProductsTest.java) these test classes contains all test cases.
How to run
Prerequisites: 
1-	jdk should be installed
2-	Eclipse IDE 
3-	TestNG framework
You can clone the project and run it as TestNG with one of two ways 
•	From TestNG.xml file and run it as a suit
•	Individually from the test classes ran as TestNG.
