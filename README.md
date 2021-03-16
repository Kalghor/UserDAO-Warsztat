# UserDAO-WorkShop

Application for managing a table in a database. The application implements the DAO pattern. Communication with the database via MySQL.

## Description of the action

When navigating through the menu, you can use both numeric and text values.
1. Add User - To add user to the database
2. Remove User - To remove user from the database
3. Update User - To update user data in the database
4. Show User - To display a single user with the given id
5. Show all Users - To display all users in the database
6. Save data in csv - To save all data from the database to a .csv file in the imposed location (main application directory) under the name "Data.csv"
7. Load data from csv - To read all data from the .csv file (to the database) in the imposed location (main application directory) under the name "Data.csv"
8. Quit - Exits the program

### Libraries used

Password hashing performed using the BCrypt library
http://www.mindrot.org/projects/jBCrypt

Interface coloring done with ConsoleColors
https://stackoverflow.com/a/45444716
