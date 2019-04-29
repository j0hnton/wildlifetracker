import java.util.List;
import org.sql2o.*;
public class sighting {

    private int id;
    private String Rangername;
    private String Animalname;
    private String Health;
    private String Age;
    private String Location;


    public sighting(String rangername, String animalname, String health,String age, String location) {

        Rangername=rangername;
        Animalname=animalname;
        Health=health;
        Age=age;
        Location=location;
    }

    public String getrangername() {
        return Rangername;
    }

    public String getanimalname() {
        return Animalname;
    }

    public String gethealth() {
        return Health;
    }

    public String getage() {
        return Age;
    }

    public String getlocation() {
        return Location;
    }

    public int getid() {
        return id;
    }

    @Override
    public boolean equals(Object anothersighting) {
        if (!(anothersighting instanceof animal)) {
            return false;
        } else {
            sighting newsighting = (sighting) anothersighting;
            return this.getrangername().equals(newsighting.getrangername()) &&
                    this.getanimalname() == newsighting.getanimalname() &&
                    this.gethealth() == newsighting.gethealth()&&
                    this.getage() == newsighting.getage() &&
                    this.getlocation() == newsighting.getlocation();



        }
    }

    public static List<sighting> all() {
        String sql = "SELECT * FROM sighting";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(sighting.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sighting (rangername ,animalname, health, age, location) VALUES (:rangername, :animalname , :health , :age , :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangername", Rangername)
                    .addParameter("animalname", Animalname)
                    .addParameter("health",Health)
                    .addParameter("age", Age)
                    .addParameter("location",Location)
                    .executeUpdate()
                    .getKey();
        }
    }
}
