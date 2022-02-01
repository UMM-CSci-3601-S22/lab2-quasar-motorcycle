package umm3601.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.UserDatabase listUser functionality
 */
// The tests here include a ton of "magic numbers" (numeric constants).
// It wasn't clear to me that giving all of them names would actually
// help things. The fact that it wasn't obvious what to call some
// of them says a lot. Maybe what this ultimately means is that
// these tests can/should be restructured so the constants (there are
// also a lot of "magic strings" that Checkstyle doesn't actually
// flag as a problem) make more sense.
@SuppressWarnings({ "MagicNumber" })
public class FullUserListFromDB {

  @Test
  public void totalUserCount() throws IOException {
    UserDatabase db = new UserDatabase("/users.json");
    User[] allUsers = db.listUsers(new HashMap<>());
    assertEquals(10, allUsers.length, "Incorrect total number of users");
  }

  @Test
  public void firstUserInFullList() throws IOException {
    UserDatabase db = new UserDatabase("/users.json");
    User[] allUsers = db.listUsers(new HashMap<>());
    User firstUser = allUsers[0];
    assertEquals("Connie Stewart", firstUser.name, "Incorrect name");
    assertEquals(25, firstUser.age, "Incorrect age");
    assertEquals("OHMNET", firstUser.company, "Incorrect company");
    assertEquals("conniestewart@ohmnet.com", firstUser.email, "Incorrect e-mail");
  }
}
