package com.papasmurfie.rent_a_car_oop2.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "nikolaykalchev";
    private static final String PASSWORD = "1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }


}
