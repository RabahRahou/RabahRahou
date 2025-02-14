import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ParsonJSONTest {

    String testParser = "carburantsTest.json";


    @Test
    public void parserTest()
    {
        ParseJSON a = new ParseJSON();
        a.ParserJSON(testParser);

        //test pour la création de la station
        assertEquals(a.getStations().size(), 1);
    }
    
}
