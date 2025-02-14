public interface IStatistiques {
    
    
    /**
     * Méthode pour calculer la moyenne d'un ensemble de stations.
     * @param fuel  le type de fuel recherché
     * @return      le prix moyen de ce type de fuel parmi un ensemble de stations
     */
    double getPrixMoyen(FuelType fuel);

    /**
     * Méthode pour calculer la médiane d'un ensemble de stations.
     * @param fuel  le type de fuel recherché   
     * @return      le prix médian d'un ensemble de stations
     */
    double getPrixMedian(FuelType fuel);

    /**
     * Méthode pour calculer l'écart-type d'un ensemble de stations.
     * @return  l'écart type d'un ensemble de stations
     */
    double getEcartType();

    /**
     * Méthode pour calculer la variance d'un ensemble de stations.
     * @param fuel  le type de fuel recherché
     * @return      la variance de ce type de fuel dans un ensemble de stations
     */
    double getVariance(FuelType fuel);

    /**
     * Méthode pour calculer la somme d'un ensemble de stations.
     * @param fuel  le type de fuel recherché
     * @return      la somme d'un ensemble de stations
     */
    double getSomme(FuelType fuel);

    /**
     * Méthode pour trouver la valeur minimale d'un ensemble de stations.
     * @param fuel  le type du fuel recherché
     * @return      le prix minimum de ce type de fuel dans un ensemble de stations
     */
    double getMinimum(FuelType fuel);

    /**
     * Méthode pour trouver la valeur maximale d'un ensemble de stations.
     * @param fuel      le type de fuel recherché
     * @return          le prix du fuel recherché
     */
    double getMaximum(FuelType fuel);

    /**
     * Méthode pour trouver le nombre de stations d'une granularitée choisie.
     * @param fuel  le type de fuel recherché
     * @return      le nombre de stations dans la ganularitée choisie ayant ce type de carburant
     */
    int getStationNbr(FuelType fuel);

    /**
     * Méthode pour trouver le nombre de stations ayant un certains type de carburant.
     * @param fuel  le type de carburant
     * @return      nombre de stations ayant ce type de carburant
     */
    int getNbrStationAyantCarb(FuelType fuel);

    /**
     * Méthode pour trouver le nombre de stations ayant un certain service.
     * @param services  un service particulier
     * @return          le nombre de stations ayant ce service
     */
    int getNbrStationAyantServices(String ... services);

}