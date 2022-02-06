package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByCategory {

  @Test
  public void filterUsersByOwner() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Todos[] allTodos = db.listTodos(new HashMap<>());

    Todos[] totalSoftwareTodos = db.filterTodosByOwner(allTodos, "Blanche");
    totalSoftwareTodos = db.filterTodosByCategory(totalSoftwareTodos, "software design");
    assertEquals(14, totalSoftwareTodos.length, "Limited to wrong number of todos");
  }
}
