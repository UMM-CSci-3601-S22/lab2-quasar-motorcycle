package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.UserDatabase getUser functionality
 */
public class GetTodoByIDFromDB {

  @Test
  public void getStokesClayton() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Todos todo = db.getTodo("588959851e4dc2da5e75599b");
    assertEquals("Blanche", todo.owner, "Incorrect owner");
  }

  @Test
  public void getBoltonMonroe() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Todos todo = db.getTodo("5889598528c4748a0292e014");
    assertEquals("Workman", todo.owner, "Incorrect name");
  }
}
