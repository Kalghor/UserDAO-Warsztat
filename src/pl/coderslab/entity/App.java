package pl.coderslab.entity;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static final String[] MENU_OPTIONS = {"Add User", "Remove User", "Update User", "Show User", "Show all Users","Quit"};
    public static final String PATTERN_EMAIL = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}";
    public static final String PATTERN_PASSWORD = "(^[A-Z])[A-Za-z0-9]{3,15}";
    public static final String PATTERN_USERNAME = "[A-Za-z0-9_-]{3,16}";

    public static void run() {
        UserDao userDao = new UserDao();
        boolean primaryLoop = true;
        while (primaryLoop) {
            Scanner scan = new Scanner(System.in);
            showMenu();
            System.out.println("\n" + "Select an option: ");
            String userOption = scan.nextLine();
            switch (userOption.toLowerCase()) {
                case "1":
                case "add user": {
                    User user = createUser();
                    System.out.println("");
                    userDao.addUserToDB(user);
                    System.out.println("");
                    break;
                }
                case "2":
                case "remove user": {
                    System.out.println("Enter ID of the user you want to delete: ");
                    while (!scan.hasNextInt()){
                        scan.next();
                    }
                    int id = scan.nextInt();
                    System.out.println("");
                    userDao.delete(id);
                    System.out.println("");
                    break;
                }
                case "3":
                case "update user": {
                    System.out.println("Enter ID of the user you want to update: ");
                    while (!scan.hasNextInt()){
                        scan.next();
                    }
                    int id = scan.nextInt();
                    User user = createUser();
                    user.setId(id);
                    System.out.println("");
                    userDao.update(user);
                    System.out.println("");
                    break;
                }
                case "4":
                case "show user": {
                    System.out.println("Enter ID of the user you want to show: ");
                    while (!scan.hasNextInt()){
                        scan.next();
                    }
                    int id = scan.nextInt();
                    System.out.println("");
                    userDao.read(id);
                    System.out.println("");
                    break;
                }
                case "5":
                case "show all users": {
                    System.out.println("");
                    userDao.ShowAllUsers(userDao.findAll());
                    System.out.println("");
                    break;
                }
                case "6":
                case "quit": {
                    primaryLoop = false;
                    break;
                }
                default:{
                    System.out.println("Select correct option.\n");
                    break;
                }
            }
        }
    }

    private static void showMenu() {
        for (int i = 0; i < MENU_OPTIONS.length; i++) {
            System.out.println(ConsoleColors.RED_BRIGHT + (i + 1) + ". " + MENU_OPTIONS[i]);
        }
    }

    private static boolean validateUserInput(String regex, String userInput) {
        boolean result = false;
        Pattern compiledPattern = Pattern.compile(regex);
        Matcher matcher = compiledPattern.matcher(userInput);
        if (matcher.matches()) {
            result = true;
        }
        return result;
    }

    private static User createUser(){
        Scanner scan = new Scanner(System.in);
        String userName = "";
        String userEmail = "";
        String userPassword = "";
        while (true) {
            System.out.println("Enter username (Username can contain the following characters: a - z, digits, dash, underscore, with the number of characters from 3 to 16): ");
            userName = scan.nextLine();
            if(validateUserInput(PATTERN_USERNAME,userName)){
                break;
            } else {
                System.out.println("You entered wrong username! Try again");
            }
        }
        while (true) {
            System.out.println("Enter email: ");
            userEmail = scan.nextLine();
            if(validateUserInput(PATTERN_EMAIL,userEmail)){
                break;
            } else {
                System.out.println("You entered incorrect email! Try again");
            }
        }
        while (true) {
            System.out.println("Enter password (password should start with a capital letter, be between 6 and 16 characters long, and contain at least one number): ");
            userPassword = scan.nextLine();
            if(validateUserInput(PATTERN_PASSWORD,userPassword)){
                break;
            } else {
                System.out.println("You entered wrong password! Try again");
            }
        }
        User user = new User(userName,userEmail,userPassword);
        return user;
    }
}