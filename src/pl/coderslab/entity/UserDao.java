package pl.coderslab.entity;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";

    public UserDao() {
    }

    public User addUserToDB(User user) {

        //User userTmp = user;
        try (Connection connection = DBUtils.getConnection("workshop2");
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, hashPassword(user.getPassword()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User read(int userID) {
        User user = new User();
        try (Connection connection = DBUtils.getConnection("workshop2")) {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String index = resultSet.getString("id");
                for (int i = 0; i <= Integer.parseInt(index); i++) {
                    if (index.equals(String.valueOf(userID))) {
                        user.setId(Integer.parseInt(resultSet.getString("id")));
                        user.setUserName(resultSet.getString("username"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        return user;
                    } else {
                        System.out.println("Rekord nie istnieje w bazie");
                        break;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Boolean isExist(User user) throws SQLException {
        boolean result = false;
        try(Connection connection = DBUtils.getConnection("workshop2")){
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                if(user.getEmail().equals(resultSet.getString("email"))){
                    result = true;
                }
            }
        }
        return result;
    }

    public UserDao(String userName, String email, String password) {
        super(userName, email, password);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
