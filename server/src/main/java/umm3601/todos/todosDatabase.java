package umm3601.todos;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.BadRequestResponse;


public class todosDatabase {
private todos[] allTodos;

public todosDatabase(String todosDataFile) throws IOException {
  InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todosDataFile));
  ObjectMapper objectMapper = new ObjectMapper();
  allTodos = objectMapper.readValue(reader, todos[].class);
}

public int size() {
  return allTodos.length;
}

public todos getTodo(String id) {
  return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
}

//public User[] listUsers(Map<String, List<String>> queryParams) {

public todos[] listTodos(Map<String, List<String>> queryParams){
  todos[] filteredTodos = allTodos;

  if (queryParams.containsKey("owner")){
    String ownerParam = queryParams.get("owner").get(0);
    filteredTodos = filterTodosByOwner(filteredTodos, ownerParam);
  }

  return filteredTodos;
}

public todos[] filterTodosByOwner(todos[] todos, String targetOwner){
  return Arrays.stream(todos).filter(x -> x.owner == targetOwner).toArray(todos[]::new);
}


}
