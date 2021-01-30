import pl.coderslab.entity.DBUtils;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.SQLException;
import java.util.Arrays;

public class DaoApp {
    public static void main(String[] args) {
        try {
            DBUtils.createDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        /*Testowanie addUser*/
        UserDao Dao2 = new UserDao();
        User tomek = new User("tomek", "tomek@wp.pl", "qwerty");
        User marcin = new User("marcin", "marcin@interia.pl", "asddfgg");
        User karol = new User("karol", "karol@interia.pl", "zxcvbn");
        User Slawek = new User("Slawek", "Slawek@inters gitia.pl", "ghjkl;'");
        User Adam = new User("Adam", "Adam@interia.pl", "nxzcvhjgasd");
        User Mariusz = new User("Mariusz", "Mariusz@interia.pl", "uytfdgcvbhjkiou8y7gh");
        User Krystian = new User("Krystian", "Krystian@interia.pl", "123456uyjthgbf");
        tomek = Dao2.addUserToDB(tomek);
        marcin = Dao2.addUserToDB(marcin);
        karol = Dao2.addUserToDB(karol);
        Slawek = Dao2.addUserToDB(Slawek);
        Adam = Dao2.addUserToDB(Adam);
        Mariusz = Dao2.addUserToDB(Mariusz);
        Krystian = Dao2.addUserToDB(Krystian);
//        /*----------------------------------------------*/
        /*Testowanie read*/
//        UserDao userdao1 = new UserDao();
//        User test = userdao1.read(3);
        /*----------------------------------------------*/
        /*Testowanie update*/
//        UserDao userDao = new UserDao();
//        User readTest = userDao.read(1);
//        tomek.setUserName("konrad");
//        tomek.setEmail("konrad@o2.pl");
//        userDao.update(tomek);
//        readTest = userDao.read(1);
//        /*----------------------------------------------*/
        /*Testowanie delete*/
//        UserDao userDao = new UserDao();
//        userDao.delete(1);
        /*----------------------------------------------*/
        /*Testowanie findAll*/
//        UserDao userDao = new UserDao();
//        User[] usersArr = new User[0];
//        usersArr = userDao.findAll();
//        for (int i = 0; i < usersArr.length; i++) {
//            usersArr[i].toString();
//        }
    }
}

