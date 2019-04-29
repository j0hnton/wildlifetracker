import java.util.List;
import org.sql2o.*;
    public class animal {

    private int id;
    private String Rangername;
    private String Animalname;
    private String Health;
    private String Age;
    private String Location;


    public animal(String rangername, String animalname, String health,String age, String location) {

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
    public boolean equals(Object anotheranimal) {
        if (!(anotheranimal instanceof animal)) {
            return false;
        } else {
            animal newanimal = (animal) anotheranimal;
            return this.getrangername().equals(newanimal.getrangername()) &&
                    this.getanimalname() == newanimal.getanimalname() &&
                    this.gethealth() == newanimal.gethealth()&&
                    this.getage() == newanimal.getage() &&
                    this.getlocation() == newanimal.getlocation();



        }
    }

    public static List<animal> all() {
        String sql = "SELECT * FROM animal";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(animal.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animal (rangername ,animalname, health, age, location) VALUES (:rangername, :animalname , :health , :age , :location)";
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
