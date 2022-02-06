package umm3601.todos;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;

public class todosController {

  private todosDatabase database;


public todosController(todosDatabase database) {
    this.database = database;
  }

  public void getTodo(Context ctx) {
    String id = ctx.pathParam("id");
    todos todo = database.getTodo(id);
    if (todo != null){
      ctx.json(todo);
      ctx.status(HttpCode.OK);
    } else {
      throw new NotFoundResponse("No user with id " + id + " was found.");
    }
  }

  public void getTodos(Context ctx) {
    todos[] todos = database.listTodos(ctx.queryParamMap());
    ctx.json(todos);
  }


}
