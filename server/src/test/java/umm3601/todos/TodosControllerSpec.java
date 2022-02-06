package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;

import umm3601.Server;

/**
 * Tests the logic of the UserController
 *
 * @throws IOException
 */
// The tests here include a ton of "magic numbers" (numeric constants).
// It wasn't clear to me that giving all of them names would actually
// help things. The fact that it wasn't obvious what to call some
// of them says a lot. Maybe what this ultimately means is that
// these tests can/should be restructured so the constants (there are
// also a lot of "magic strings" that Checkstyle doesn't actually
// flag as a problem) make more sense.
@SuppressWarnings({ "MagicNumber" })
public class TodosControllerSpec {

  private Context ctx = mock(Context.class);

  private TodosController todosController;
  private static TodosDatabase db;

  @BeforeEach
  public void setUp() throws IOException {
    ctx.clearCookieStore();
    db = new TodosDatabase(Server.TODOS_DATA_FILE);
    todosController = new TodosController(db);
  }

  @Test
  public void canGetTodoWithSpecifiedId() throws IOException {
    String id = "5889598528c4748a0292e014";
    Todos todo = db.getTodo(id);

    when(ctx.pathParam("id")).thenReturn(id);

    todosController.getTodo(ctx);

    verify(ctx).json(todo);
    verify(ctx).status(HttpCode.OK);
  }

  @Test
  public void canGetTodosWithCategory() throws IOException {
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("category", Arrays.asList(new String[] {"groceries"}));
    when(ctx.queryParamMap()).thenReturn(queryParams);

    todosController.getTodos(ctx);

    // Confirm that all the users passed to `json` work for OHMNET.
    ArgumentCaptor<Todos[]> argument = ArgumentCaptor.forClass(Todos[].class);
    verify(ctx).json(argument.capture());
    for (Todos todo : argument.getValue()) {
      assertEquals("groceries", todo.category);
    }
  }

  @Test
  public void canGetTodosWithGivenCategoryAndOwner() throws IOException {
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("category", Arrays.asList(new String[] {"groceries"}));
    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    when(ctx.queryParamMap()).thenReturn(queryParams);

    todosController.getTodos(ctx);

    // Confirm that all the users passed to `json` work for OHMNET
    // and have age 25.
    ArgumentCaptor<Todos[]> argument = ArgumentCaptor.forClass(Todos[].class);
    verify(ctx).json(argument.capture());
    for (Todos user : argument.getValue()) {
      assertEquals("groceries", user.category);
      assertEquals("Blanche", user.owner);
    }
  }

  @Test
  public void respondsAppropriatelyToRequestForNonexistentId() throws IOException {
    when(ctx.pathParam("id")).thenReturn(null);
    Assertions.assertThrows(NotFoundResponse.class, () -> {
      todosController.getTodo(ctx);
    });
  }

  @Test
  public void canGetTodosWithGivenStatusAndContains() throws IOException {
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("status", Arrays.asList(new String[] {"complete"}));
    queryParams.put("contains", Arrays.asList(new String[] {"Incididunt enim ea sit qui esse magna eu. Nisi sunt exercitation est Lorem consectetur incididunt cupidatat laboris commodo veniam do ut sint."}));
    when(ctx.queryParamMap()).thenReturn(queryParams);

    todosController.getTodos(ctx);

    // Confirm that all the users passed to `json` work for OHMNET
    // and have age 25.
    ArgumentCaptor<Todos[]> argument = ArgumentCaptor.forClass(Todos[].class);
    verify(ctx).json(argument.capture());
    for (Todos user : argument.getValue()) {
      assertEquals(true, user.status);
      assertEquals("Incididunt enim ea sit qui esse magna eu. Nisi sunt exercitation est Lorem consectetur incididunt cupidatat laboris commodo veniam do ut sint.", user.body);
    }
  }

  @Test
  public void canGetAllTodos() throws IOException {
    // Call the method on the mock context, which doesn't
    // include any filters, so we should get all the users
    // back.
    todosController.getTodos(ctx);

    // Confirm that `json` was called with all the users.
    ArgumentCaptor<Todos[]> argument = ArgumentCaptor.forClass(Todos[].class);
    verify(ctx).json(argument.capture());
    assertEquals(db.size(), argument.getValue().length);
  }

  @Test
  public void respondsAppropriatelyToIllegalAge() {
    // We'll set the requested "age" to be a string ("abc")
    // that can't be parsed to a number.
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("limit", Arrays.asList(new String[] {"abc"}));
    when(ctx.queryParamMap()).thenReturn(queryParams);

    // This should now throw a `BadRequestResponse` exception because
    // our request has an age that can't be parsed to a number.
    Assertions.assertThrows(BadRequestResponse.class, () -> {
      todosController.getTodos(ctx);
    });
  }

  @Test
  public void canGetTodosWithGivenSort() throws IOException {
    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("orderBy", Arrays.asList(new String[] {"owner"}));
    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    when(ctx.queryParamMap()).thenReturn(queryParams);

    todosController.getTodos(ctx);


    ArgumentCaptor<Todos[]> argument = ArgumentCaptor.forClass(Todos[].class);
    verify(ctx).json(argument.capture());
    for (Todos user : argument.getValue()) {
      assertEquals("Blanche", user.owner);
    }
  }




}
