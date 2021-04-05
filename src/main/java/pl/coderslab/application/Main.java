package pl.coderslab.application;

import pl.coderslab.entity.DBUtils;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBUtils.createDB();
            App.run();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
