package sqlClass1;

import utils.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E3SQL {
    public static void main(String[] args) {


        String dbUrl = ConfigReader.getPropertyValue("dbUrl");
        String dbUserName = ConfigReader.getPropertyValue("dbUserName");
        String dbPassword = ConfigReader.getPropertyValue("dbPassword");
        List<Map<String,String>> empMapList = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            Statement statement = connection.createStatement();

            String query = "select * from employee";
            ResultSet resultSet = statement.executeQuery(query);
            //information about the data for example no of columns their names data types etc.
            ResultSetMetaData metaData = resultSet.getMetaData();
            // gives us the no of columns in the resultSet
            int columnCount = metaData.getColumnCount();

            while ( resultSet.next()){
                Map<String,String> rowMap = new HashMap<>();
                for (int i = 1; i < columnCount ; i++) {
                    String key = metaData.getColumnLabel(i);
                    String value = resultSet.getNString(i);
                    rowMap.put(key,value);
                }
                empMapList.add(rowMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(empMapList);
    }

}


