package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;


@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByOwner {

  @Test
  public void filterUsersByOwner() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Todos[] allTodos = db.listTodos(new HashMap<>());

    Todos[] totalBlancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals(43, totalBlancheTodos.length, "Limited to wrong number of todos");
  }


  @Test
  public void listUsersWithOwnerFilter() throws IOException {
    TodosDatabase db = new TodosDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    Todos[] BlancheUsers = db.listTodos(queryParams);
    assertEquals(43, BlancheUsers.length, "Incorrect number of todos with Blanche");

    queryParams.put("owner", Arrays.asList(new String[] {"Fry"}));
    Todos[] FryUsers = db.listTodos(queryParams);
    assertEquals(61, FryUsers.length, "Incorrect number of Todos with Fry");
  }

}
