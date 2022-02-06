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
public int[] test = {1, 3, 4};
public boolean Complete;

public todosDatabase(String todosDataFile) throws IOException {
  InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todosDataFile));
  ObjectMapper objectMapper = new ObjectMapper();
  allTodos = objectMapper.readValue(reader, todos[].class);
}

public int size() {
  return allTodos.length;
}

public todos[] listTodos(Map<String, List<String>> queryParams){
  todos[] filteredTodos = allTodos;

  if (queryParams.containsKey("owner")){
    String ownerParam = queryParams.get("owner").get(0);
    filteredTodos = filterTodosByOwner(filteredTodos, ownerParam);

  }
  if (queryParams.containsKey("category")){
    String categoryParam = queryParams.get("category").get(0);
    filteredTodos = filterTodosByCategory(filteredTodos, categoryParam);

  }

  if (queryParams.containsKey("status")){
    String statusParam = queryParams.get("status").get(0);
    System.out.println(statusParam);
    filteredTodos = filterTodosByStatus(filteredTodos, statusParam);
  }
  if(queryParams.containsKey("limit")){
    String limitParam = queryParams.get("limit").get(0);
    try {
      int targetLimit = Integer.parseInt(limitParam);
      filteredTodos = filterTodosByNumber(filteredTodos, targetLimit);
    }catch (NumberFormatException e){
      throw new BadRequestResponse("Specified age '" + limitParam + "' can't be parsed to an integer");
    }
  }

  if(queryParams.containsKey("contains")){
    String bodyparam = queryParams.get("contains").get(0);
    System.out.println(bodyparam);
    filteredTodos = filterTodosByContains(filteredTodos, bodyparam);
  }


  return filteredTodos;
}

public todos getTodo(String id) {
  return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
}


public todos[] filterTodosByNumber(todos[] todos, int limit){
  todos[] result;
  result = new todos[limit];
  for (int i = 0; i < limit; i++) {
    result[i] = todos[i];
  }
  return result;
}


public todos[] filterTodosByOwner(todos[] todos, String targetOwner){
  return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(todos[]::new);
}


public todos[] filterTodosByContains(todos[] todos, String targetBody){
  return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(todos[]::new);
}

public todos[] filterTodosByCategory(todos[] todos, String targetCategory){
  return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(todos[]::new);
}

public todos[] filterTodosByStatus(todos[] todos, String targetStatus){

  if(targetStatus.equals("complete")){
    Complete = true;
  }
  if(targetStatus.equals("incomplete")){
    Complete = false;
  }
  return Arrays.stream(todos).filter(x -> x.status == Complete).toArray(todos[]::new);
}
}

