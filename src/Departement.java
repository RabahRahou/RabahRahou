import java.util.ArrayList;

/**
 * La classe {@code Departement} représente un département avec un nom, un code et une liste de stations de carburant.
 */
public class Departement implements IStatistiques {
    private String nom;
    private int codeDep;
    private ArrayList<Station> stations;

    /**
     * Constructeur de la classe {@code Departement}.
     * 
     * @param codeDep   Le code du département.
     * @param nom       Le nom du département.
     * @param stations  Les stations de carburant du département (en tant que varargs).
     */
    public Departement(int codeDep, String nom, Station ... stations) {
        this.codeDep = codeDep;
        this.stations = new ArrayList<>();
        for (Station station : stations) {
            this.stations.add(station);
        }
        this.nom = nom;
    }

    /**
     * Constructeur de la classe {@code Departement}.
     * 
     * @param codeDep   Le code du département.
     * @param nom       Le nom du département.
     * @param stations  Les stations de carburant du département.
     */
    public Departement(int codeDep, String nom, ArrayList<Station> stations) {
        this.codeDep = codeDep;
        this.stations = new ArrayList<>();
        for (Station station : stations) {
            this.stations.add(station);
        }
        this.nom = nom;
    }

    /**
     * Retourne la liste des stations de carburant du département.
     * 
     * @return La liste des stations de carburant du département.
     */
    public ArrayList<Station> getStations() {
        return stations;
    }

    /**
     * Retourne le nom du département.
     * 
     * @return Le nom du département.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le code du département.
     * 
     * @return Le code du département.
     */
    public int getCode() {
        return codeDep;
    }

    /**
     * Retourne le prix moyen des stations de carburant du département pour un type d'essence précis.
     * 
     * @param recherche     Le type d'essence recherché.
     * @return              Le prix moyen du type d'essence dans les stations du département.
     */
    public double getPrixMoyen(FuelType recherche) {
        double prix = 0.0;
        double stationSize = 0.0;

        for (int i = 0; i < getStations().size(); i++) {
            for (int j = 0; j < getStations().get(i).getFuels().size(); j++) {
                if (getStations().get(i).getFuels().get(j).getFuelType() == recherche && getStations().get(i).getFuels().get(j).getDispo()) {
                    prix += getStations().get(i).getFuels().get(j).getPrix();
                    stationSize += 1;
                }
            }
        }

        return (prix / stationSize);
    }

    /**
     * Retourne le prix médian des stations de carburant du département pour un type d'essence précis.
     * 
     * @param recherche     Le type d'essence recherché.
     * @return              Le prix médian du type d'essence dans les stations du département.
     */
    public double getPrixMedian(FuelType recherche) {
        ArrayList<Double> tabPrix = new ArrayList<>();

        for (int i = 0; i < getStations().size(); i++) {
            for (int j = 0; j < getStations().get(i).getFuels().size(); j++) {
                if (getStations().get(i).getFuels().get(j).getFuelType() == recherche && getStations().get(i).getFuels().get(j).getDispo()) {
                    tabPrix.add(getStations().get(i).getFuels().get(j).getPrix());
                }
            }
        }
    
        triInsertion(tabPrix);
    
        int size = tabPrix.size();
        if (size % 2 == 0) {
            int middleIndex = size / 2;
            return (tabPrix.get(middleIndex - 1) + tabPrix.get(middleIndex)) / 2.0;
        } else {
            return tabPrix.get(size / 2);
        }
    }

    /**
     * Trie une liste de prix par insertion.
     * 
     * @param tabPrix       La liste de prix à trier.
     */
    public void triInsertion(ArrayList<Double> tabPrix) {
        int n = tabPrix.size();
        for (int i = 1; i < n; ++i) {
            Double cle = tabPrix.get(i);
            int j = i - 1;

            while (j >= 0 && tabPrix.get(j) > cle) {
                tabPrix.set(j + 1, tabPrix.get(j));
                j = j - 1;
            }
            tabPrix.set(j + 1, cle);
        }
    }

    /**
     * Trouvé avec ChatGPT
     * Retourne l'écart-type des prix des carburants dans le département.
     * 
     * @return  L'écart-type des prix des carburants.
     */
    public double getEcartType() {
        double sum = 0.0;
        double mean;
        double variance = 0.0;
        int count = 0;

        for (Station station : stations) {
            for (Fuel fuel : station.getFuels()) {
                if (fuel.getDispo()) {
                    sum += fuel.getPrix();
                    count++;
                }
            }
        }

        if (count == 0) return 0.0;

        mean = sum / count;

        for (Station station : stations) {
            for (Fuel fuel : station.getFuels()) {
                if (fuel.getDispo()) {
                    variance += Math.pow(fuel.getPrix() - mean, 2);
                }
            }
        }

        variance = variance / count;
        return Math.sqrt(variance);
    }

