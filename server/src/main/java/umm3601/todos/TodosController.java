package umm3601.todos;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;

public class TodosController {

  private TodosDatabase database;


public TodosController(TodosDatabase database) {
    this.database = database;
  }

  //takes an ID and returns the correct todo
  public void getTodo(Context ctx) {
    String id = ctx.pathParam("id");
    Todos todo = database.getTodo(id);
    if (todo != null) {
      ctx.json(todo);
      ctx.status(HttpCode.OK);
    } else {
      throw new NotFoundResponse("No user with id " + id + " was found.");
    }
  }

  //Uses listTodos from TodosDatabase to filter and list the requested todos
  public void getTodos(Context ctx) {
    Todos[] todos = database.listTodos(ctx.queryParamMap());
    ctx.json(todos);
  }


}
