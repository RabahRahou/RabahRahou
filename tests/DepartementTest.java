import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DepartementTest {

    @Test
    public void testDepartementConstructor() {
        // Préparer les données
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 75, 1, new ArrayList<>(), new ArrayList<>());
        Station station2 = new Station("Adresse 2", new Coordinate(80, 4), 69, 1, new ArrayList<>(), new ArrayList<>());

        // Créer un département avec des stations en tant que varargs
        Departement departement = new Departement(75, "Paris", station1, station2); //la difference entre les deux constructeurs est ici !!

        // Vérifier les valeurs
        assertEquals(75, departement.getCodeDep());
        assertEquals("Paris", departement.getNom());
        assertEquals(2, departement.getStations().size());
        assertTrue(departement.getStations().contains(station1));
        assertTrue(departement.getStations().contains(station2));
    }

    @Test
    public void testDepartementConstructorArrayList() {
        // Préparer les données
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 75, 1, new ArrayList<>(), new ArrayList<>());
        Station station2 = new Station("Adresse 2", new Coordinate(80, 4), 69, 1, new ArrayList<>(), new ArrayList<>());
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);

        // Créer un département avec des stations en tant qu'ArrayList
        Departement departement = new Departement(75, "Paris", stations); //ici on met directement la liste

        // Vérifier les valeurs
        assertEquals(75, departement.getCodeDep());
        assertEquals("Paris", departement.getNom());
        assertEquals(2, departement.getStations().size());
        assertTrue(departement.getStations().contains(station1));
        assertTrue(departement.getStations().contains(station2));
    }

    @Test
    public void testGetPrixMoyen() {
        // Créer des objets Station pour les tests

        ArrayList<Fuel> gazfamily = new ArrayList<Fuel>();
        gazfamily.add(new Fuel(FuelType.SP95, 2.0, true));
        gazfamily.add(new Fuel(FuelType.Gazole, 1.0, true));
        

        ArrayList<Fuel> gazfamily2 = new ArrayList<Fuel>();
        gazfamily.add(new Fuel(FuelType.SP95, 6, true));
        gazfamily.add(new Fuel(FuelType.Gazole, 5, true));
        

        //1ere liste de stations
        ArrayList<Station> gazStations = new ArrayList<>();
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 35, 1, gazfamily);
        Station station2 = new Station("Adresse 2",new Coordinate(80, 4),  35,2, gazfamily2);
        gazStations.add(station1);
        gazStations.add(station2);

        Departement ileEtVilaine = new Departement(35, "Ile et Vilaine", gazStations);

        // Tester getPrixMoyen pour le diesel
        assertEquals(ileEtVilaine.getPrixMoyen(FuelType.Gazole), 3, 0.001);
        assertEquals(ileEtVilaine.getPrixMoyen(FuelType.SP95), 4.0, 0.001);

        // Tester getPrixMoyen pour l'essence

    }

    @Test
    public void testTriInsertion(){

        // Création d'une instance de la classe Departement
        Departement departement = new Departement(35, "Morbihan");

        // Création d'une liste de prix non triée
        ArrayList<Double> prixNonTries = new ArrayList<>();
        prixNonTries.add(3.5);
        prixNonTries.add(1.2);
        prixNonTries.add(2.8);
        prixNonTries.add(0.9);
        
        // Appel de la méthode de tri
        departement.triInsertion(prixNonTries);

        // Création d'une liste de prix triés
        ArrayList<Double> prixTries = new ArrayList<>();
        prixTries.add(0.9);
        prixTries.add(1.2);
        prixTries.add(2.8);
        prixTries.add(3.5);
        
        // Vérification que les deux listes sont identiques après le tri
        assertEquals(prixTries, prixNonTries);
    }

    @Test
    public void testGetPrixMedian() {
        // Création d'une 1ère liste de carburants
        ArrayList<Fuel> gazfamily1 = new ArrayList<Fuel>();
        gazfamily1.add(new Fuel(FuelType.SP95, 3.0, true));
        gazfamily1.add(new Fuel(FuelType.Gazole, 1.0, true));
        gazfamily1.add(new Fuel(FuelType.E10, 2.0, true));
        gazfamily1.add(new Fuel(FuelType.E85, 1.0, true));

        // Création d'une 2ème liste de carburants
        ArrayList<Fuel> gazfamily2 = new ArrayList<Fuel>();
        gazfamily2.add(new Fuel(FuelType.SP95, 4.0, true));
        gazfamily2.add(new Fuel(FuelType.Gazole, 2.0, true));
        gazfamily1.add(new Fuel(FuelType.E10, 5.0, true));
        gazfamily1.add(new Fuel(FuelType.E85, 3.0, true));

        //Création d'une 2ème liste de carburants
        ArrayList<Fuel> gazfamily3 = new ArrayList<Fuel>();
        gazfamily2.add(new Fuel(FuelType.SP95, 1.0, true));
        gazfamily2.add(new Fuel(FuelType.Gazole, 3.0, true));
        

        // Création d'une 1ère station qui prendra la première liste de carburants
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 56, 1, gazfamily1);

        // Création d'une 2ème station qui prendra la deuxième liste de carburants
        Station station2 = new Station("Adresse 2", new Coordinate(120, 60), 56, 2, gazfamily2);

        // Création d'une 3ème station qui prendra la troisième liste de carburants
        Station station3 = new Station("Adresse 3", new Coordinate(120, 60), 56, 3, gazfamily3);


        // Création d'une liste de stations contenant les deux stations créées
        ArrayList<Station> Total = new ArrayList<>();
        Total.add(station1);
        Total.add(station2);
        Total.add(station3);

        // Création d'un département avec la liste de stations
        Departement morbihan = new Departement(35, "Morbihan", Total);

        // Tester getPrixMedian pour SP95
        assertEquals(morbihan.getPrixMedian(FuelType.SP95), 3.0, 0.001);

        // Tester getPrixMedian pour Gazole
        assertEquals(morbihan.getPrixMedian(FuelType.Gazole), 2.0, 0.001);

        // Tester getPrixMedian pour E10 (+test de liste pair)
        assertEquals(morbihan.getPrixMedian(FuelType.E10), 3.5, 0.001);

        // Tester getPrixMedian pour E85 (+test de liste pair)
        assertEquals(morbihan.getPrixMedian(FuelType.E85), 2.0, 0.001);
    }

    @Test
    public void testGetMinimum() {
        // Création d'une 1ère liste de carburants
        ArrayList<Fuel> gazfamily1 = new ArrayList<Fuel>();
        gazfamily1.add(new Fuel(FuelType.SP95, 3.0, true));
        gazfamily1.add(new Fuel(FuelType.Gazole, 1.0, true));

        // Création d'une 2ème liste de carburants
        ArrayList<Fuel> gazfamily2 = new ArrayList<Fuel>();
        gazfamily2.add(new Fuel(FuelType.SP95, 4.0, true));
        gazfamily2.add(new Fuel(FuelType.Gazole, 2.0, true));

        // Création d'une 1ère station qui prendra la première liste de carburants
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 56, 1, gazfamily1);

        // Création d'une 2ème station qui prendra la deuxième liste de carburants
        Station station2 = new Station("Adresse 2", new Coordinate(120, 60), 56, 2, gazfamily2);

        // Création d'une liste de stations contenant les deux stations créées
        ArrayList<Station> Total = new ArrayList<>();
        Total.add(station1);
        Total.add(station2);

        // Création d'un département avec la liste de stations
        Departement morbihan = new Departement(35, "Morbihan", Total);

        assertEquals(morbihan.getMinimum(FuelType.SP95), 3.0, 0.001);
        assertEquals(morbihan.getMinimum(FuelType.Gazole), 1.0, 0.001);
    }

    @Test
    public void testGetMaximum(){
        // Création d'une 1ère liste de carburants
        ArrayList<Fuel> gazfamily1 = new ArrayList<Fuel>();
        gazfamily1.add(new Fuel(FuelType.SP95, 3.0, true));
        gazfamily1.add(new Fuel(FuelType.Gazole, 1.0, true));

        // Création d'une 2ème liste de carburants
        ArrayList<Fuel> gazfamily2 = new ArrayList<Fuel>();
        gazfamily2.add(new Fuel(FuelType.SP95, 4.0, true));
        gazfamily2.add(new Fuel(FuelType.Gazole, 2.0, true));

        // Création d'une 1ère station qui prendra la première liste de carburants
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 56, 1, gazfamily1);

        // Création d'une 2ème station qui prendra la deuxième liste de carburants
        Station station2 = new Station("Adresse 2", new Coordinate(120, 60), 56, 2, gazfamily2);

        // Création d'une liste de stations contenant les deux stations créées
        ArrayList<Station> Total = new ArrayList<>();
        Total.add(station1);
        Total.add(station2);

        // Création d'un département avec la liste de stations
        Departement morbihan = new Departement(35, "Morbihan", Total);

        assertEquals(morbihan.getMaximum(FuelType.SP95), 4.0, 0.001);
        assertEquals(morbihan.getMaximum(FuelType.Gazole), 2.0, 0.001);
    }

    @Test
    public void testGetStation(){
        ArrayList<Fuel> gazfamily = new ArrayList<Fuel>();
        gazfamily.add(new Fuel(FuelType.SP95, 2.0, true));
        gazfamily.add(new Fuel(FuelType.Gazole, 1.0, true));
        ArrayList<Station> gazStations = new ArrayList<>();
        gazStations.add(new Station("adresse 1",new Coordinate(120, 60), 35,9, gazfamily, new ArrayList<>()));
        gazStations.add(new Station("adresse 2",new Coordinate(120, 60), 35,9, gazfamily, new ArrayList<>()));
        Departement ileEtVilaine = new Departement(35, "Ile et Vilaine", gazStations);
        assertEquals(ileEtVilaine.getStations(),gazStations);
    }

    @Test
    public void testGetNom() {
        // Création d'un département avec un nom spécifique
        Departement departement = new Departement(35, "Ille-et-Vilaine");

        // Vérification que le nom retourné correspond au nom spécifié lors de la création
        assertEquals("Ille-et-Vilaine", departement.getNom());
    }

    @Test
    public void testGetCodeDep() {
        // Création d'un département avec un code départemental spécifique
        Departement departement = new Departement(35, "Ille-et-Vilaine");

        // Vérification que le code départemental retourné correspond au code spécifié lors de la création
        assertEquals(35, departement.getCodeDep());
    }

    
    @Test
    public void testGetStationSize() {
        // Création d'une liste de stations
        ArrayList<Station> stations = new ArrayList<>();
        Station station1 = new Station("Adresse 1", new Coordinate(120, 60), 1, 1, new ArrayList<>());
        Station station2 = new Station("Adresse 2", new Coordinate(10, 60), 1, 2, new ArrayList<>());
        stations.add(station1);
        stations.add(station2);
        

        // Création d'un département avec la liste de stations
        Departement departement = new Departement(35, "Ille-et-Vilaine", stations);

        // Vérification que la taille de la liste de stations retournée correspond à la taille de la liste spécifiée lors de la création
        assertEquals(2, departement.getStationSize(), 0.001); // Utilisation d'une marge d'erreur de 0.001 pour prendre en compte les erreurs d'arrondi
    }


    @Test
    public void testGetStationNbr() {
        // Création d'une liste de stations avec des carburants spécifiques
        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.Gazole, 1.0, true));
        stations.add(new Station( "adresse 1",new Coordinate(120, 60), 35,53, fuels1));

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.6, true));
        fuels2.add(new Fuel(FuelType.SP98, 1.7, true));
        stations.add(new Station("adresse2", new Coordinate(10, 60), 35, 53, fuels2));

        // Création d'un département avec les stations spécifiées
        Departement departement = new Departement(35, "Morbihan", stations);

        // Vérification du nombre de stations distribuant le carburant SP95 dans le département
        assertEquals(2, departement.getStationNbr(FuelType.SP95));

        // Vérification du nombre de stations distribuant le carburant Gazole dans le département
        assertEquals(1, departement.getStationNbr(FuelType.Gazole));

        // Vérification du nombre de stations distribuant le carburant SP98 dans le département
        assertEquals(1, departement.getStationNbr(FuelType.SP98));
    }

    @Test 
    public void testgetNbrStationAyantCarb(){
        // Création d'une liste de stations avec des carburants spécifiques
        ArrayList<Station> stations = new ArrayList<>();
        ArrayList<Fuel> fuels1 = new ArrayList<>();
        fuels1.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels1.add(new Fuel(FuelType.Gazole, 1.0, true));
        stations.add(new Station("adresse 1",new Coordinate(120, 60), 35, 53, fuels1));

        ArrayList<Fuel> fuels2 = new ArrayList<>();
        fuels2.add(new Fuel(FuelType.SP95, 1.6, true));
        fuels2.add(new Fuel(FuelType.SP98, 1.7, true));
        stations.add(new Station("adresse 2",new Coordinate(120, 60), 35, 53, fuels2));

        // Création d'un département avec les stations spécifiées
        Departement departement = new Departement(35, "Morbihan", stations);

        // Vérification du nombre de stations distribuant le carburant SP98 dans le département
        assertEquals(departement.getNbrStationAyantCarb(FuelType.SP95),2);
        assertEquals(departement.getNbrStationAyantCarb(FuelType.Gazole),1);
        assertEquals(departement.getNbrStationAyantCarb(FuelType.SP98),1);

    }

    @Test
    public void testgetNbrStationAyantCarb_ListeStationsVide(){
        ArrayList<Station> stations = new ArrayList<>();
        Departement departement = new Departement(35, "Morbihan", stations);

        // Vérification du nombre de stations distribuant le carburant SP98 dans le département vide
        assertEquals(departement.getNbrStationAyantCarb(FuelType.SP95), 0);
    }

    @Test
    public void testgetNbrStationAyantCarb_CarburantsNonDisponibles(){
        ArrayList<Station> stations = new ArrayList<>();
        // Aucun carburant n'est disponible dans les stations
        stations.add(new Station("adresse 1",new Coordinate(120, 60), 35, 53, new ArrayList<>()));

        Departement departement = new Departement(35, "Morbihan", stations);

        // Vérification du nombre de stations distribuant le carburant SP95 dans les stations sans carburants
        assertEquals(departement.getNbrStationAyantCarb(FuelType.SP95), 0);
    }

    @Test
    public void testgetNbrStationAyantCarb_TypeCarburantNonPrisEnCharge(){
        ArrayList<Station> stations = new ArrayList<>();
        // Les stations ne prennent en charge que le SP95 et le Gazole
        ArrayList<Fuel> fuels = new ArrayList<>();
        fuels.add(new Fuel(FuelType.SP95, 1.5, true));
        fuels.add(new Fuel(FuelType.Gazole, 1.0, true));
        stations.add(new Station("adresse 1",new Coordinate(120, 60), 35, 53, fuels));

        Departement departement = new Departement(35, "Morbihan", stations);

        // Vérification du nombre de stations distribuant le carburant SP98 dans les stations
        assertEquals(departement.getNbrStationAyantCarb(FuelType.SP98), 0);
    }

    @Test
    public void testgetNbrStationAyantServ(){
        // Création d'une liste de services spécifiques
        List<String> listServices = new ArrayList<>();
        listServices.add("RelaisColis");
        listServices.add("BoutiqueAlimentaire");
        listServices.add("VenteAdditifsCarburants");
        
        //Création d'une 2eme liste de services spécifiques
        List<String> listServices2 = new ArrayList<>();
        listServices2.add("VenteAdditifsCarburants");
        listServices2.add("BoutiqueAlimentaire");
        listServices2.add("VenteDeGazDomestique");

        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station("adresse 1", new Coordinate(120, 60), 35, 53, new ArrayList<>(), listServices));
        stations.add(new Station("adresse 2", new Coordinate(10, 60), 35, 54, new ArrayList<>(), listServices2));

        // Création d'un département avec les stations spécifiées
        Departement departement = new Departement(35, "IleEtVilaine", stations);

        // Vérification du nombre de stations offrant le service Boutique alimentaire
        assertEquals(2, departement.getNbrStationAyantServices("BoutiqueAlimentaire"));
        // Vérification du nombre de stations offrant le service de vente de gaz domestique dans le département
        assertEquals(1, departement.getNbrStationAyantServices("VenteDeGazDomestique"));
    }

    @Test
    public void testgetNbrStationAyantServ_ListeStationsVide(){
        ArrayList<Station> stations = new ArrayList<>();
        Departement departement = new Departement(35, "IleEtVilaine", stations);

        // Vérification du nombre de stations offrant le service de vente de gaz domestique dans le département vide
        assertEquals(departement.getNbrStationAyantServices("VenteAdditifsCarburants"), 0);
    }

    @Test
    public void testgetNbrStationAyantServ_TypeServiceNonPrisEnCharge(){
        ArrayList<Station> stations = new ArrayList<>();
        // Les stations ne proposent que la vente d'additifs carburants et de gaz domestique
        List<String> listServices = new ArrayList<>();
        listServices.add("VenteDeGazDomestique");
        listServices.add("VenteAdditifsCarburants");

        stations.add(new Station("adresse 1", new Coordinate(120, 60), 35, 53, new ArrayList<>(), listServices));

        Departement departement = new Departement(35, "Morbihan", stations);

        // Vérification du nombre de stations offrant le service de relais colis dans les stations
        assertEquals(departement.getNbrStationAyantServices("RelaisColis"), 0);
    }

}