    /**
     * Trouvé avec ChatGPT
     * Retourne la variance des prix des carburants pour un type d'essence donné dans le département.
     * 
     * @param fuel Le type d'essence recherché.
     * @return La variance des prix des carburants pour le type d'essence spécifié.
     */
    public double getVariance(FuelType fuel) {
        double sum = 0.0;
        double mean;
        double variance = 0.0;
        int count = 0;

        for (Station station : stations) {
            for (Fuel essFuel : station.getFuels()) {
                if (essFuel.getFuelType() == fuel && essFuel.getDispo()) {
                    sum += essFuel.getPrix();
                    count++;
                }
            }
        }

        if (count == 0) return 0.0;

        mean = sum / count;

        for (Station station : stations) {
            for (Fuel essFuel : station.getFuels()) {
                if (essFuel.getFuelType() == fuel && essFuel.getDispo()) {
                    variance += Math.pow(essFuel.getPrix() - mean, 2);
                }
            }
        }

        return variance / count;
    }

    /**
     * Retourne la somme des prix des carburants pour un type d'essence donné dans le département.
     * 
     * @param fuel Le type d'essence recherché.
     * @return La somme des prix des carburants pour le type d'essence spécifié.
     */
    @Override
    public double getSomme(FuelType fuel) {
        throw new UnsupportedOperationException("Unimplemented method 'calculerSomme'");
    }

    /**
     * Retourne le prix le plus bas parmi les stations de carburant du département pour un type d'essence donné.
     * 
     * @param recherche Le type d'essence recherché.
     * @return Le prix le plus bas pour le type d'essence spécifié.
     */
    public double getMinimum(FuelType recherche) {
        double prixMin = getStations().get(0).getFuels().get(0).getPrix();

        for (int i = 0; i < getStations().size(); i++) {
            for (int j = 0; j < getStations().get(i).getFuels().size(); j++) {
                if (getStations().get(i).getFuels().get(j).getFuelType() == recherche
                        && getStations().get(i).getFuels().get(j).getPrix() < prixMin
                        && getStations().get(i).getFuels().get(j).getDispo()) {
                    prixMin = getStations().get(i).getFuels().get(j).getPrix();
                }
            }
        }
        return prixMin;
    }

    /**
     * Retourne le prix le plus élevé parmi les stations de carburant du département pour un type d'essence donné.
     * 
     * @param fuel Le type d'essence recherché.
     * @return Le prix le plus élevé pour le type d'essence spécifié.
     */
    @Override
    public double getMaximum(FuelType fuel) {
        double prixMax = Double.MIN_VALUE;

        for (Station station : getStations()) {
            for (Fuel carburant : station.getFuels()) {
                if (carburant.getFuelType() == fuel && carburant.getDispo()) {
                    prixMax = Math.max(prixMax, carburant.getPrix());
                }
            }
        }
        return prixMax;
    }

    /**
     * Retourne le code du département auquel la station est associée.
     * 
     * @return Le code du département de la station.
     */
    public int getCodeDep() {
        return codeDep;
    }

    /**
     * Retourne le nombre total de stations dans le département.
     * 
     * @return Le nombre total de stations dans le département.
     */
    public double getStationSize() {
        return (stations.size());
    }

    /**
     * Retourne le nombre total de stations de carburant distribuant un type d'essence dans le département.
     * 
     * @param recherche Le type d'essence recherché.
     * @return Le nombre total de stations distribuant ce type d'essence dans le département.
     */
    public int getStationNbr(FuelType recherche) {
        int stationFuel = 0;

        for (int i = 0; i < getStations().size(); i++)
            for (int j = 0; j < getStations().get(i).getFuels().size(); j++) {
                if (getStations().get(i).getFuels().get(j).getFuelType() == recherche)
                    stationFuel++;
            }

        return (stationFuel);
    }

    /**
     * Ajoute une station à la liste des stations d'un département.
     *
     * @param station La station à ajouter.
     * @param dep Le département auquel ajouter la station.
     */
    public void addStation(Station station, Departement dep){
        dep.stations.add(station);
    }
    

    /**
     * Retourne le nombre total de stations dans un département distribuant un type de carburant spécifique.
     *
     * @param fuel Le type de carburant recherché.
     * @return Le nombre total de stations distribuant le type de carburant spécifié.
     */
    public int getNbrStationAyantCarb(FuelType fuel){
        int nombreStation = 0;
            for(Station station : stations){
                for(Fuel essFuel : station.getFuels()){
                    if (essFuel.getFuelType() == fuel && essFuel.getDispo()){
                        nombreStation +=1;
                    }
                }
        }
    return nombreStation;
    }

    /**
     * Retourne le nombre total de stations dans un département offrant un ensemble spécifique de services.
     *
     * @param services Les services requis.
     * @return Le nombre total de stations offrant tous les services spécifiés.
     */
    public int getNbrStationAyantServices(String ... services){
        int nombreStation = 0;
        ArrayList<String> temp = new ArrayList<>();
        for (String unService : services) {
            temp.add(unService);
        }
        for(Station station : stations){
            if(station.lookUpStation(temp) == true ){
                nombreStation += 1;
            }
        }
    return nombreStation;
    }

}
