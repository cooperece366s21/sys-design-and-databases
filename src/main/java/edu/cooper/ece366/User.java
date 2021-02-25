package edu.cooper.ece366;

public class User {
  private final Integer id;
  private final String name;

  public User(final Integer id, final String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
