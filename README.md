# UserDAO-Warsztat

Application for managing a table in a database. The application implements the DAO pattern. Communication with the database via MySQL.

## Description of the action

When navigating through the menu, you can use both numeric and text values.
1. Add User - Adds user to the database
2. Remove User - Removes user from the database
3. Update User - Updates user data in the database
4. Show User - Display a single user with the given id
5. Show all Users - It displays all users in the database
6. Save data in csv - Saves all data from the database to a .csv file in the imposed location (main application directory) under the name "Data.csv"
7. Load data from csv - Reads all data from the .csv file (to the database) in the imposed location (main application directory) under the name "Data.csv"
8. Quit - Exits the program

### Libraries used

Password hashing performed using the BCrypt library
http://www.mindrot.org/projects/jBCrypt

Interface coloring done with ConsoleColors
https://stackoverflow.com/a/45444716
