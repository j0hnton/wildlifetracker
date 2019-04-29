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


        get("/animaldisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/animaldisplay.vtl");
            model.put("tracks", animal.all());
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/endangereddisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("tracks", sighting.all());
            model.put("template", "public/template/endangereddisplay.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "public/template/animal.vtl");
            model.put("tracks", request.session().attribute("track"));
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        //post//

        post("/animaldisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rangername");
            String animalname = request.queryParams("animalname");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");

            animal newAnimal = new animal(rangername, animalname, health, age, location);
            newAnimal.save();
            model.put("template", "public/template/animaldisplay.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

             post("/endangereddisplay", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rname");
            String animalname = request.queryParams("aname");
            String health = request.queryParams("ahealth");
            String age = request.queryParams("aage");
            String location = request.queryParams("alocation");

            sighting newSighting = new sighting (rangername, animalname, health, age, location);
            newSighting.save();
            model.put("template", "public/template/endangereddisplay.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rangername");
            String animalname = request.queryParams("animalname");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String location = request.queryParams("location");

            animal newAnimal = new animal(rangername, animalname, health, age, location);
            newAnimal.save();
            model.put("template", "public/template/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/esuccess", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String rangername = request.queryParams("rname");
            String animalname = request.queryParams("aname");
            String health = request.queryParams("ahealth");
            String age = request.queryParams("aage");
            String location = request.queryParams("alocation");

            sighting newSighting = new sighting (rangername, animalname, health, age, location);
            newSighting.save();
            model.put("template", "public/template/esuccess.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


    }
}
