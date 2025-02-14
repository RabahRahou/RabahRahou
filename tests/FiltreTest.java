import java.util.ArrayList;
import java.util.List;


public class FiltreTest {
    
    public static void main(String[] args) {

        // Création d'une 1ere liste de carburant
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.E10, 2, true));
        fuels1.add(new Fuel(FuelType.E85, 1.9, true));
        fuels1.add(new Fuel(FuelType.Gazole, 1.2, true));
        fuels1.add(new Fuel(FuelType.GPLc, 1.3, true));
        fuels1.add(new Fuel(FuelType.SP98, 2.6, true));
        // Création d'une 2eme liste de carburant
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.E10, 1.4, true));
        fuels2.add(new Fuel(FuelType.SP95, 2.6, true));
        fuels2.add(new Fuel(FuelType.E85, 1.2, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.6, true));
        fuels2.add(new Fuel(FuelType.GPLc, 1.4, true));
        fuels2.add(new Fuel(FuelType.SP98, 1.7, true));
        // Création d'une 3eme liste de carburant
        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels3.add(new Fuel(FuelType.E10, 1.2, true));
        fuels3.add(new Fuel(FuelType.SP95, 2.2, true));
        fuels3.add(new Fuel(FuelType.E85, 1.2, true));
        fuels3.add(new Fuel(FuelType.Gazole, 1.3, true));
        fuels3.add(new Fuel(FuelType.GPLc, 2.0, true));
        fuels3.add(new Fuel(FuelType.SP98, 1.9, true));
        // Création d'une 4eme liste de carburant
        ArrayList<Fuel> fuels4 = new ArrayList<>();
        fuels4.add(new Fuel(FuelType.E10, 2.9, true));
        fuels4.add(new Fuel(FuelType.SP95, 2.1, true));
        fuels4.add(new Fuel(FuelType.E85, 2.3, true));
        fuels4.add(new Fuel(FuelType.Gazole, 1.8, true));
        fuels4.add(new Fuel(FuelType.GPLc, 1.5, true));
        fuels4.add(new Fuel(FuelType.SP98, 1.3, true));
        //Création d'une 1ere liste de services
        ArrayList<String> services1 = new ArrayList<>();
        services1.add("RelaisColis");
        services1.add("StationDeGonflage");
        services1.add("BoutiqueAlimentaire");
        services1.add("LavageAutomatique");
        services1.add("Wifi");
        //Création d'une 2eme liste de services
        ArrayList<String> services2 = new ArrayList<>();
        services2.add("BoutiqueAlimentaire");
        services2.add("RelaisColis");
        services2.add("StationDeGonflage");
        services2.add("LavageAutomatique");
        //Création d'une 3eme liste de services
        ArrayList<String> services3 = new ArrayList<>();
        services3.add("RelaisColis");
        services3.add("StationDeGonflage");
        //Création d'une 3eme liste de services
        ArrayList<String> services4 = new ArrayList<>();
        services4.add("RelaisColis");
        services4.add("StationDeGonflage");
        services4.add("LavageAutomatique");


        //Création d'une premiere station contenant la premiere liste de carburants et de services.
        Station stationA = new Station("rue A", new Coordinate(4, 2), 75, 1, fuels3, services1);
        Station stationB = new Station("rue B", new Coordinate(6, 2), 75, 1, fuels2, services3);
        Station stationC = new Station("rue C", new Coordinate(7, 2), 35, 2, fuels4, services4);
        Station stationD = new Station("rue D", new Coordinate(8, 2), 35, 2, fuels2, services2);
        Station stationE = new Station("rue E", new Coordinate(9, 2), 13, 3, fuels3, services4);
        Station stationF = new Station("rue F", new Coordinate(10, 2), 13, 3, fuels1, services1);
        Station stationG = new Station("rue G", new Coordinate(11, 2), 69, 4, fuels4, services2);
        Station stationH = new Station("rue H", new Coordinate(12, 2), 69, 4, fuels1, services3);


        // Création d'une premiere liste de station.
        ArrayList<Station> listeDeStations1 = new ArrayList<>();
        listeDeStations1.add(stationA);
        listeDeStations1.add(stationB);

        //Création d'une deuxieme liste de station.
        ArrayList<Station> listeDeStations2 = new ArrayList<>();
        listeDeStations2.add(stationC);
        listeDeStations2.add(stationD);

        // Création d'une troisieme liste de station.
        ArrayList<Station> listeDeStations3 = new ArrayList<>();
        listeDeStations3.add(stationE);
        listeDeStations3.add(stationF);

        // Création d'une quatrieme liste de station.
        ArrayList<Station> listeDeStations4 = new ArrayList<>();
        listeDeStations4.add(stationG);
        listeDeStations4.add(stationH);



        // Création de départements
        //premiere liste de departement 
        Departement versailles = new Departement(75,"Versailles", listeDeStations1);
        ArrayList<Departement> listedept1 = new ArrayList<>();
        listedept1.add(versailles);
        //deuxieme liste de departement
        Departement ilEtVilaine = new Departement(35,"Ile-Et-Vilaine", listeDeStations2);
        ArrayList<Departement> listedept2 = new ArrayList<>();
        listedept2.add(ilEtVilaine);
        //troisieme liste de departement
        Departement bouchesDuRhône = new Departement(13,"Bouches-du-Rhône", listeDeStations3);
        ArrayList<Departement> listedept3 = new ArrayList<>();
        listedept3.add(bouchesDuRhône);
        //quatrieme liste de departement
        Departement rhône = new Departement(69,"Rhône", listeDeStations4);
        ArrayList<Departement> listedept4 = new ArrayList<>();
        listedept4.add(rhône);
        

        // Création de régions
        Region region1 = new Region(1, "Ile-de-France", listedept1);          
        Region region2 = new Region(2, "Bretagne", listedept2);
        Region region3 = new Region(3, "Provence-Alpes-Côte d'Azur", listedept3);
        Region region4 = new Region(3, "Auvergne-Rhône-Alpes", listedept4);

        // Liste des régions
        List<Region> regions = new ArrayList<>();
        regions.add(region1);
        regions.add(region2);
        regions.add(region3);
        regions.add(region4);

        // Cas de test 1
        List<Object> userInput1 = List.of("Bretagne", "SP95", "prix minimum");
        Filtre.filtrer(userInput1, regions);                                                                        //pour tester un cas: mettre les autres en commentaires

        // Cas de test 2
        //List<Object> userInput2 = List.of("Provence-Alpes-Côte d'Azur", "Gazole", "prix moyen");
        //Filtre.Filtre(userInput2, regions);

        // Cas de test 3
        //List<Object> userInput3 = List.of("Provence-Alpes-Côte d'Azur", "Gazole", "prix médian");
        //Filtre.Filtre(userInput3, regions);

        // Cas de test 3
        //List<Object> userInput4 = List.of("Provence-Alpes-Côte d'Azur", "Gazole", "nombre de station qui propose mon carburant");
        //Filtre.Filtre(userInput4, regions);
    }
}



