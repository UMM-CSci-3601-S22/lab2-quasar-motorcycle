package umm3601;

import java.io.IOException;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;
import umm3601.user.UserDatabase;
import umm3601.user.UserController;
import umm3601.todos.todosController;
import umm3601.todos.todosDatabase;

public class Server {

  private static final int PORT_NUMBER = 4567;
  public static final String CLIENT_DIRECTORY = "../client";
  public static final String USER_DATA_FILE = "/users.json";
  public static final String TODOS_DATA_FILE = "/todos.json";
  private static UserDatabase userDatabase;
  private static todosDatabase todosDatabase;

  public static void main(String[] args) {

    // Initialize dependencies
    UserController userController = buildUserController();
    todosController todosController = buildTodosController();

    Javalin server = Javalin.create(config -> {
      // This tells the server where to look for static files,
      // like HTML and JavaScript.
      config.addStaticFiles(CLIENT_DIRECTORY, Location.EXTERNAL);
      // This adds a Javalin plugin that will list all of the
      // routes/endpoints that we add below on a page reachable
      // via the "/api" path.
      config.registerPlugin(new RouteOverviewPlugin("/api"));
      // The next line starts the server listening on port 4567.
    }).start(PORT_NUMBER);

    // Simple example route
    server.get("/hello", ctx -> ctx.result("Hello World"));

    // Redirects to create simpler URLs
    server.get("/users", ctx -> ctx.redirect("/users.html"));
    server.get("/todos", ctx -> ctx.redirect("/todos.html"));

    // API endpoints

    // Get specific user
    server.get("/api/users/{id}", userController::getUser);

    // List users, filtered using query parameters
    server.get("/api/users", userController::getUsers);

    //List todos, filtered using query parameters
    server.get("/api/todos/{id}", todosController::getTodo);
  }

  /***
   * Create a database using the json file, use it as data source for a new
   * UserController
   *
   * Constructing the controller might throw an IOException if there are problems
   * reading from the JSON "database" file. If that happens we'll print out an
   * error message exit the program.
   */
  private static UserController buildUserController() {
    UserController userController = null;

    try {
      userDatabase = new UserDatabase(USER_DATA_FILE);
      userController = new UserController(userDatabase);
    } catch (IOException e) {
      System.err.println("The server failed to load the user data; shutting down.");
      e.printStackTrace(System.err);

      // Exit from the Java program
      System.exit(1);
    }

    return userController;
  }

  private static todosController buildTodosController() {
    todosController todosController = null;

    try {
      todosDatabase = new todosDatabase(TODOS_DATA_FILE);
      todosController = new todosController(todosDatabase);
    } catch (IOException e) {
      System.err.println("The server failed to load the todos data; shutting down.");
      e.printStackTrace(System.err);

      // Exit from the Java program
      System.exit(1);
  }
  return todosController;
}
}

