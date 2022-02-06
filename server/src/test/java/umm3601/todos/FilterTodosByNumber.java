package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByNumber {

  @Test
  public void filterUsersByAge() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Todos[] allTodos = db.listTodos(new HashMap<>());

    Todos[] total5todos = db.filterTodosByNumber(allTodos, 5);
    assertEquals(5, total5todos.length, "Limited to wrong number of todos");

    Todos[] AllTodos = db.filterTodosByNumber(allTodos, 1000);
    assertEquals(300, AllTodos.length, "Limited to wrong number of todos");
  }
}
