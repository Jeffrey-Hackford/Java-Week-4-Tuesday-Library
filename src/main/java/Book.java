import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Book {
  private int copies;
  private String author;
  private String title;
  private int id;

  public Book(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public static List<Book> all() {
    String sql = "SELECT * FROM books";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

  @Override
  public boolean equals(Object otherBook){
    if (!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO books (title) VALUES (:title)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("title", this.title)
      .executeUpdate()
      .getKey();
    }
  }

  public static Book find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE books SET title = :title WHERE id = :id";
      Book book = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Book.class);
      return book;
    }
  }

  public void addAuthor(Author author) {
    try (Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO authors_books (author_id, book_id) VALUES (:author_id, :book_id)";
      con.createQuery(sql)
      .addParameter("author_id", author.getId())
      .addParameter("book_id", this.getId())
      .executeUpdate();
    }
  }

  public List<Author> getAuthors() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT author_id FROM authors_books WHERE book_id = :book_id";
      List<Integer> authorIds = con.createQuery(joinQuery)
        .addParameter("book_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Author> authors = new ArrayList<Author>();

      for (Integer authorId : authorIds) {
        String bookQuery = "Select * From authors WHERE id = :authorId";
        Author author = con.createQuery(bookQuery)
          .addParameter("authorId", authorId)
          .executeAndFetchFirst(Author.class);
        authors.add(author);
      }
      return authors;
    }
  }
}
