import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {
    
    @Test
    public void testConstructor() {
        double latitude = 50;
        double longitude = 120;
        Coordinate coordinate = new Coordinate(latitude, longitude);
        assertEquals(latitude, coordinate.getLatitude(), 0.0001);
        assertEquals(longitude, coordinate.getLongitude(), 0.0001);
    }

    @Test
    public void testGetLatitude() {
        Coordinate coordinate = new Coordinate(50, 120);
        assertEquals(50, coordinate.getLatitude(), 0.0001);
    }

    @Test
    public void testGetLongitude() {
        Coordinate coordinate = new Coordinate(50, 120);
        assertEquals(120, coordinate.getLongitude(), 0.0001);
    }

}
