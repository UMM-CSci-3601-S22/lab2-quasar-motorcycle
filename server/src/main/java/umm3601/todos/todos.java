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

  public String getOwner(){
    return this.owner;
  }

  public String getCategory(){
    return this.category;
  }

  public String getBody(){
    return this.body;
  }

  public Boolean getStatus(){
    return this.status;
  }


}
