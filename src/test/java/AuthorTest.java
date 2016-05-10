import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Author_instantiatesCorrectly_True() {
    Author newAuthor = new Author("Author 1");
    assertEquals(true, newAuthor instanceof Author);
  }

  @Test
  public void all_emptyAtFirst_True() {
    assertEquals(Author.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfAuthorsAreTheSame_True() {
    Author firstAuthor = new Author("Author 1");
    Author secondAuthor = new Author("Author 1");
    assertTrue(firstAuthor.equals(secondAuthor));
  }

  @Test
  public void save_returnsTrueIfAuthorsAreTheSame() {
    Author newAuthor = new Author("Author 1");
    newAuthor.save();
    assertTrue(Author.all().get(0).equals(newAuthor));
  }

  @Test
  public void save_assignsIdToObject_int() {
    Author newAuthor = new Author("Author 2");
    newAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertEquals(newAuthor.getId(), savedAuthor.getId());
  }

  @Test
  public void addBook_addsBookToAuthor_List() {
    Author myAuthor = new Author("Kurt Vonnegut");
    myAuthor.save();
    Book myBook = new Book ("Slaughterhouse Five");
    myBook.save();
    myAuthor.addBook(myBook);
    Book savedBook = myAuthor.getBooks().get(0);
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void getBooks_returnsAllBooks_List() {
    Author myAuthor = new Author("Bernard Rollin");
    myAuthor.save();
    Book myBook = new Book("Science and Ethics");
    myBook.save();
    myAuthor.addBook(myBook);
    List savedBooks = myAuthor.getBooks();
    assertEquals(1, savedBooks.size());
  }

}
