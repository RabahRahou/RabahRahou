import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.FileWriter;

/**
 * La classe ParseJSON est responsable de l'analyse des données JSON de carburant
 * et de leur conversion en objets de type Station, Département et Région.
 */
public class ParseJSON {

    private static ArrayList<Station> Stations = new ArrayList<>();
    public static ArrayList<Region> Pays = new ArrayList<>();

    /**
     * Analyse le fichier JSON spécifié et extrait les informations de station de carburant.
     *
     * @param filePath le chemin vers le fichier JSON à analyser
     */
    public static void ParserJSON(String filePath) {
        // Chemin vers votre fichier JSON local

        // Création de l'objet ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> servicesList = new ArrayList<String>();
        ArrayList<Fuel> fuelList = new ArrayList<>();

        try {
            // Lecture du fichier JSON et parsing
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            // Vérifier si le fichier JSON contient des éléments
            if (rootNode.isArray() && rootNode.size() > 0) {
                for (JsonNode Element : rootNode) {

                    servicesList.clear();
                    fuelList.clear();

                    // Récupérer les éléments
                    String adresse = Element.get("adresse").asText();
                    int code_departement = Element.get("code_departement").asInt();
                    String nom_departement = Element.get("departement").asText();
                    int code_region = Element.get("code_region").asInt();
                    String nom_region = Element.get("region").asText();
                    Coordinate coordonnee;

                    // Récupérer les prix
                    Double prix_Gazole = Element.get("gazole_prix").asDouble();
                    Double prix_sp95 = Element.get("sp95_prix").asDouble();
                    Double prix_sp98 = Element.get("sp98_prix").asDouble();
                    Double prix_E10 = Element.get("e10_prix").asDouble();
                    Double prix_E85 = Element.get("e85_prix").asDouble();
                    Double prix_GPLC = Element.get("gplc_prix").asDouble();

                    //Récupération les coordonées : 
                    JsonNode geomNode = Element.get("geom");
                    double lon = geomNode.get("lon").asDouble();
                    double lat = geomNode.get("lat").asDouble();
                    coordonnee = new Coordinate(lat,lon);

                    //Récupération des fuels dispos : 
                    if (prix_Gazole != 0.0) {
                        fuelList.add(new Fuel(FuelType.Gazole, prix_Gazole, true));
                    } else {
                        fuelList.add(new Fuel(FuelType.Gazole, prix_Gazole, false));
                    }
                    if (prix_sp95 != 0.0) {
                        fuelList.add(new Fuel(FuelType.SP95, prix_sp95, true));
                    } else {
                        fuelList.add(new Fuel(FuelType.SP95, prix_sp95, false));
                    }
                    if (prix_sp98 != 0.0) {
                        fuelList.add(new Fuel(FuelType.SP98, prix_sp98, true));
                    } else {
                        fuelList.add(new Fuel(FuelType.SP98, prix_sp98, false));
                    }
                    if (prix_E10 != 0.0) {
                        fuelList.add(new Fuel(FuelType.E10, prix_E10, true));
                    } else {
                        fuelList.add(new Fuel(FuelType.E10, prix_E10, false));
                    }
                    if (prix_E85 != 0.0) {
                        fuelList.add(new Fuel(FuelType.E85, prix_E85, true));
                    } else {
                        fuelList.add(new Fuel(FuelType.E85, prix_E85, false));
                    }
                    if (prix_GPLC != 0.0) {
                        fuelList.add(new Fuel(FuelType.GPLc, prix_GPLC, true));
                    } else {
                        fuelList.add(new Fuel(FuelType.GPLc, prix_GPLC, false));
                    }

                    // Création de la liste des services
                    JsonNode servicesNode = Element.get("services_service");
                    if (servicesNode.isArray()) {
                        for (JsonNode service : servicesNode) {
                            servicesList.add(service.asText());
                        }
                    }
                    // Création d'une station
                    Station station = new Station(adresse, coordonnee, code_departement, code_region, fuelList, servicesList);
                    Stations.add(station);
                    addStation(station, nom_departement, code_departement, nom_region, code_region);
                }
            } else {
                System.out.println("Le fichier JSON est vide ou ne contient pas d'éléments.");
            }
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la lecture du fichier JSON : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Ajoute une station aux structures de données appropriées.
     *
     * @param station la station à ajouter
     * @param nom_dep le nom du département
     * @param code_dep le code du département
     * @param nom_reg le nom de la région
     * @param code_reg le code de la région
     */
    public static void addStation(Station station, String nom_dep, int code_dep, String nom_reg, int code_reg) {
        int reg_index = getRegionIndex(code_reg);
        if (reg_index != -1) {
            int dep_index = getDepartementsIndex(Pays.get(reg_index), code_dep);
            if (dep_index != -1) {
                Pays.get(reg_index).getDepartements().get(dep_index).getStations().add(station);
            } else {
                Departement departement = new Departement(code_dep, nom_dep, station);
                Pays.get(reg_index).getDepartements().add(departement);
            }
        } else {
            Departement departement = new Departement(code_dep, nom_dep, station);
            Region region = new Region(code_reg, nom_reg, departement);
            Pays.add(region);
        }
    }

    /**
     * Obtient l'index de la région avec le code spécifié.
     *
     * @param code_reg le code de la région
     * @return l'index de la région ou -1 si non trouvé
     */
    public static int getRegionIndex(int code_reg) {
        for (int i = 0; i < Pays.size(); i++) {
            if (Pays.get(i).getCode() == code_reg) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Obtient l'index du département dans une région spécifiée par son code.
     *
     * @param region la région contenant les départements
     * @param code_dep le code du département
     * @return l'index du département ou -1 si non trouvé
     */
    public static int getDepartementsIndex(Region region, int code_dep) {
        for (int i = 0; i < region.getDepartements().size(); i++) {
            if (region.getDepartements().get(i).getCode() == code_dep) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Obtient la liste des régions.
     *
     * @return la liste des régions
     */
    public static ArrayList<Region> getPays() {
        return Pays;
    }

    /**
     * Obtient la liste des stations.
     *
     * @return la liste des stations
     */
    public static ArrayList<Station> getStations() {
        return Stations;
    }

    /**
     * Affiche les informations des stations du premier département de la première région.
     */
    public static void afficherStationsPremierDepartement() {
        if (!Pays.isEmpty()) {
            Region premiereRegion = Pays.get(0);
            if (!premiereRegion.getDepartements().isEmpty()) {
                Departement premierDepartement = premiereRegion.getDepartements().get(0);
                for (Station station : premierDepartement.getStations()) {
                    System.out.println("Adresse : " + station.toString());
                }
            } else {
                System.out.println("Aucun département dans la première région.");
            }
        } else {
            System.out.println("Aucune région dans la liste des pays.");
        }
    }

    /**
     * Affiche les informations des régions et des départements.
     */
    public static void afficherRegionsEtDepartements() {
        for (Region region : Pays) {
            System.out.println("Region : " + region.getNom());
            for (Departement departement : region.getDepartements()) {
                System.out.println("   Département : " + departement.getNom());
            }
        }
    }

    /**
     * Affiche la hiérarchie des régions, départements et stations.
     */
    public static void afficherTree() {
        for (Region region : Pays) {
            System.out.println("Region : " + region.getNom());
            for (Departement departement : region.getDepartements()) {
                System.out.println("   Département : " + departement.getNom());
                for (Station station : departement.getStations())
                    System.out.println("       Station : " + station.getAdresse());
            }
        }
    }

    /**
     * Met à jour le jeu de données en téléchargeant les données JSON à partir d'une URI spécifiée.
     *
     * @throws InterruptedException si le thread est interrompu
     * @throws URISyntaxException si l'URI est incorrecte
     */
    public static void updateDataSet() throws InterruptedException, URISyntaxException {
        try {
            URI dataSetAdressUri = new URI(
                    "https://data.economie.gouv.fr/api/explore/v2.1/catalog/datasets/prix-des-carburants-en-france-flux-instantane-v2/exports/json?lang=fr&timezone=Europe%2FParis");

            HttpClient client = HttpClient
                    .newBuilder()
                    .build();
            HttpRequest requete = HttpRequest.newBuilder()
                    .uri(dataSetAdressUri)
                    .GET()
                    .build();
            HttpResponse<String> reponse = client.send(requete, BodyHandlers.ofString());

            File localDataSet = new File("localDataSet.json");
            FileWriter writer = new FileWriter(localDataSet);
            writer.write(reponse.body());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "Vérifier la validité du lien vers la base de données -> src/ParseJSON -> updateDataSet : dataSetAdressUri");
        }
    }
}
