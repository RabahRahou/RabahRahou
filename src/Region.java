import java.util.ArrayList;

/**
 * Représente une région avec un code, un nom et une liste de départements.
 */

public class Region implements IStatistiques {
    int reg_code;
    private String nom;
    private ArrayList<Departement> departements;
    
    
    /**
     * Construit une nouvelle région avec les informations spécifiées.
     *
     * @param reg_code     Le code de la région.
     * @param nom          Le nom de la région.
     * @param departements La liste des départements de la région.
     */
    
    public Region(int reg_code, String nom, Departement...departements){
        this.reg_code = reg_code;
        this.departements = new ArrayList<>();
        for (Departement dept : departements) {
            this.departements.add(dept);
        }
        this.nom = nom;
    }

    /**
     * Construit une nouvelle région avec les informations spécifiées.
     *
     * @param reg_code     Le code de la région.
     * @param nom          Le nom de la région.
     * @param departements La liste des départements de la région. (juste une manière différente de créer la liste de départment par rapport au 1er constructeur)
     */
    public Region(int reg_code, String nom, ArrayList<Departement> departements){
        this.reg_code = reg_code;
        this.departements = new ArrayList<>();
        for (Departement dept : departements) {
            this.departements.add(dept);
        }
        this.nom = nom;
    }

    /**
     * Ajoute un département à la région.
     * 
     * @param departement Le département à ajouter.
     */

    public void addDepartement(Departement departement) {
        departements.add(departement);
    }

    //Getter

    /**
     * Retourne le nom de la région.
     * 
     * @return Le nom de la région.
     */

    public String getNom() {
        return nom;
    }

    /**
     * Retourne le code de la région.
     * 
     * @return Le code de la région.
     */

     public int getCode() {
        return reg_code;
    }

    /**
     * Retourne la liste des départements de la région.
     * 
     * @return La liste des départements de la région.
     */

    public ArrayList<Departement> getDepartements() {
        return departements;
    }

    /**
     * Retourne le prix moyen des stations d'un département pour un type d'essence précis.
     * 
     * @param recherche Le type d'essence recherché.
     * @return Le prix moyen du type d'essence dans les stations des départements de la région.
     */

    public double getPrixMoyen(FuelType recherche) {
        double prix = 0.0;

        double stationSize = 0.0;

        // On parcours les départments de la région
        for (int i = 0; i < getDepartements().size(); i++) {
            // on regarde les prix de l'essence recherchée pour ce département
            prix += getDepartements().get(i).getPrixMoyen(recherche);
            // on additionne le nombre de stations afin de résoudre la moyenne
            stationSize = stationSize + getDepartements().get(i).getStationSize();
        }

        // Calcule la moyenne du type d'essence selectionné
        return (prix / stationSize);
    }

    /**
     * Retourne le prix médian des stations d'un département pour un type d'essence précis.
     * 
     * @param recherche Le type d'essence recherché.
     * @return Le prix médian du type d'essence dans les stations des départements de la région.
     */

    public double getPrixMedian(FuelType recherche) {
        ArrayList<Double> tabPrix = new ArrayList<>();

        for (int i = 0; i < getDepartements().size(); i++) {
            // Trouve le type d'essence exact
            tabPrix.add(getDepartements().get(i).getPrixMedian(recherche));
            // tri insertion pour ordonner le tableau et pouvoir récupérer la médiane
            triInsertion(tabPrix);
        }
        // Calcule la médiane du type d'essence selectionné
        return (tabPrix.get(tabPrix.size() / 2));
    }

    /**
     * Méthode pour trier un tableau par insertion
     * 
     * @param tabPrix
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

    @Override
    public double getEcartType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculerEcartType'");
        }

    /**
     * Calcule et retourne la variance des prix des stations pour un type d'essence donné.
     * 
     * @param fuel Le type de carburant.
     * @return La variance des prix pour le type de carburant spécifié.
     */

    @Override
    public double getVariance(FuelType fuel) {

        // Calcul de la moyenne des prix pour ce type de carburant
        double moyenne = getPrixMoyen(fuel);

        // Initialisation de la somme des carrés des écarts
        double sommeCarresEcart = 0.0;

        // Nombre total de prix
        int nombrePrix = 0;

        // Parcourir tous les départements de la région
        for (Departement departement : departements) {
            // Parcourir toutes les stations de chaque département
            for (Station station : departement.getStations()) {
                // Obtenir tous les cIStatistiquesarburants de la station
                ArrayList<Fuel> fuels = (ArrayList<Fuel>) station.getFuels();
                // Parcourir tous les carburants de la station
                for (Fuel carburant : fuels) {
                    // Vérifier si le carburant est celui recherché
                    if (carburant.getFuelType() == fuel) {
                        // Ajouter le carré de l'écart au carré à la somme
                        double ecart = carburant.getPrix() - moyenne;
                        sommeCarresEcart += Math.pow(ecart, 2);
                        // Incrémenter le nombre total de prix
                        nombrePrix++;
                    }
                }
            }
        }

        // Calcul de la variance
        double variance = sommeCarresEcart / nombrePrix;

        return variance;
    }

