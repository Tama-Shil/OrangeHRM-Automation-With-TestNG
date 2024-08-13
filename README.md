# OrangeHRM-Automation-With-TestNG


## Technology used:
- Java
- Intellij idea
- Allure

## Framework used:
  - TestNG

## Problem scenario 
1. Login as a admin to _https://opensource-demo.orangehrmlive.com/_
2. Go to PIM menu and create a new employee. Save the employee firstname, lastname, employeeid, username and password into JSONArray file. Generate random password which meets following criteria:   For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers. Assert if employee is created successfully.

3. Now go to the dashboard again and search by the employee id to check if the employee is found
4. Now go to the Directory menu and search by employee name and check if the employee is found and logout the session
5. Now login with the newly created employee creds
6. Assert your full name is showing besides the profile icon.
7. Go to my info
8. Scroll down and select Gender and Blood Type as O+ and save it. Then logout the user.

## How to Run the Project
1. Clone this project
2. Open cmd in the root folder.
3. Give the following command:  _````gradle clean test````_

## To generate Allure Report:
1. Open cmd in the root folder.
2. Give the following commands:
   
  *  _````allure generate allure-results --clean -o allure-report````_
  *  _````allure serve allure-results````_
   
  
## Test Cases 
https://docs.google.com/spreadsheets/d/10BaWzOoYkqDq5e1Z49k3lap4QoYeoSuMWx6Is19qhM0/edit?usp=sharing
## Allure Report
![image](https://github.com/user-attachments/assets/115f9e8b-341f-468e-8da6-c29eb39ba150)

![image](https://github.com/user-attachments/assets/25c120c3-1333-4712-b5cc-fdfc4ea2b581)


## Demo Video


https://github.com/user-attachments/assets/d443edcc-cae6-45c4-9d0f-e4737de32080




