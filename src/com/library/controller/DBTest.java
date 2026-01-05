package com.library.controller;

import java.sql.Connection;
import com.library.utils.DBConnection;

public class DBTest {

    public static void main(String[] args) {
        Connection conn=DBConnection.getConnection();
        System.out.println(conn);
        if (conn != null) {
            System.out.println("Database Connected Successfully");
        } else {
            System.out.println("Database Connection Failed");
        }
    }
}

