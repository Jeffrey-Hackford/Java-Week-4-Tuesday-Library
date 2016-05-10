import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Author {
  private int id;
  private String name;

  public Author(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static List<Author> all() {
    String sql = "SELECT * FROM authors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  @Override
  public boolean equals(Object otherAuthor) {
    if (!(otherAuthor instanceof Author)) {
      return false;
    } else {
      Author newAuthor = (Author) otherAuthor;
      return this.getName().equals(newAuthor.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO authors (name) VALUES (:name)";
      con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

}