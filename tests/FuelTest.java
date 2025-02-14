import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FuelTest {
    // Création d'instances de Fuel pour les tests
    Fuel fuel1 = new Fuel(FuelType.SP95, 1.5, true);
    Fuel fuel2 = new Fuel(FuelType.SP98, 1.6, false);
    Fuel fuel3 = new Fuel(FuelType.Gazole, 1.8, false);
    Fuel fuel4 = new Fuel(FuelType.E85, 1.9, false);
    Fuel fuel5 = new Fuel(FuelType.GPLc, 2.0, false);
    Fuel fuel6 = new Fuel(FuelType.E10, 1.7, false);

    

    @Test
    public void testGetFuelType() {
        assertEquals(FuelType.SP95, fuel1.getFuelType());
        assertEquals(FuelType.SP98, fuel2.getFuelType());
        assertEquals(FuelType.Gazole, fuel3.getFuelType());
        assertEquals(FuelType.E85, fuel4.getFuelType());
        assertEquals(FuelType.GPLc, fuel5.getFuelType());
        assertEquals(FuelType.E10, fuel6.getFuelType());
    }

    @Test
    public void testGetPrix() {
        assertEquals(1.5, fuel1.getPrix(), 0.01); // Delta de tolérance de 0.01 pour les valeurs décimales
        assertEquals(1.6, fuel2.getPrix(), 0.01);
        assertEquals(1.8, fuel3.getPrix(), 0.01);
        assertEquals(1.9, fuel4.getPrix(),0.01);
        assertEquals(2.0, fuel5.getPrix(),0.01);
        assertEquals(1.7, fuel6.getPrix(),0.01);
        
    }

    @Test
    public void testGetDispo() {
        assertEquals(true, fuel1.getDispo());
        assertEquals(false, fuel2.getDispo());
        assertEquals(false, fuel3.getDispo());
        assertEquals(false, fuel4.getDispo());
        assertEquals(false, fuel5.getDispo());
        assertEquals(false, fuel6.getDispo());
    }
}