/*import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltreTest {
    
    private List<Region> regions;

    @Before
    public void setUp() {
        // Création d'une 1ere liste de carburant
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.E10, 2, true));
        fuels1.add(new Fuel(FuelType.E85, 1.9, true));
        fuels1.add(new Fuel(FuelType.Gazole, 1.2, true));
        fuels1.add(new Fuel(FuelType.GPLc, 1.3, true));
        fuels1.add(new Fuel(FuelType.SP98, 2.6, true));
        
        // Création d'une 2eme liste de carburant
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.E10, 1.4, true));
        fuels2.add(new Fuel(FuelType.SP95, 2.6, true));
        fuels2.add(new Fuel(FuelType.E10, 2.9, true));
        fuels2.add(new Fuel(FuelType.E85, 1.2, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.6, true));
        fuels2.add(new Fuel(FuelType.GPLc, 1.4, true));
        fuels2.add(new Fuel(FuelType.SP98, 1.7, true));
        
        // Création d'une 3eme liste de carburant
        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels3.add(new Fuel(FuelType.E10, 1.2, true));
        fuels3.add(new Fuel(FuelType.SP95, 2.6, true));
        fuels3.add(new Fuel(FuelType.E10, 2.2, true));
        fuels3.add(new Fuel(FuelType.E85, 1.2, true));
        fuels3.add(new Fuel(FuelType.Gazole, 1.3, true));
        fuels3.add(new Fuel(FuelType.GPLc, 2.0, true));
        fuels3.add(new Fuel(FuelType.SP98, 1.9, true));
        
        // Création d'une 4eme liste de carburant
        ArrayList<Fuel> fuels4 = new ArrayList<>();
        fuels4.add(new Fuel(FuelType.E10, 2.9, true));
        fuels4.add(new Fuel(FuelType.SP95, 2.1, true));
        fuels4.add(new Fuel(FuelType.E10, 1.8, true));
        fuels4.add(new Fuel(FuelType.E85, 2.3, true));
        fuels4.add(new Fuel(FuelType.Gazole, 1.8, true));
        fuels4.add(new Fuel(FuelType.GPLc, 1.5, true));
        fuels4.add(new Fuel(FuelType.SP98, 1.3, true));
        
        //Création de listes de services
        ArrayList<String> services1 = new ArrayList<>();
        services1.add("RelaisColis");
        services1.add("StationDeGonflage");
        services1.add("BoutiqueAlimentaire");
        services1.add("LavageAutomatique");
        services1.add("Wifi");

        ArrayList<String> services2 = new ArrayList<>();
        services2.add("BoutiqueAlimentaire");
        services2.add("RelaisColis");
        services2.add("StationDeGonflage");
        services2.add("LavageAutomatique");

        ArrayList<String> services3 = new ArrayList<>();
        services3.add("RelaisColis");
        services3.add("StationDeGonflage");

        ArrayList<String> services4 = new ArrayList<>();
        services4.add("RelaisColis");
        services4.add("StationDeGonflage");
        services4.add("LavageAutomatique");

        //Création de stations
        Station stationA = new Station("rue A", new Coordinate(4, 2), 75, 1, fuels3, services1);
        Station stationB = new Station("rue B", new Coordinate(6, 2), 75, 1, fuels2, services3);
        Station stationC = new Station("rue C", new Coordinate(7, 2), 35, 2, fuels4, services4);
        Station stationD = new Station("rue D", new Coordinate(8, 2), 35, 2, fuels2, services2);
        Station stationE = new Station("rue E", new Coordinate(9, 2), 13, 3, fuels3, services4);
        Station stationF = new Station("rue F", new Coordinate(10, 2), 13, 3, fuels1, services1);
        Station stationG = new Station("rue G", new Coordinate(11, 2), 69, 4, fuels4, services2);
        Station stationH = new Station("rue H", new Coordinate(12, 2), 69, 4, fuels1, services3);

        // Création de listes de stations
        ArrayList<Station> listeDeStations1 = new ArrayList<>();
        listeDeStations1.add(stationA);
        listeDeStations1.add(stationB);

        ArrayList<Station> listeDeStations2 = new ArrayList<>();
        listeDeStations2.add(stationC);
        listeDeStations2.add(stationD);

        ArrayList<Station> listeDeStations3 = new ArrayList<>();
        listeDeStations3.add(stationE);
        listeDeStations3.add(stationF);

        ArrayList<Station> listeDeStations4 = new ArrayList<>();
        listeDeStations4.add(stationG);
        listeDeStations4.add(stationH);

        // Création de départements
        Departement versailles = new Departement(75, "Versailles", listeDeStations1);
        ArrayList<Departement> listedept1 = new ArrayList<>();
        listedept1.add(versailles);

        Departement ilEtVilaine = new Departement(35, "Ile-Et-Vilaine", listeDeStations2);
        ArrayList<Departement> listedept2 = new ArrayList<>();
        listedept2.add(ilEtVilaine);

        Departement bouchesDuRhône = new Departement(13, "Bouches-du-Rhône", listeDeStations3);
        ArrayList<Departement> listedept3 = new ArrayList<>();
        listedept3.add(bouchesDuRhône);

        Departement rhône = new Departement(69, "Rhône", listeDeStations4);
        ArrayList<Departement> listedept4 = new ArrayList<>();
        listedept4.add(rhône);

        // Création de régions
        Region region1 = new Region(1, "Ile-de-France", listedept1);
        Region region2 = new Region(2, "Bretagne", listedept2);
        Region region3 = new Region(3, "Provence-Alpes-Côte d'Azur", listedept3);
        Region region4 = new Region(4, "Auvergne-Rhône-Alpes", listedept4);

        // Liste des régions
        regions = new ArrayList<>();
        regions.add(region1);
        regions.add(region2);
        regions.add(region3);
        regions.add(region4);
    }

    @Test
    public void testFiltrePrixMinimum() {
        List<Object> userInput = List.of("Ile-de-France", "SP95", "prix minimum");
        Station result = Filtre.Filtre(userInput, regions);
        assertEquals("rue A", result.getAdresse()); // Remplacez par l'adresse correcte attendue
    }

    @Test
    public void testFiltrePrixMoyen() {
        List<Object> userInput = List.of("Bretagne", "E10", "prix moyen");
        Station result = Filtre.Filtre(userInput, regions);
        assertEquals("rue C", result.getAdresse()); // Remplacez par l'adresse correcte attendue
    }

}

 */
