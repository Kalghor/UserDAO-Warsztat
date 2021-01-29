import pl.coderslab.entity.DBUtils;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.SQLException;

public class DaoApp {
    public static void main(String[] args) {
        try {
            DBUtils.createDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        User tomek = new User("tomek", "tomek@wp.pl","qwerty");
        UserDao tomekDAO = new UserDao();
        tomekDAO.addUserToDB(tomek);

        User marcin = new User("marcin", "marcin@interia.pl","asddfgg");
        UserDao Dao2 = new UserDao();
        Dao2.addUserToDB(marcin);

        User karol = new User("karol", "karol@interia.pl","zxcvbn");
        User Slawek = new User("Slawek", "Slawek@inters gitia.pl","ghjkl;'");
        User Adam = new User("Adam", "Adam@interia.pl","nxzcvhjgasd");
        User Mariusz = new User("Mariusz", "Mariusz@interia.pl","uytfdgcvbhjkiou8y7gh");
        User Krystian = new User("Krystian", "Krystian@interia.pl","123456uyjthgbf");
        Dao2.addUserToDB(karol);
        Dao2.addUserToDB(Slawek);
        Dao2.addUserToDB(Adam);
        Dao2.addUserToDB(Mariusz);
        Dao2.addUserToDB(Krystian);

        UserDao userdao1 = new UserDao();
        User test = userdao1.read(2);
        String result = test.toString();
        System.out.println(result);
        int id = tomek.getId();
        System.out.println(id);
//        userdao1.update(tomek);
        userdao1.delete(1);


    }
}
