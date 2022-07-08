import dbUtil.MySQLJDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
SQL 04 - View
Exercise - SQL-view

before doing this exercise, you have to complete before the following exercises:
0-SQL-installation-and-user-permissions
SQL-table
SQL-Select
SQL-alter
create a class Student that has:
2 string properties:
name
surname
a constructor for setting the 2 values
getters methods
use JDBC for executing the following queries on the jdbc:mysql://localhost:3306/newdb database:
create a view italian_students that gets all the name and surname of the Italian students
create a view german_students that gets all the name and surname of the German students
execute a select using the the italian_students and put the result in an ArrayList of Student objects called italianStudents
execute a select using the the german_students and put the result in an ArrayList of Student objects called germanStudents
 */
public class Main {
    public static void main(String[] args) {

        try (Connection connection = MySQLJDBCUtil.getConnection()) {

            Statement statement = connection.createStatement();

            statement.executeUpdate(
                    "CREATE OR REPLACE VIEW italian_students " +
                            "AS SELECT first_name, last_name " +
                            "FROM students " +
                            "WHERE country = 'Italy';"
            );
            System.out.println("- Create view: italian_students");

            statement.executeUpdate(
                    "CREATE OR REPLACE VIEW german_students " +
                            "AS SELECT first_name, last_name " +
                            "FROM students " +
                            "WHERE country = 'Germay';"
            );
            System.out.println("- Create view: german_students");

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM italian_students"
            );

            ArrayList<Student> italianStudents = new ArrayList<>();

            while (resultSet.next()) {
                italianStudents.add(new Student(resultSet.getString("first_name"), resultSet.getString("last_name")));

            }

            resultSet = statement.executeQuery(
                    "SELECT * FROM german_students"
            );

            ArrayList<Student> germanStudents = new ArrayList<>();

            while (resultSet.next()) {
                germanStudents.add(new Student(resultSet.getString("first_name"), resultSet.getString("last_name")));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
