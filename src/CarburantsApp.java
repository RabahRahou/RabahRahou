import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CarburantsApp extends JFrame implements CarburantsAppInterface {
    private List<Object> filtresUtilisateur = new ArrayList<>();
    private JPanel cards; // Un conteneur qui utilise CardLayout
    private final static String COMBO_BOX = "ComboBox";
    private final static String REGION_LIST = "RegionList";
    private final static String DEPARTEMENT_LIST = "DepartementList";
    private List<String> selectedGranularitee = new ArrayList<>();
    private JList<String> departementsList; // La liste des départements

    public CarburantsApp() {
        super("Prix des carburants en France");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Création du panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // En-tête
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel headerLabel = new JLabel("Prix des carburants en France", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Zone de contenu principal
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2));

        // Section A
        JPanel sectionAPanel = new JPanel();
        sectionAPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sectionAPanel.setLayout(new BorderLayout());
        JLabel sectionALabel = new JLabel("Résultats :", SwingConstants.CENTER);
        JTextArea sectionATextArea = new JTextArea();
        sectionATextArea.setEditable(false);
        sectionATextArea.setLineWrap(true);
        sectionATextArea.setWrapStyleWord(true);
        sectionAPanel.add(sectionALabel, BorderLayout.NORTH);
        sectionAPanel.add(sectionATextArea, BorderLayout.CENTER);
        contentPanel.add(sectionAPanel);

        // Création des cartes pour CardLayout
        cards = new JPanel(new CardLayout());

        // Section B avec JComboBox
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        comboBoxPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Selection de l'emplacement géographique : ");

        // Appel à votre méthode afficherRegionsEtDepartements() pour obtenir les
        // régions et les départements
        ArrayList<Region> regions = ParseJSON.getPays();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("Veuillez choisir une granularité :");
        for (Region region : regions) {
            comboBoxModel.addElement(region.getNom());
        }
        JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedRegion = (String) combo.getSelectedItem();
                if (!"Veuillez choisir une granularité :".equals(selectedRegion)) {
                    for (Region region : regions) {
                        if (region.getNom().equals(selectedRegion)) {
                            DefaultListModel<String> departementModel = new DefaultListModel<>();
                            for (Departement departement : region.getDepartements()) {
                                departementModel.addElement(departement.getNom());
                            }
                            departementsList.setModel(departementModel);
                            CardLayout cl = (CardLayout) (cards.getLayout());
                            cl.show(cards, DEPARTEMENT_LIST);
                            selectedGranularitee.clear();
                            selectedGranularitee.add(selectedRegion);
                            break;
                        }
                    }
                }
            }
        });

        comboBoxPanel.add(label);
        comboBoxPanel.add(comboBox);
        cards.add(comboBoxPanel, COMBO_BOX);

        // Section B avec JList pour les départements
        JPanel departementListPanel = new JPanel();
        departementListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        departementListPanel.setLayout(new BorderLayout());
        departementsList = new JList<>();
        departementListPanel.add(new JScrollPane(departementsList), BorderLayout.CENTER);
        cards.add(departementListPanel, DEPARTEMENT_LIST);
        // Ajouter le panneau des cartes au panneau principal
        mainPanel.add(cards, BorderLayout.CENTER);

        // Ajout d'un écouteur de sélection à la JList des départements pour mettre à
        // jour selectedGranularitee
        departementsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedDepartement = departementsList.getSelectedValue();
                    if (selectedDepartement != null) {
                        selectedGranularitee.add(selectedDepartement);
                    }
                }
            }
        });

        // Ajout d'un écouteur d'événements au JComboBox pour basculer entre les cartes
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                String selectedGranularite = (String) comboBox.getSelectedItem();
                if ("Par région".equals(selectedGranularite)) {
                    cl.show(cards, REGION_LIST);
                } else if ("Par département".equals(selectedGranularite)) {
                    cl.show(cards, DEPARTEMENT_LIST);
                } else {
                    cl.show(cards, COMBO_BOX);
                }
            }
        });
        contentPanel.add(cards);

        // Section C
        JPanel sectionCPanel = new JPanel();
        sectionCPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sectionCPanel.setLayout(new BorderLayout());
        JLabel sectionCLabel = new JLabel("Selection du (es) carburant(s) : ", SwingConstants.CENTER);
        JPanel checkboxesPanel = new JPanel(new GridLayout(0, 1));
        String[] carburants = { "Gazole", "SP98", "E10", "E85", "SP95", "GPLc" };
        for (String carburant : carburants) {
            JCheckBox checkBox = new JCheckBox(carburant);
            checkboxesPanel.add(checkBox);
        }
        sectionCPanel.add(sectionCLabel, BorderLayout.NORTH);
        sectionCPanel.add(new JScrollPane(checkboxesPanel), BorderLayout.CENTER);
        contentPanel.add(sectionCPanel);

        // Section D
        JPanel sectionDPanel = new JPanel();
        sectionDPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sectionDPanel.setLayout(new FlowLayout());
        JLabel labelD = new JLabel("Statistiques : \n");
        JLabel label2 = new JLabel("Selection d'une statistique : \n");
        JComboBox<String> statsComboBox = new JComboBox<>();
        statsComboBox.addItem("Veuillez choisir un Item :");
        statsComboBox.addItem("Prix moyen");
        statsComboBox.addItem("Prix médian");
        statsComboBox.addItem("Prix minimum");
        statsComboBox.addItem("Nombre de stations qui propose mon carburant");
        // Définition des services disponibles
        String[] servicesDisponibles = { "Relais colis", "Boutique alimentaire", "Station de gonflage",
                "Carburant additivé",
                "Piste poids lourds", "Services réparation / entretien",
                "Vente de gaz domestique (Butane, Propane)", "Vente d'additifs carburants",
                "Automate CB 24/24" };

        // Création d'un panel pour les services
        JPanel servicesPanel = new JPanel();
        servicesPanel.setLayout(new GridLayout(0, 1)); // Une colonne

        // Ajout d'une JLabel pour le titre des services
        JLabel servicesLabel = new JLabel("Services disponibles :");
        servicesPanel.add(servicesLabel);

        // Ajout des cases à cocher pour chaque service
        for (String service : servicesDisponibles) {
            JCheckBox checkBox = new JCheckBox(service);
            servicesPanel.add(checkBox);
        }

        // Ajout du panel des services à sectionDPanel
        sectionDPanel.add(labelD);
        sectionDPanel.add(label2);
        sectionDPanel.add(statsComboBox);
        contentPanel.add(sectionDPanel);
        sectionDPanel.add(servicesPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Création du footer pour le bouton "Appliquer les filtres"
        JPanel footerPanel2 = new JPanel();
        footerPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alignement à droite

        // Création du bouton "Appliquer les filtres"
        JButton appliquerButton = new JButton("Appliquer les filtres");

        // Ajout d'un ActionListener au bouton "Appliquer les filtres"
        appliquerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Récupérer les carburants sélectionnés
                List<String> selectedCarburants = new ArrayList<>();
                for (Component component : checkboxesPanel.getComponents()) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        if (checkBox.isSelected()) {
                            selectedCarburants.add(checkBox.getText());
                        }
                    }
                }

                // Récupérer la statistique sélectionnée
                String selectedStat = (String) statsComboBox.getSelectedItem();

                // Récupérer les services sélectionnés
                List<String> selectedServices = new ArrayList<>();
                for (Component component : servicesPanel.getComponents()) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) component;
                        if (checkBox.isSelected()) {
                            selectedServices.add(checkBox.getText());
                        }
                    }
                }

                // Ajouter les filtres utilisateur à la liste
                filtresUtilisateur.clear();
                filtresUtilisateur.add(selectedGranularitee);
                filtresUtilisateur.add(selectedCarburants);
                filtresUtilisateur.add(selectedStat);
                filtresUtilisateur.add(selectedServices);

                // Afficher les filtres utilisateur
                System.out.println("Filtres utilisateur : " + filtresUtilisateur);

                // Récupère pour les filtre et les calculs pour le rapport
                List<Object> user = filtresUtilisateur;
                Filtre c = new Filtre();
                c.filtrer(user, ParseJSON.Pays);

                // Réinitialiser la sélection dans le JComboBox
                comboBox.setSelectedIndex(0);

                // Réinitialiser la liste des départements dans la JList
                DefaultListModel<String> emptyModel = new DefaultListModel<>();
                departementsList.setModel(emptyModel);

                // Réinitialiser la visibilité des différentes cartes
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, COMBO_BOX);

                // Réinitialiser la variable de granularité sélectionnée
                selectedGranularitee.clear();
                
            }
        });

        // Création du bouton de "Mise à Jour des données"
        JButton majButton = new JButton("MàJ des Données");

        // Ajout d'un ActionListener au bouton "Mise à Jour des données"
        majButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ParseJSON.updateDataSet();
                } catch (InterruptedException | URISyntaxException e1) {
                    System.out.println("Erreur lors de la Mise à Jour des données");
                }
            }
        });

        // Ajout du bouton "Mise à Jour des données" au footerPanel2
        footerPanel2.add(majButton);

        // Ajout du bouton "Appliquer les Filtres" au footerPanel2
        footerPanel2.add(appliquerButton);

        // Ajout du footerPanel2 au bas de la fenêtre
        add(footerPanel2, BorderLayout.SOUTH);

        // Pied de page
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel footerLabel = new JLabel("© 2024 CarburantsApp. Tous droits réservés.", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerPanel.add(footerLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public List<Object> getFiltresUtilisateur() {
        return filtresUtilisateur;

    }

    public void runApplication() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CarburantsApp();

            }
        });
    }
}
