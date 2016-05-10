import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Book_instantiatesCorrectly_true() {
    Book myBook = new Book("Crying of Lot 49");
    assertEquals(true, myBook instanceof Book);
  }

  @Test
  public void Book_returnsTitleOfBook_Book1() {
    Book newBook = new Book("Book 1");
    assertEquals("Book 1", newBook.getTitle());
  }

  @Test
  public void Book_arrayListEmptyAtFirst_0() {
    assertEquals(Book.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTitlesAreTheSame_True() {
    Book firstBook = new Book("Book 1");
    Book secondBook= new Book("Book 1");
    assertTrue(firstBook.equals(secondBook));
  }

  @Test
  public void save_returnsTrueIfTitlesAreTheSame() {
    Book myBook = new Book ("Eloquent Javascript");
    myBook.save();
    assertTrue(Book.all().get(0).equals(myBook));
  }

  @Test
  public void save_assignsIdToObject() {
    Book myBook = new Book ("Code");
    myBook.save();
    Book savedBook = Book.all().get(0);
    assertEquals(myBook.getId(), savedBook.getId());
  }
}
