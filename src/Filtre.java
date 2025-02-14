import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * La classe Filtre traite les informations des stations de carburant et génère des résultats statistiques.
 */
public class Filtre {
    public static List<String> chaines = new ArrayList<>();
    public static ArrayList<Station> stations = new ArrayList<Station>();

    /**
     * Méthode principale pour filtrer les informations des stations de carburant en fonction de l'entrée utilisateur.
     *
     * @param user    les entrées utilisateur sous forme de liste d'objets
     * @param regions la liste des régions disponibles
     */
    public static void filtrer(List<Object> user, List<Region> regions) {
        stations.clear();
        convertirEnChaines(user);
        String result = processStationInfo(chaines, regions);
        System.out.println(result);
        user.clear();
        chaines.clear();
    }

    /**
     * Convertit une liste d'objets en une liste de chaînes de caractères.
     *
     * @param listeObjets la liste d'objets à convertir
     */
    public static void convertirEnChaines(List<Object> listeObjets) {
        for (Object objet : listeObjets) {
            if (objet instanceof List) {
                List<?> liste = (List<?>) objet;
                StringBuilder chaineListe = new StringBuilder();
                for (Object element : liste) {
                    if (chaineListe.length() > 0) {
                        chaineListe.append(", ");
                    }
                    chaineListe.append(element);
                }
                chaines.add(chaineListe.toString());
            } else {
                chaines.add(objet.toString());
            }
        }
    }

    /**
     * Traite les informations des stations en fonction des entrées et des régions données.
     *
     * @param inputs  les entrées utilisateur sous forme de liste de chaînes
     * @param regions la liste des régions disponibles
     * @return le résultat du traitement sous forme de chaîne de caractères
     */
    public static String processStationInfo(List<String> inputs, List<Region> regions) {
        String regionDeptInfo = inputs.get(0);
        String fuelTypeString = inputs.get(1);
        String statisticType = inputs.get(2);

        if (regionDeptInfo == null || regionDeptInfo.isEmpty()) {
            return "Please select at least one region.";
        }

        if (fuelTypeString == null || fuelTypeString.isEmpty()) {
            return "Please select a fuel type.";
        }

        if (statisticType == null || statisticType.isEmpty()) {
            return "Please select a statistic type.";
        }

        FuelType fuelType;
        try {
            fuelType = FuelType.valueOf(fuelTypeString);
        } catch (IllegalArgumentException e) {
            return "Invalid fuel type. Please provide a valid fuel type.";
        }

        String[] regionDeptArray = regionDeptInfo.split(",");
        Region selectedRegion = null;
        Departement selectedDepartement = null;

        for (Region region : regions) {
            if (region.getNom().equalsIgnoreCase(regionDeptArray[0].trim())) {
                selectedRegion = region;
                if (regionDeptArray.length > 1) {
                    for (Departement dept : region.getDepartements()) {
                        if (dept.getNom().equalsIgnoreCase(regionDeptArray[1].trim())) {
                            selectedDepartement = dept;
                            break;
                        }
                    }
                }
                break;
            }
        }

        if (selectedRegion == null) {
            return "Region not found.";
        }


        String result;
        switch (statisticType.toLowerCase()) {
            case "prix minimum":
                result = getMinimumPriceStation(selectedRegion, selectedDepartement, fuelType);
                break;
            case "prix moyen":
                result = getAveragePrice(selectedRegion, selectedDepartement, fuelType);
                break;
            case "prix médian":
                result = getMedianPrice(selectedRegion, selectedDepartement, fuelType);
                break;
            case "nombre de station qui propose mon carburant":
                result = getStationCount(selectedRegion, selectedDepartement, fuelType);
                break;
            default:
                result = "Invalid statistic type. Please provide a valid statistic type (minimum, moyenne, mediane, nombre).";
                break;
        }

        try {
            generateAndOpenHtml(result, stations);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error generating or opening the HTML file.";
        }

        return result;
    }

