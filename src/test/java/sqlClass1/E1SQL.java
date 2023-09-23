package sqlClass1;

import java.sql.*;

public class E1SQL {
    public static void main(String[] args) {
        // jdbc API -companyname-IP address-port-nameOfDatabase
        String dbUrl = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String userName = "syntax_hrm";
        String password =  "syntaxhrm123";
        try {
            // we need to create a connection to connect to database first
            Connection connection = DriverManager.getConnection(dbUrl,userName,password);
            // taking queries execute then on dtbase and brings the results back
            Statement statement = connection.createStatement();
            String querry ="select * from person";
            // contains querry resultd we execute on database
            ResultSet resultSet = statement.executeQuery(querry);
            while ( resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String age = resultSet.getString("age");
                String city = resultSet.getString("City");
                System.out.println(firstName + " " + age + " " + city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
