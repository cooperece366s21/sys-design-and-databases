package edu.cooper.ece366;

import org.jdbi.v3.core.Jdbi;

public class JdbiExampleSqlite {
  public static void main(String[] args) {
    String jdbcUrl = "jdbc:sqlite:sqlitedb";
    Jdbi jdbi = Jdbi.create(jdbcUrl);
    jdbi.withHandle(handle -> handle.execute("select * from users"));
  }
}
