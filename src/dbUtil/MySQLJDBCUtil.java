package dbUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySQLJDBCUtil {
    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try (FileInputStream file = new FileInputStream("C:\\Users\\Davide\\Desktop\\Corsi\\Corso DEVELHOPE\\db.properties")) {

            Properties properties = new Properties();
            properties.load(file);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("* CONNECTION TO DB SUCCESFUL *");

        } catch (IOException e) {

            System.out.println("* UNABLE TO CONNECT TO THE DB, MODIFY IT OR RUN A QUERY *\n" + e.getMessage());

        }
        return connection;
    }
}
