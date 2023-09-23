package sqlClass1;

import utils.CommonMethods;
import utils.ConfigReader;

import java.sql.*;

public class E2SQL extends CommonMethods {
    public static void main(String[] args) {

        // break till 9:06
        String dbUrl = ConfigReader.getPropertyValue("dbUrl");
        String dbUserName = ConfigReader.getPropertyValue("dbUserName");
        String dbPassword = ConfigReader.getPropertyValue("dbPassword");
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            Statement statement = connection.createStatement();

            String query = "select * from employee";
            ResultSet resultSet = statement.executeQuery(query);
            //information about the data for example no of columns their names data types etc.
            ResultSetMetaData metaData = resultSet.getMetaData();
            // gives us the no of columns in the resultSet
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnLabel(i) + " ");
            }
            // loop goes throgh rows
            while (resultSet.next()){
            // goes through colums
                for (int i = 1; i <=columnCount ; i++) {
                    System.out.print(resultSet.getString(i)+" ");
                }
                System.out.println();
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
