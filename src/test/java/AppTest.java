import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*;
import org.junit.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Library");
  }

  @Test
  public void authorIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#AuthorName").with("Author 1");
    submit(".Author");
    assertThat(pageSource()).contains("Authors 1");
  }

  @Test
  public void bookIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#BookName").with("Book 1");
    submit(".Book");
    assertThat(pageSource()).contains("Book 1");
  }
}
