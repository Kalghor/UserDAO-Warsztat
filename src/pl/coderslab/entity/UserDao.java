package pl.coderslab.entity;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class UserDao extends User {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
    private static final String allFromUsers = "SELECT * FROM users";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public UserDao() {
    }

    public User addUserToDB(User user) {
        try {
            if(isExist(user)){
                System.out.println("Uzytkownik juz znajduje sie w bazie.");
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj nazwe kolumny: ");
        String column = scan.next();
        System.out.println("Podaj wartosc do wpisania: ");
        String value = scan.next();
        try(Connection connection = DBUtils.getConnection("workshop2")){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);


//            preparedStatement.setString(1,column);
            preparedStatement.setString(1,value);
            preparedStatement.setInt(2,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int userId) {
        Scanner scan = new Scanner(System.in);
        int index = scan.nextInt();
        try (Connection connection = DBUtils.getConnection("workshop2")){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1,index);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Boolean isExist(User user) throws SQLException {
        boolean result = false;
        try(Connection connection = DBUtils.getConnection("workshop2")){
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(allFromUsers);
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
