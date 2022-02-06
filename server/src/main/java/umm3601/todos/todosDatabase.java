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
    filteredTodos = filterTodosByStatus(filteredTodos, statusParam);
  }

  if(queryParams.containsKey("contains")){
    String bodyparam = queryParams.get("contains").get(0);
    filteredTodos = filterTodosByContains(filteredTodos, bodyparam);
  }
  if(queryParams.containsKey("orderBy")){
    List<todos> resultE;
    String orderparam = queryParams.get("orderBy").get(0);
    resultE = sorted(filteredTodos, orderparam);
    todos[] array = new todos[filteredTodos.length];
    resultE.toArray(array);
    filteredTodos = array;
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


  return filteredTodos;
}

public todos getTodo(String id) {
  return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
}


public todos[] filterTodosByNumber(todos[] todos, int limit){
  int len = todos.length;
  if(len < limit){
    limit = len;
  }
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

public List<todos> sorted(todos[] list2, String type)
    {
      List<todos> list = Arrays.asList(list2);
      if(type.equals("owner")){
        list.sort((o1, o2) -> o1.getOwner().compareTo(o2.getOwner()));
      }
      if(type.equals("category")){
        list.sort((o1, o2) -> o1.getCategory().compareTo(o2.getCategory()));
      }
      if(type.equals("body")){
        list.sort((o1, o2) -> o1.getBody().compareTo(o2.getBody()));
      }
      if(type.equals("status")){
        list.sort((o1, o2) -> o1.getStatus().compareTo(o2.getStatus()));
      }
      return list;
}
}

