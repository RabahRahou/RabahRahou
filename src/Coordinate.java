/**
 * Représente les coordonnées géographiques d'un emplacement.
 */
public class Coordinate {
    double longitude;
    double latitude;

    /**
     * Constructeur pour les coordonnées géographiques.
     *
     * @param latitude  La latitude de la station.
     * @param longitude La longitude de la station.
     */
    public Coordinate(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Retourne la latitude de la station.
     *
     * @return La latitude de la station.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Retourne la longitude de la station
     * 
     * @return La longitude de la station
     */
    public double getLongitude() {
        return longitude;
    }
    
}
