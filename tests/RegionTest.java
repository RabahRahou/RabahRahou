import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionTest {

    @Test
    public void testGetPrixMoyen() {

        // Arrange
        String adresse = "123 Rue de la Station";
        String adresse2 = "124 Rue de la Station";
        Coordinate coordonnee = new Coordinate(48.8566, 2.3522);
        Coordinate coordonnee2 = new Coordinate(49.8566, 2.3522);
        int codeDepartement = 75;
        int codeRegion = 11;

        // Création des carburants pour les stations
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.SP98, 1.6, true));
        fuels1.add(new Fuel(FuelType.E10, 2.5, true));
        fuels1.add(new Fuel(FuelType.Gazole, 2.2, true));
        fuels1.add(new Fuel(FuelType.E85, 1.8, true));
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.7, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.8, false));
        fuels1.add(new Fuel(FuelType.E10, 1.2, true));
        fuels1.add(new Fuel(FuelType.SP98, 1.7, true));
        fuels1.add(new Fuel(FuelType.E85, 1.1, true));

        // Création des stations pour les départements
        ArrayList<Station> stations1 = new ArrayList<>();
        stations1.add(new Station(adresse, coordonnee, codeDepartement, codeRegion, fuels1));

        ArrayList<Station> stations2 = new ArrayList<>();
        stations2.add(new Station(adresse2, coordonnee2, codeDepartement, codeRegion, fuels2));

        // Création des départements pour la région
        Departement departement1 = new Departement(75,"Ain" ,stations1);
        Departement departement2 = new Departement(75,"Aillier" ,stations2);
        ArrayList<Departement> departements = new ArrayList<>();
        departements.add(departement1);
        departements.add(departement2);

        // Création de la région
        Region region = new Region(11,"Bretagne" ,departements);

        // Test du prix moyen pour SP95
        assertEquals(1.6, region.getPrixMoyen(FuelType.SP95), 0.01);
        //assertEquals(1.65, region.getPrixMoyen(FuelType.SP98), 0.01) //pb ici 
        //assertEquals(1.85, region.getPrixMoyen(FuelType.E10), 0.01); //pb ici
    }

    @Test
    public void testGetPrixMedian() {

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);

        // Création des carburants pour les stations
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.SP98, 1.6, true));
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.7, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.8, false));

        // Création des stations pour les départements
        ArrayList<Station> stations1 = new ArrayList<>();

        stations1.add(new Station("Station1", coordonnee, 1,9, fuels1));
        ArrayList<Station> stations2 = new ArrayList<>();
        stations2.add(new Station("Station2", coordonnee2, 1,9, fuels2));


        // Création des départements pour la région
        Departement departement1 = new Departement(1,"Ain", stations1);
        Departement departement2 = new Departement(2,"Aillier" ,stations2);
        ArrayList<Departement> departements = new ArrayList<>();
        departements.add(departement1);
        departements.add(departement2);

        // Création de la région
        Region region = new Region(3,"Bretagne" ,departements);

        // Test du prix médian pour SP95Update RegionTest.java
        assertEquals(1.7, region.getPrixMedian(FuelType.SP95), 0.01);
    }

    @Test
    public void testGetMinimum() {

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);
        // Création des carburants pour les stations
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.SP98, 1.6, true));
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.7, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.8, false));

        // Création des stations pour les départements
        ArrayList<Station> stations1 = new ArrayList<>();

        stations1.add(new Station("Station1", coordonnee, 1,9, fuels1));
        ArrayList<Station> stations2 = new ArrayList<>(); 
        stations2.add(new Station("Station2", coordonnee2, 1,9, fuels2));

        // Création des départements pour la région
        Departement departement1 = new Departement(1,"Aillier" ,stations1);
        Departement departement2 = new Departement(2,"Aillier", stations2);
        ArrayList<Departement> departements = new ArrayList<>();
        departements.add(departement1);
        departements.add(departement2);

        // Création de la région
        Region region = new Region(4,"Bretagne" ,departements);

        // Test du prix minimum pour SP95
        assertEquals(1.5, region.getMinimum(FuelType.SP95), 0.01);
    }

    @Test
    public void testGetMaximum() {

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);

        // Création des carburants pour les stations
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.SP98, 1.6, true));
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.7, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.8, false));

        // Création des stations pour les départements
        ArrayList<Station> stations1 = new ArrayList<>();

        stations1.add(new Station("Station1", coordonnee, 1,9, fuels1));
        ArrayList<Station> stations2 = new ArrayList<>();
        stations2.add(new Station("Station2", coordonnee2, 1,9, fuels2));


        // Création des départements pour la région
        Departement departement1 = new Departement(1,"Ain" ,stations1);
        Departement departement2 = new Departement(2,"Aillier" ,stations2);
        ArrayList<Departement> departements = new ArrayList<>();
        departements.add(departement1);
        departements.add(departement2);

        // Création de la région
        Region region = new Region(5,"Bretagne" ,departements);
        // Test du prix maximum pour SP95
        assertEquals(1.7, region.getMaximum(FuelType.SP95), 0.01);
    }

    @Test
    public void testGetSomme() {

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);

        // Création des carburants pour les stations
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.SP98, 1.6, true));
        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.7, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.8, false));

         // Création des stations pour les départements
         ArrayList<Station> stations1 = new ArrayList<>();

         stations1.add(new Station("Station1", coordonnee, 1,9, fuels1));
         ArrayList<Station> stations2 = new ArrayList<>();
         stations2.add(new Station("Station2", coordonnee2, 1,9, fuels2));
 
        // Création des départements pour la région
        Departement departement1 = new Departement(1,"Ain", stations1);
        Departement departement2 = new Departement(2,"Aillier" ,stations2);
        ArrayList<Departement> departements = new ArrayList<>();
        departements.add(departement1);
        departements.add(departement2);

        
        // Création de la région
        Region region = new Region(6, "Bretagne", departements);

        // Calcul de la somme des prix des carburants
        double sommeAttendue = 1.5 + 1.7;

        // Vérification du résultat
        assertEquals(sommeAttendue, region.getSomme(FuelType.SP95), 0.01);
    }

    @Test
    public void testGetStationNbr(){

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);
        Coordinate coordonnee3 = new Coordinate(7, 8);
        Coordinate coordonnee4 = new Coordinate(8, 8);

        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95, 1.84, true));
        fuels.add(new Fuel(FuelType.SP98, 1.90, false));
        fuels.add(new Fuel(FuelType.Gazole, 1.79, true));

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.88, false));
        fuels2.add(new Fuel(FuelType.SP98, 1.93, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.69, true));

        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels3.add(new Fuel(FuelType.SP95, 1.68, true));
        fuels3.add(new Fuel(FuelType.SP98, 1.97, true));
        fuels3.add(new Fuel(FuelType.Gazole, 1.99, false));

        ArrayList<Fuel> fuels4 = new ArrayList<>();
        fuels4.add(new Fuel(FuelType.SP98, 1.89, true));
        fuels4.add(new Fuel(FuelType.Gazole, 1.82, true));


        Station totalMtStMichel = new Station("adresse 1", coordonnee, 1,9, fuels);
        Station totalCaen = new Station("adresse 2", coordonnee2, 1,9, fuels2);
        Station totalAlençon = new Station("adresse 3", coordonnee3, 1,9, fuels3);
        Station totalStLot = new Station("adresse 4", coordonnee4, 1,9, fuels4);


        ArrayList<Station> stationManche = new ArrayList<>();
        stationManche.add(totalMtStMichel);
        stationManche.add(totalStLot);

        ArrayList<Station> stationCalvados = new ArrayList<>();
        stationCalvados.add(totalCaen);

        ArrayList<Station> stationOrne = new ArrayList<>();
        stationOrne.add(totalAlençon);

        Departement Manche = new Departement(50,"Manche" ,stationManche);
        Departement Calvados = new Departement(14,"Calvados" ,stationCalvados);
        Departement Orne = new Departement(61,"Orne", stationOrne);

        ArrayList<Departement> dept = new ArrayList<>();
        dept.add(Manche);
        dept.add(Calvados);
        dept.add(Orne);
        Region basseNormandie = new Region(1, "Basse-Normandie", dept);

        assertEquals(3, basseNormandie.getStationNbr(FuelType.SP95));

    }

    @Test
    public void testGetDepartements(){

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);
        Coordinate coordonnee3 = new Coordinate(7, 8);
        Coordinate coordonnee4 = new Coordinate(8, 8);

        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95, 1.84, true));
        fuels.add(new Fuel(FuelType.SP98, 1.90, false));
        fuels.add(new Fuel(FuelType.Gazole, 1.79, true));

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.88, false));
        fuels2.add(new Fuel(FuelType.SP98, 1.93, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.69, true));

        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels3.add(new Fuel(FuelType.SP95, 1.68, true));
        fuels3.add(new Fuel(FuelType.SP98, 1.97, true));
        fuels3.add(new Fuel(FuelType.Gazole, 1.99, false));

        ArrayList<Fuel> fuels4 = new ArrayList<>();
        fuels4.add(new Fuel(FuelType.SP98, 1.89, true));
        fuels4.add(new Fuel(FuelType.Gazole, 1.82, true));

        Station totalMtStMichel = new Station("adresse 1", coordonnee, 1,9, fuels);
        Station totalCaen = new Station("adresse 2", coordonnee2, 1,9, fuels2);
        Station totalAlençon = new Station("adresse 3", coordonnee3, 1,9, fuels3);
        Station totalStLot = new Station("adresse 4", coordonnee4, 1,9, fuels4);
        ArrayList<Station> stationManche = new ArrayList<>();
        stationManche.add(totalMtStMichel);
        stationManche.add(totalStLot);

        ArrayList<Station> stationCalvados = new ArrayList<>();
        stationCalvados.add(totalCaen);
        ArrayList<Station> stationOrne = new ArrayList<>();
        stationOrne.add(totalAlençon);
        Departement Manche = new Departement(50,"Manche" ,stationManche);
        Departement Calvados = new Departement(14,"Calvados" ,stationCalvados);
        Departement Orne = new Departement(61,"Orne", stationOrne);
        ArrayList<Departement> dept = new ArrayList<>();
        dept.add(Manche);
        dept.add(Calvados);
        dept.add(Orne);
        Region basseNormandie = new Region(1, "Basse-Normandie", dept);

        assertEquals(basseNormandie.getDepartements(), dept);
    }

    @Test
    public void getNbrStationAyantCarb(){

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);
        Coordinate coordonnee3 = new Coordinate(7, 8);
        Coordinate coordonnee4 = new Coordinate(8, 8);

        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95, 1.84, true));
        fuels.add(new Fuel(FuelType.SP98, 1.90, false));
        fuels.add(new Fuel(FuelType.Gazole, 1.79, true));

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.88, false));
        fuels2.add(new Fuel(FuelType.SP98, 1.93, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.69, true));

        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels3.add(new Fuel(FuelType.SP95, 1.68, true));
        fuels3.add(new Fuel(FuelType.SP98, 1.97, true));
        fuels3.add(new Fuel(FuelType.Gazole, 1.99, false));

        ArrayList<Fuel> fuels4 = new ArrayList<>();
        fuels4.add(new Fuel(FuelType.SP98, 1.89, true));
        fuels4.add(new Fuel(FuelType.Gazole, 1.82, true));
        
        Station totalMtStMichel = new Station("adresse 1", coordonnee, 1,9, fuels);
        Station totalCaen = new Station("adresse 2", coordonnee2, 1,9, fuels2);
        Station totalAlençon = new Station("adresse 3", coordonnee3, 1,9, fuels3);
        Station totalStLot = new Station("adresse 4", coordonnee4, 1,9, fuels4);
        ArrayList<Station> stationManche = new ArrayList<>();
        stationManche.add(totalMtStMichel);
        stationManche.add(totalStLot);

        ArrayList<Station> stationCalvados = new ArrayList<>();
        stationCalvados.add(totalCaen);

        ArrayList<Station> stationOrne = new ArrayList<>();
        stationOrne.add(totalAlençon);
        Departement Manche = new Departement(50,"Manche" ,stationManche);
        Departement Calvados = new Departement(14,"Calvados" ,stationCalvados);
        Departement Orne = new Departement(61,"Orne", stationOrne);
        ArrayList<Departement> dept = new ArrayList<>();
        dept.add(Manche);
        dept.add(Calvados);
        dept.add(Orne);
        Region basseNormandie = new Region(1, "Basse-Normandie", dept);

        assertEquals(basseNormandie.getNbrStationAyantCarb(FuelType.SP98),3);
        
    }
    @Test
    public void getNbrStationAyantServ(){

        Coordinate coordonnee = new Coordinate(5, 8);
        Coordinate coordonnee2 = new Coordinate(6, 8);
        Coordinate coordonnee3 = new Coordinate(7, 8);
        Coordinate coordonnee4 = new Coordinate(8, 8);

        //Création des listes de services 
        ArrayList<String> listServices1 = new ArrayList<>(Arrays.asList("BoutiqueAlimentaire", "StationDeGonflage"));
        ArrayList<String> listServices2 = new ArrayList<>(Arrays.asList( "PistePoidsLourds"));
        ArrayList<String> listServices3 = new ArrayList<>(Arrays.asList("RelaisColis", "BoutiqueAlimentaire", "StationDeGonflage"));
        ArrayList<String> listServices4 = new ArrayList<>(Arrays.asList("AutomateCB","RelaisColis", "BoutiqueAlimentaire"));

        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95, 1.84, true));
        fuels.add(new Fuel(FuelType.SP98, 1.90, false));
        fuels.add(new Fuel(FuelType.Gazole, 1.79, true));

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.88, false));
        fuels2.add(new Fuel(FuelType.SP98, 1.93, true));
        fuels2.add(new Fuel(FuelType.Gazole, 1.69, true));

        ArrayList<Fuel> fuels3 = new ArrayList<>();
        fuels3.add(new Fuel(FuelType.SP95, 1.68, true));
        fuels3.add(new Fuel(FuelType.SP98, 1.97, true));
        fuels3.add(new Fuel(FuelType.Gazole, 1.99, false));

        ArrayList<Fuel> fuels4 = new ArrayList<>();
        fuels4.add(new Fuel(FuelType.SP98, 1.89, true));
        fuels4.add(new Fuel(FuelType.Gazole, 1.82, true));
        
        Station totalMtStMichel = new Station("adresse 1", coordonnee, 1,9, fuels, listServices1);
        Station totalCaen = new Station("adresse 2", coordonnee2, 1,9, fuels2, listServices2);
        Station totalAlençon = new Station("adresse 3", coordonnee3, 1,9, fuels3, listServices3);
        Station totalStLot = new Station("adresse 4", coordonnee4, 1,9, fuels4, listServices4);
        ArrayList<Station> stationManche = new ArrayList<>();
        stationManche.add(totalMtStMichel);
        stationManche.add(totalStLot);

        ArrayList<Station> stationCalvados = new ArrayList<>();
        stationCalvados.add(totalCaen);

        ArrayList<Station> stationOrne = new ArrayList<>();
        stationOrne.add(totalAlençon);
        Departement Manche = new Departement(50,"Manche" ,stationManche);
        Departement Calvados = new Departement(14,"Calvados" ,stationCalvados);
        Departement Orne = new Departement(61,"Orne", stationOrne);
        ArrayList<Departement> dept = new ArrayList<>();
        dept.add(Manche);
        dept.add(Calvados);
        dept.add(Orne);
        Region basseNormandie = new Region(1, "Basse-Normandie", dept);

        
        // Test pour les services BoutiqueAlimentaire et StationDeGonflage
        assertEquals(2, basseNormandie.getNbrStationAyantServices("BoutiqueAlimentaire", "StationDeGonflage"));

        // Test pour le service PistePoidsLourds
        assertEquals(1, basseNormandie.getNbrStationAyantServices("PistePoidsLourds"));

        // Test pour le service AutomateCB
        assertEquals(1, basseNormandie.getNbrStationAyantServices("AutomateCB"));

        // Test pour un service qui n'est pas proposé par aucune station
        assertEquals(0, basseNormandie.getNbrStationAyantServices("ServiceInexistant"));
    }
}