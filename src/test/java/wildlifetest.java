import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class wildlifetest {

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/herosalon_test", null, null);
    }

    @After
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM Stylists *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void animal_instantiatesCorrectly_true() {
        animal myanimal = new animal("rangername","animalname","health","age", "location");
        assertEquals(true, myanimal instanceof animal);
    }
    @Test
    public void sighting_instantiatesCorrectly_true() {
        sighting mysighting = new sighting("rangername","animalname","health","age", "location");
        assertEquals(true, mysighting instanceof sighting);
    }
    @Test
    public void App_instantiatesCorrectly_true() {
        App myApp = new App();
        assertEquals(true, myApp instanceof App);
    }

}
