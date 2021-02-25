package edu.cooper.ece366;

import spark.Request;

public class Handler {

  private final Service service;

  public Handler(final Service service) {
    this.service = service;
  }

  public User getUser(final Request req) {
    return new User(1, "yeet");
  }

  public User createUser(final Request req) {
    return new User(1, "yeet");
  }
}
