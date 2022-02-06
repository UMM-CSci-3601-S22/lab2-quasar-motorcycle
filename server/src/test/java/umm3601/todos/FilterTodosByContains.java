package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByContains {

  @Test
  public void filterUsersByOwner() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Todos[] allTodos = db.listTodos(new HashMap<>());

    Todos[] totalAliquipTodos = db.filterTodosByContains(allTodos, "Aliquip");
    assertEquals(8, totalAliquipTodos.length, "Limited to wrong number of todos");
  }
}
