import java.util.ArrayList;
import java.util.List;

/**
 * Représente une station avec une adresse, des coordonnées, un numéro de région et de département, des informations sur les carburants et les services disponibles.
 */

public class Station {
    private String adresse;
    private int codeDepartement;
    private int codeRegion;
    private Coordinate coordonnee;
    private ArrayList<Fuel> fuels;
    private ArrayList<String> listServices ;

    /**
    * Construit une nouvelle station avec les informations spécifiées.
    *
    * @param adresse         l'adresse de la station
    * @param coordonnee      les coordonnées de la station
    * @param codeDepartement le code du département de la station
    * @param codeRegion      le code de la région de la station
    * @param fuels           la liste des carburants disponibles à la station
    * @param listServices    la liste des services disponibles à la station
    */
    public Station(String adresse, Coordinate coordonnee, int codeDepartement, int codeRegion, ArrayList<Fuel> fuels, 
            List<String> listServices) {
        this.adresse = adresse;
        this.coordonnee = coordonnee;
        this.codeDepartement = codeDepartement;
        this.codeRegion = codeRegion;
        this.fuels = fuels;
        this.listServices = new ArrayList<>();
        if (listServices != null){
            for (String service : listServices) {
            this.listServices.add(service);
            }
        }
    }

    /**
     * Construit une nouvelle station avec les informations spécifiées, sans liste de services.
     *
     * @param adresse         l'adresse de la station
     * @param coordonnee      les coordonnées de la station
     * @param codeDepartement le code du département de la station
     * @param codeRegion      le code de la région de la station
     * @param fuels           la liste des carburants disponibles à la station
     */
    public Station(String adresse, Coordinate coordonnee, int codeDepartement, int codeRegion, ArrayList<Fuel> fuels)
    {   
        this.adresse = adresse;
        this.codeDepartement = codeDepartement;
        this.codeRegion = codeRegion;
        this.fuels = fuels;
        this.coordonnee = coordonnee;
        
    }

    /**
     * Vérifie si la station propose tous les services souhaités.
     *
     * @param ListDeServicesSouhaites la liste des services souhaités
     * @return true si tous les services souhaités sont proposés par la station, false sinon
     */
    public Boolean lookUpStation(ArrayList<String> ListDeServicesSouhaites){
            if (listServices.containsAll(ListDeServicesSouhaites)){
                return true;
            }
            else {
                return false;
            }
        }
    

    //Getters

    /**
     * Obtient l'adresse de la station.
     *
     * @return l'adresse de la station
     */
    public String getAdresse(){
        return adresse;
    }

    /**
     * Obtient la liste des carburants disponibles à la station.
     *
     * @return la liste des carburants
     */
    public ArrayList<Fuel> getFuels() {
        return fuels;
    }

    /**
     * Obtient la liste des services disponibles à la station.
     *
     * @return la liste des services
     */
    public ArrayList<String> getListServices() {
        return listServices;
    }

    /**
     * Obtient les coordonnées de la station.
     *
     * @return les coordonnées de la station
     */
    public Coordinate getCoordonnee() {
        return coordonnee;
    }

    /**
     * Obtient le code du département de la station.
     *
     * @return le code du département
     */
    public int getCodeDepartement() {
        return codeDepartement;
    }

    /**
     * Obtient le code de la région de la station.
     *
     * @return le code de la région
     */
    public int getCodeRegion() {
        return codeRegion;
    }


    /**
     * Retourne une représentation sous forme de chaîne de caractères des informations de la station.
     *
     * @return une chaîne de caractères représentant les informations de la station
     */
    @Override
    public String toString() {
        String finalString =  "La station " + adresse + " se situe dans le département numéro " + codeDepartement + ", elle possède les carburants suivants : ";
        for(int i = 0; i < fuels.size(); i++){
            if (fuels.get(i).getDispo()) {
                finalString+= fuels.get(i).getFuelType() + " " ;
            }
        }
        finalString += "lat =" +coordonnee.latitude + coordonnee.longitude;
        return finalString;
    }
}
