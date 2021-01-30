package pl.coderslab.entity;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
    private static final String allFromUsers = "SELECT * FROM users";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public UserDao() {
    }

    public User addUserToDB(User user) {

            if(isExist(user)){
                System.out.println("Uzytkownik juz znajduje sie w bazie.");
                try (Connection connection = DBUtils.getConnection("workshop2");
                     PreparedStatement preparedStatement = connection.prepareStatement(allFromUsers,PreparedStatement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.executeQuery();
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if(rs.next()){
                        user.setId(rs.getInt(1));
                    }
                    return user;
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else {
                try (Connection connection = DBUtils.getConnection("workshop2");
                     PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY,PreparedStatement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, user.getUserName());
                    preparedStatement.setString(2, user.getEmail());
                    preparedStatement.setString(3, hashPassword(user.getPassword()));
                    preparedStatement.executeUpdate();
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if(rs.next()){
                        user.setId(rs.getInt(1));
                    }
                    return user;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        return null;
    }

    public User read(int userID) {
        User user = new User();
        try (Connection connection = DBUtils.getConnection("workshop2")) {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(allFromUsers);
            while (resultSet.next()) {
                String index = resultSet.getString("id");
                for (int i = 0; i <= Integer.parseInt(index); i++) {
                    if (index.equals(String.valueOf(userID))) {
                        user.setId(Integer.parseInt(index));
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

    public void update(User user){
        if(!isExist(user)) {
            int id = user.getId();
            String username = user.getUserName();
            String email = user.getEmail();
            String password = user.getPassword();
            try (Connection connection = DBUtils.getConnection("workshop2")) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, hashPassword(password));
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Taki uzytwnik juz istnieje w bazie");
        }
    }

    public void delete(int userId) {
        if(isExist(userId)) {
            try (Connection connection = DBUtils.getConnection("workshop2")) {
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Taki rekord nie istnieje w bazie!");
        }
    }

    public User[] findAll() {
        User user = new User();
        User[] usersArr = new User[0];
        try (Connection connection = DBUtils.getConnection("workshop2")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(allFromUsers);
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                usersArr = addToArray(user,usersArr);
                user = new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersArr;
    }

    private User[] addToArray(User user, User[] users){
        User[] usersTmp = Arrays.copyOf(users, users.length+1);
        usersTmp[usersTmp.length-1] = user;
        return usersTmp;
    }

    public Boolean isExist(User user)  {
        boolean result = false;
        try(Connection connection = DBUtils.getConnection("workshop2")){
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(allFromUsers);
            while (resultSet.next()){
                if(user.getEmail().equals(resultSet.getString("email"))){
                    result = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Boolean isExist(int id)  {
        boolean result = false;
        try(Connection connection = DBUtils.getConnection("workshop2")){
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(allFromUsers);
            while (resultSet.next()){
                if(id == (resultSet.getInt("id"))){
                    result = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
