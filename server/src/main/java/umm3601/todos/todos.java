package umm3601.todos;

@SuppressWarnings({"VisibilityModifier"})
public class todos {
  // By default Java field names shouldn't start with underscores.
  // Here, though, we *have* to use the name `_id` to match the
  // name of the field in the database.
  @SuppressWarnings({"MemberName"})
  public String _id;
  public String owner;
  public boolean status;
  public String body;
  public String category;
}

