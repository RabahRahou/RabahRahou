import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StationTest {

@Test
    public void testConstructorWithMandatoryParameters() {
        // Arrange
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        
        // Act
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels);

        // Assert
        assertNotNull(station);
        assertEquals(adresse, station.getAdresse());
        assertEquals(coordonnee, station.getCoordonnee());
        assertEquals(codeDepartement, station.getCodeDepartement());
        assertEquals(codeRegion, station.getCodeRegion());
        assertEquals(fuels, station.getFuels());
        assertNull(station.getListServices()); // listServices should be null initially
    }

    @Test
    public void testConstructorWithAllParameters() {
        // Arrange
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        ArrayList<String> listServices = new ArrayList<>(Arrays.asList("RelaisColis", "BoutiqueAlimentaire"));

        // Act
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        // Assert
        assertNotNull(station);
        assertEquals(adresse, station.getAdresse());
        assertEquals(coordonnee, station.getCoordonnee());
        assertEquals(codeDepartement, station.getCodeDepartement());
        assertEquals(codeRegion, station.getCodeRegion());
        assertEquals(fuels, station.getFuels());
        assertNotNull(station.getListServices());
        assertEquals(listServices, station.getListServices());
    }

    @Test
    public void testLookUpStation() {
        // Arrange
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95,2.0, true));
        fuels.add(new Fuel(FuelType.E10,2.0, true));
        ArrayList<String> listServices = new ArrayList<>(Arrays.asList("RelaisColis", "BoutiqueAlimentaire"));
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        //Assert
        //services présents
        ArrayList<String> requiredServices1 = new ArrayList<>(Arrays.asList("RelaisColis"));
        assertTrue(station.lookUpStation(requiredServices1));

        ArrayList<String> requiredServices2 = new ArrayList<>(Arrays.asList("BoutiqueAlimentaire"));
        assertTrue(station.lookUpStation(requiredServices2));

        ArrayList<String> requiredServices3 = new ArrayList<>(Arrays.asList("RelaisColis", "BoutiqueAlimentaire"));
        assertTrue(station.lookUpStation(requiredServices3));

        //services pas présents
        ArrayList<String> requiredServices4 = new ArrayList<>(Arrays.asList("StationDeGonflage"));
        assertFalse(station.lookUpStation(requiredServices4));

        ArrayList<String> requiredServices5 = new ArrayList<>(Arrays.asList("RelaisColis", "StationDeGonflage"));
        assertFalse(station.lookUpStation(requiredServices5));

        ArrayList<String> requiredServices6 = new ArrayList<>(Arrays.asList("RelaisColis", "BoutiqueAlimentaire", "StationDeGonflage"));
        assertFalse(station.lookUpStation(requiredServices6));

        //vide
        ArrayList<String> requiredServices = new ArrayList<>();
        assertTrue(station.lookUpStation(requiredServices));

    }


    @Test
    public void testToStringWithAllFuelsAvailable() {
        // Arrange
        String adresse = "123Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95,2.0, true));
        fuels.add(new Fuel(FuelType.Gazole,3.0, true));
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels);

        // Act
        String result = station.toString();

        // Cas où tous les fuels proposé sont disponibles
        String expected = "La station " + adresse + " se situe dans le département numéro " + codeDepartement + ", elle possède les carburants suivants : SP95 Gazole ";
        assertEquals(expected, result);

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95,2.0, true));
        fuels2.add(new Fuel(FuelType.Gazole,3.0, false));

        Station station2 = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels2);
        String result2 = station2.toString();

        //Cas où tous un des fuels n'est pas disponible
        String expected2 = "La station " + adresse + " se situe dans le département numéro " + codeDepartement + ", elle possède les carburants suivants : SP95 ";
        assertEquals(expected2, result2);

        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95,2.0, false));
        fuels2.add(new Fuel(FuelType.Gazole,3.0, false));

        Station station3 = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels3);
        String result3 = station3.toString();
        
        //Cas où aucuns des fuels n'est disponible
        String expected3 = "La station " + adresse + " se situe dans le département numéro " + codeDepartement + ", elle possède les carburants suivants : ";
        assertEquals(expected3, result3);

        ArrayList<Fuel> fuels4 = new ArrayList<>();
        Station station4 = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels4);
        String result4 = station4.toString();

        //Cas où la liste est vide
        String expected4 = "La station " + adresse + " se situe dans le département numéro " + codeDepartement + ", elle possède les carburants suivants : ";
        assertEquals(expected4, result4);
    }

    //test des getters
    @Test
    public void testGetAdresse() {
        
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        List<String> listServices = new ArrayList<>();
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        
        String result = station.getAdresse();


        assertEquals(adresse, result);
    }

    @Test
    public void testGetFuels() {
        
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95,2.0, true));
        fuels.add(new Fuel(FuelType.Gazole,3.0, true));
        List<String> listServices = new ArrayList<>();
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        
        ArrayList<Fuel> result = station.getFuels();

        
        assertEquals(fuels, result);
    }

    @Test
    public void testGetListServices() {
        
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        List<String> listServices = new ArrayList<>();
        listServices.add("RelaisColis");
        listServices.add("BoutiqueAlimentaire");
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        
        ArrayList<String> result = station.getListServices();

        
        assertEquals(listServices, result);
    }

    @Test
    public void testGetCoordonnee() {
        
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        List<String> listServices = new ArrayList<>();
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        
        Coordinate result = station.getCoordonnee();

        
        assertEquals(coordonnee, result);
    }

    @Test
    public void testGetCodeDepartement() {
        
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        List<String> listServices = new ArrayList<>();
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        
        int result = station.getCodeDepartement();

        
        assertEquals(codeDepartement, result);
    }

    @Test
    public void testGetCodeRegion() {
        
        String adresse = "123 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;
        ArrayList<Fuel> fuels = new ArrayList<>();
        List<String> listServices = new ArrayList<>();
        Station station = new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels, listServices);

        int result = station.getCodeRegion();

        
        assertEquals(codeRegion, result);
    }

}