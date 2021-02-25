package edu.cooper.ece366;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class JdbcExampleSqlite {
  public static void main(String[] args) throws Exception {
    // connect to db, create database
    String jdbcUrl = "jdbc:sqlite:sqlitedb";
    Connection connection = DriverManager.getConnection(jdbcUrl);
    System.out.println("connected via " + connection.getMetaData().getDriverName());

    // create tables
    String tableStatement = Files.readString(Paths.get("src/main/resources/create-tables.sql"));
    List<String> createTableStatements = Arrays.asList(tableStatement.split(";"));
    createTableStatements.stream()
        .map(String::strip)
        .filter(s -> !s.isEmpty())
        .forEach(
            createTableStatement -> {
              try (Statement statement = connection.createStatement()) {
                boolean execute = statement.execute(createTableStatement);
                System.out.println("executed statement, return value " + execute);
              } catch (SQLException e) {
                e.printStackTrace();
              }
            });

    // insert data
    String insertSql = "insert into users (id, name) values (?, ?)";
    PreparedStatement psInsert = connection.prepareStatement(insertSql);
    psInsert.setInt(1, 5);
    psInsert.setString(2, "yeet");

    int row = psInsert.executeUpdate();
    System.out.println("rows affected: " + row);

    // select data
    String selectSql = "select id, name from users where id = ?";
    PreparedStatement psSelect = connection.prepareStatement(selectSql);
    psSelect.setInt(1, 5);

    ResultSet resultSet = psSelect.executeQuery();
    while (resultSet.next()) {
      resultSet.getInt(1);
      resultSet.getString(2);
    }

    // close connection
    connection.close();
  }
}
