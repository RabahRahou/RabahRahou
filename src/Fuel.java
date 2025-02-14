public class Fuel {
    private FuelType type;
    private double prix;
    private boolean dispo;

    /**
     * Constructeur de la classe Fuel.
     * 
     * @param type  Le type de carburant.
     * @param prix  Le prix du carburant.
     * @param dispo La disponibilité du carburant.
     */

    public Fuel(FuelType type, double prix, boolean dispo)
    {
        this.type = type;
        this.prix = prix;
        this.dispo = dispo;
    }

    /**
     * Retourne le type de carburant.
     * 
     * @return Le type de carburant.
     */

    public FuelType getFuelType()
    {
        return (this.type);
    }

    /**
     * Retourne le prix du carburant.
     * 
     * @return Le prix du carburant.
     */

    public double getPrix()
    {
        return (this.prix);
    }

    /**
     * Vérifie si le carburant est disponible.
     * 
     * @return {@code true} si le carburant est disponible, {@code false} sinon.
     */

    public boolean getDispo()
    {
        return (this.dispo);
    }
}
