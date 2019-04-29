import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        String layout = "/public/template/layout.vtl";

        get("/", (request, response) -> {
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("template", "public/template/index.vtl");
                    model.put("track", request.session().attribute("track"));
                    return new ModelAndView(model, layout);

                },
                new VelocityTemplateEngine());


        get("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/endangered.vtl");
            model.put("track", request.session().attribute("track"));
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/animal.vtl");
            model.put("track", request.session().attribute("track"));
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
