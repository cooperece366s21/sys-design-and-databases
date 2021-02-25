package edu.cooper.ece366;

import com.google.gson.Gson;
import spark.ResponseTransformer;
import spark.Spark;

/** Hello world! */
public class App {

  private static final Gson gson = new Gson();

  private static final ResponseTransformer JSON_TRANSFORMER = gson::toJson;

  public static void main(String[] args) {
    Handler handler = new Handler(new Service(new Store()));

    // initialize routes
    Spark.get("/ping", (req, res) -> "OK");
    Spark.get("/users/:id", (req, res) -> handler.getUser(req), JSON_TRANSFORMER);
    Spark.post("/users", (req, res) -> handler.createUser(req), JSON_TRANSFORMER);
  }
}