    /**
     * Calcule et retourne la somme des prix des stations pour un type d'essence donné.
     * 
     * @param fuel Le type de carburant.
     * @return La somme des prix pour le type de carburant spécifié.
     */

    @Override
    public double getSomme(FuelType fuel) {
        double somme = 0.0;

    // Parcourir tous les départements de la région
    for (Departement departement : departements) {
        // Parcourir toutes les stations de chaque département
        for (Station station : departement.getStations()) {
            // Obtenir tous les carburants de la station
            ArrayList<Fuel> fuels = station.getFuels();
            // Parcourir tous les carburants de la station
            for (Fuel carburant : fuels) {
                // Vérifier si le carburant est celui recherché
                if (carburant.getFuelType() == fuel) {
                    // Ajouter le prix du carburant à la somme totale
                    somme += carburant.getPrix();
                }
            }
        }
    }

    return somme;
    }

    
    /**
     * Retourne le prix le plus faible parmi les stations de la région pour un type d'essence donné.
     * 
     * @param recherche Le type d'essence recherché.
     * @return Le prix le plus faible pour le type d'essence spécifié.
     */

    public double getMinimum(FuelType recherche) {
        ArrayList<Double> prixList = new ArrayList<>();

        // regarde tous les départements
        for (int i = 0; i < getDepartements().size(); i++) {
            // récupère les prix des différentes stations du département
            prixList.add(getDepartements().get(i).getMinimum(recherche));
        }
        // trie les différents prix
        triInsertion(prixList);

        // renvoie le moins cher
        return (prixList.get(0));
    }

    /**
     * Retourne le prix le plus élevé parmi les stations de la région pour un type d'essence donné.
     * 
     * @param recherche Le type d'essence recherché.
     * @return Le prix le plus élevé pour le type d'essence spécifié.
     */

    public double getMaximum(FuelType recherche) {
        ArrayList<Double> prixList = new ArrayList<>();

        // regarde tous les départements
        for (int i = 0; i < getDepartements().size(); i++) {
            // récupère les prix des différentes stations du département
            prixList.add(getDepartements().get(i).getMinimum(recherche));
        }
        // trie les différents prix
        triInsertion(prixList);

        // renvoie le moins cher
        return (prixList.get(prixList.size()-1));
    }

    // Getter

    /**
     * Retourne le nombre total de stations distribuant un type d'essence dans la région.
     * 
     * @param fuel Le type d'essence recherché.
     * @return Le nombre total de stations distribuant ce type d'essence.
     */

    public int getStationNbr(FuelType fuel){
        int nombreStation = 0;
        for(Departement dep : departements){
            nombreStation += dep.getStationNbr(fuel);
        }
        return nombreStation;
    }

    /**
     * Retourne le code de la région.
     * 
     * @return Le code de la région.
     */

    public int getReg_code() {
        return reg_code;
    }

    public ArrayList<Station> getStation(){
        ArrayList<Station> stations = new ArrayList();
        for(Departement dep : departements){
            for(Station station : dep.getStations()){
                stations.add(station);
            }
        }
        return(stations);
    }

    /**
     * Retourne le nombre total de stations dans la région distribuant un type de carburant spécifique.
     *
     * @param fuel Le type de carburant recherché.
     * @return Le nombre total de stations distribuant le type de carburant spécifié.
     */
    public int getNbrStationAyantCarb(FuelType fuel){
        int nombreStation = 0;
            for(Departement dep : departements){
                for(Station station : dep.getStations()){
                    for(Fuel essFuel : station.getFuels()){
                        if (essFuel.getFuelType() == fuel && essFuel.getDispo()){
                            nombreStation +=1;
                        }
                    }
            }
        }
        return nombreStation;
    }

    /**
     * Retourne le nombre total de stations dans la région offrant un ensemble spécifique de services.
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
        for(Departement dep : departements){
            for (Station station : dep.getStations()) {
                if(station.lookUpStation(temp) == true ){
                    nombreStation += 1;
                }
            }
        }
        return nombreStation;
    }

}
