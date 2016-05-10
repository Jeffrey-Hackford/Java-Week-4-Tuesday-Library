import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/authors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String newAuthor = request.queryParams("AuthorName");
      model.put("authors", Author.all());
      model.put("template", "templates/authors.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/authors/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Author author = Author.find(Integer.parseInt(request.params(":id")));
    //   model.put("author", author);
    //   model.put("allBooks", Book.all());
    //   model.put("template", "templates/author.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


    post("/books", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("books", Book.all());
      model.put("template", "templates/books.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
