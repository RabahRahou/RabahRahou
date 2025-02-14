
import java.util.List;

/**
 * Interface représentant une application de gestion des carburants.
 */
public interface CarburantsAppInterface {

    /*
     * PLEASE NOTE THAT BY CONSTRUCTING AN OBJECT OF THIS TYPE, THE APPLICATION IS
     * AUTOMATICALLY LAUNCHED
     */

    /**
     * Récupère les filtres sélectionnés par l'utilisateur.
     * 
     * @return Une liste d'objets représentant les filtres sélectionnés par
     *         l'utilisateur.
     *         Exemple : [Par région, [Gazole, SP98], Prix minimum, [Boutique
     *         alimentaire, Carburant additivé]]
     */
    List<Object> getFiltresUtilisateur();

    /**
     * Lance l'application de gestion des carburants.
     * Cette méthode doit être implémentée par les classes qui utilisent cette
     * interface.
     */
    void runApplication();
}