    private static String getMinimumPriceStation(Region region, Departement dept, FuelType fuelType) {
        double minPrice = Double.MAX_VALUE;
        Station cheapestStation = new Station(null, null, 0, 0, null, chaines);

        if (dept != null) {
            minPrice = dept.getMinimum(fuelType);
            for (Station station : dept.getStations()) {
                for (Fuel fuel : station.getFuels()) {
                    if (fuel.getFuelType() == fuelType && fuel.getDispo() && fuel.getPrix() == minPrice) {
                        cheapestStation = station;
                        stations.add(cheapestStation);
                        break;
                    }
                }
            }
        } else {
            minPrice = region.getMinimum(fuelType);
            for (Departement department : region.getDepartements()) {
                for (Station station : department.getStations()) {
                    for (Fuel fuel : station.getFuels()) {
                        if (fuel.getFuelType() == fuelType && fuel.getDispo() && fuel.getPrix() == minPrice) {
                            cheapestStation = station;
                            stations.add(cheapestStation);
                            break;
                        }
                    }
                }
            }
        }

        if (cheapestStation != null) {
            return "The cheapest station is located at: " + cheapestStation.getAdresse() + " with a price of " + minPrice;
        } else {
            return "No station found for the given fuel type.";
        }
    }

    private static String getAveragePrice(Region region, Departement dept, FuelType fuelType) {
        double avgPrice;
        if (dept != null) {
            avgPrice = dept.getPrixMoyen(fuelType);
            for(Station station : dept.getStations()){
                stations.add(station);
            }
        } else {
            avgPrice = region.getPrixMoyen(fuelType);
            for(Station station : region.getStation()){
                stations.add(station);
            }
        }
        return "The average price for " + fuelType + " is: " + avgPrice;
    }

    private static String getMedianPrice(Region region, Departement dept, FuelType fuelType) {
        double medianPrice;
        if (dept != null) {
            medianPrice = dept.getPrixMedian(fuelType);
            for(Station station : dept.getStations()){
                stations.add(station);
            }
        } else {
            medianPrice = region.getPrixMedian(fuelType);
            for(Station station : region.getStation()){
                stations.add(station);
            }
        }
        return "The median price for " + fuelType + " is: " + medianPrice;
    }

    private static String getStationCount(Region region, Departement dept, FuelType fuelType) {
        int stationCount;
        if (dept != null) {
            stationCount = dept.getStationNbr(fuelType);
            for(Station station : dept.getStations()){
                stations.add(station);
            }
        } else {
            stationCount = region.getStationNbr(fuelType);
            for(Station station : region.getStation()){
                stations.add(station);
            }
        }
        return "The number of stations offering " + fuelType + " is: " + stationCount;
    }

    public static void generateAndOpenHtml(String result, ArrayList<Station> stations) throws IOException {
        String markersJs = "";
        double totalLat = 0;
        double totalLon = 0;
    
        for (Station station : stations) {
            Coordinate coord = station.getCoordonnee();
            totalLat += coord.getLatitude();
            totalLon += coord.getLongitude();
            markersJs += ("L.marker([")
            + coord.getLatitude() + ", "
            + coord.getLongitude() + "]).addTo(map)"
            + ".bindPopup('<b>" + station.getAdresse().replace("'", "\\\\'") + "</b>');";
        }
        
    
        int numStations = stations.size();
        double avgLat = totalLat / numStations;
        double avgLon = totalLon / numStations;
        int zoomLevel = 8;
    
        String mapScript = "<script>" +
        "var map = L.map('map').setView([" + avgLat + ", " + avgLon + "], " + zoomLevel + ");" +
        "L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {" +
        "    attribution: '© OpenStreetMap contributors'" +
        "}).addTo(map);" +
        markersJs.toString() +
        "</script>";

        File file = new File("src\\Projet\\base.html");
        String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    
        // Replace the content within the <main> tags after emptying it :
        String updatedContent = fileContent.replaceAll("(?s)(<main class=\"accueil\">).*?(</main>)", "");
        
        updatedContent = fileContent.replaceAll("(?s)(<main class=\"accueil\">).*?(</main>)", "$1<h1>Station Information Result</h1>" +
                "<p>" + result + "</p><div id=\"map\" style=\"width: 600px; height: 400px;\"></div>" + mapScript + "$2");
    
        Files.write(file.toPath(), updatedContent.getBytes(StandardCharsets.UTF_8));
    
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(file.toURI());
        } else {
            throw new UnsupportedOperationException("Desktop browse action not supported.");
        }
        

    }
}
