/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionecoleavancée;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.*;
import java.util.ArrayList;
import static javafx.application.Application.launch;

// Boîte pour un élève, comme une fiche dans ton carnet
 class Eleve {
    String nom; // Le nom de l'élève
    int age; // Son âge
    String classe; // Sa classe (ex. : "5e")
    ArrayList<Double> notes; // Son sac de notes

    public Eleve(String nom, int age, String classe) {
        this.nom = nom;
        this.age = age;
        this.classe = classe;
        this.notes = new ArrayList<>();
    }

    // Ajouter une note
    public void ajouterNote(double note) {
        notes.add(note);
    }

    // Calculer la moyenne
    public double calculerMoyenne() {
        if (notes.isEmpty()) return 0;
        double somme = 0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.size();
    }

    // Montrer les infos
    public String toString() {
        return "Élève: " + nom + ", Âge: " + age + ", Classe: " + classe + 
               ", Moyenne: " + String.format("%.2f", calculerMoyenne());
    }

    // Pour sauvegarder dans un fichier
    public String toFileString() {
        String notesStr = "";
        for (double note : notes) {
            notesStr += note + ",";
        }
        if (!notesStr.isEmpty()) notesStr = notesStr.substring(0, notesStr.length() - 1);
        return nom + ";" + age + ";" + classe + ";" + notesStr;
    }
}

// Boîte pour un professeur
class Professeur {
    String nom; // Le nom du professeur
    String matiere; // La matière qu’il enseigne

    public Professeur(String nom, String matiere) {
        this.nom = nom;
        this.matiere = matiere;
    }

    // Montrer les infos
    public String toString() {
        return "Professeur: " + nom + ", Matière: " + matiere;
    }

    // Pour sauvegarder dans un fichier
    public String toFileString() {
        return nom + ";" + matiere;
    }
}

public class GestionEcoleAvancee extends Application {
    // Les registres (comme des carnets magiques)
    private ArrayList<Eleve> listeEleves = new ArrayList<>();
    private ArrayList<Professeur> listeProfesseurs = new ArrayList<>();
    private TextArea zoneResultat; // Le tableau pour montrer les résultats

    @Override
    public void start(Stage stage) {
        // Donner un nom à la fenêtre
        stage.setTitle("École Magique Avancée");

        // --- Section pour les élèves ---
        Label labelEleve = new Label("Gestion des Élèves");
        labelEleve.setStyle("-fx-font-size: 18; -fx-text-fill: blue;");

        // Champs pour ajouter un élève
        TextField champNomEleve = new TextField();
        champNomEleve.setPromptText("Nom de l’élève");
        TextField champAgeEleve = new TextField();
        champAgeEleve.setPromptText("Âge de l’élève");
        TextField champClasseEleve = new TextField();
        champClasseEleve.setPromptText("Classe (ex. 5e)");
        
        // Bouton pour ajouter un élève
        Button boutonAjouterEleve = new Button("Ajouter un Élève");
        boutonAjouterEleve.setStyle("-fx-background-color: lightgreen; -fx-font-size: 14;");

        // Champs pour ajouter une note
        TextField champNomNote = new TextField();
        champNomNote.setPromptText("Nom de l’élève");
        TextField champNote = new TextField();
        champNote.setPromptText("Note (ex. 15.5)");
        Button boutonAjouterNote = new Button("Ajouter une Note");
        boutonAjouterNote.setStyle("-fx-background-color: lightgreen; -fx-font-size: 14;");

        // Bouton pour voir les élèves
        Button boutonVoirEleves = new Button("Voir les Élèves");
        boutonVoirEleves.setStyle("-fx-background-color: lightblue; -fx-font-size: 14;");

        // --- Section pour les professeurs ---
        Label labelProf = new Label("Gestion des Professeurs");
        labelProf.setStyle("-fx-font-size: 18; -fx-text-fill: blue;");

        // Champs pour ajouter un professeur
        TextField champNomProf = new TextField();
        champNomProf.setPromptText("Nom du professeur");
        TextField champMatiereProf = new TextField();
        champMatiereProf.setPromptText("Matière (ex. Maths)");
        Button boutonAjouterProf = new Button("Ajouter un Professeur");
        boutonAjouterProf.setStyle("-fx-background-color: lightgreen; -fx-font-size: 14;");

        // Bouton pour voir les professeurs
        Button boutonVoirProfs = new Button("Voir les Professeurs");
        boutonVoirProfs.setStyle("-fx-background-color: lightblue; -fx-font-size: 14;");

        // --- Section pour sauvegarder et charger ---
        Button boutonSauvegarder = new Button("Sauvegarder Tout");
        boutonSauvegarder.setStyle("-fx-background-color: yellow; -fx-font-size: 14;");
        Button boutonCharger = new Button("Charger les Données");
        boutonCharger.setStyle("-fx-background-color: yellow; -fx-font-size: 14;");

        // Zone pour montrer les résultats
        zoneResultat = new TextArea();
        zoneResultat.setEditable(false);
        zoneResultat.setPrefHeight(200);

        // --- Actions des boutons ---
        // Ajouter un élève
        boutonAjouterEleve.setOnAction(event -> {
            String nom = champNomEleve.getText();
            String ageTexte = champAgeEleve.getText();
            String classe = champClasseEleve.getText();
            if (!nom.isEmpty() && !ageTexte.isEmpty() && !classe.isEmpty()) {
                try {
                    int age = Integer.parseInt(ageTexte);
                    Eleve eleve = new Eleve(nom, age, classe);
                    listeEleves.add(eleve);
                    zoneResultat.setText("Élève ajouté : " + nom + " en " + classe);
                    champNomEleve.clear();
                    champAgeEleve.clear();
                    champClasseEleve.clear();
                } catch (NumberFormatException e) {
                    zoneResultat.setText("Erreur : l’âge doit être un nombre !");
                }
            } else {
                zoneResultat.setText("Entre un nom, un âge et une classe !");
            }
        });

        // Ajouter une note
        boutonAjouterNote.setOnAction(event -> {
            String nom = champNomNote.getText();
            String noteTexte = champNote.getText();
            if (!nom.isEmpty() && !noteTexte.isEmpty()) {
                try {
                    double note = Double.parseDouble(noteTexte);
                    if (note < 0 || note > 20) {
                        zoneResultat.setText("Erreur : la note doit être entre 0 et 20 !");
                        return;
                    }
                    for (Eleve eleve : listeEleves) {
                        if (eleve.nom.equals(nom)) {
                            eleve.ajouterNote(note);
                            zoneResultat.setText("Note " + note + " ajoutée pour " + nom);
                            champNote.clear();
                            champNomNote.clear();
                            return;
                        }
                    }
                    zoneResultat.setText("Élève " + nom + " non trouvé !");
                } catch (NumberFormatException e) {
                    zoneResultat.setText("Erreur : la note doit être un nombre !");
                }
            } else {
                zoneResultat.setText("Entre un nom et une note !");
            }
        });

        // Voir la liste des élèves
        boutonVoirEleves.setOnAction(event -> {
            if (listeEleves.isEmpty()) {
                zoneResultat.setText("Aucun élève dans l’école !");
            } else {
                String texte = "Liste des Élèves :\n";
                for (Eleve eleve : listeEleves) {
                    texte += eleve.toString() + "\n";
                }
                zoneResultat.setText(texte);
            }
        });

        // Ajouter un professeur
        boutonAjouterProf.setOnAction(event -> {
            String nom = champNomProf.getText();
            String matiere = champMatiereProf.getText();
            if (!nom.isEmpty() && !matiere.isEmpty()) {
                Professeur prof = new Professeur(nom, matiere);
                listeProfesseurs.add(prof);
                zoneResultat.setText("Professeur ajouté : " + nom + ", " + matiere);
                champNomProf.clear();
                champMatiereProf.clear();
            } else {
                zoneResultat.setText("Entre un nom et une matière !");
            }
        });

        // Voir la liste des professeurs
        boutonVoirProfs.setOnAction(event -> {
            if (listeProfesseurs.isEmpty()) {
                zoneResultat.setText("Aucun professeur dans l’école !");
            } else {
                String texte = "Liste des Professeurs :\n";
                for (Professeur prof : listeProfesseurs) {
                    texte += prof.toString() + "\n";
                }
                zoneResultat.setText(texte);
            }
        });

        // Sauvegarder dans un fichier
        boutonSauvegarder.setOnAction(event -> {
            try {
                // Sauvegarder les élèves
                FileWriter writerEleves = new FileWriter("eleves.txt");
                for (Eleve eleve : listeEleves) {
                    writerEleves.write(eleve.toFileString() + "\n");
                }
                writerEleves.close();

                // Sauvegarder les professeurs
                FileWriter writerProfs = new FileWriter("professeurs.txt");
                for (Professeur prof : listeProfesseurs) {
                    writerProfs.write(prof.toFileString() + "\n");
                }
                writerProfs.close();

                zoneResultat.setText("Données sauvegardées avec succès !");
            } catch (IOException e) {
                zoneResultat.setText("Erreur lors de la sauvegarde : " + e.getMessage());
            }
        });

        // Charger depuis un fichier
        boutonCharger.setOnAction(event -> {
            try {
                // Charger les élèves
                listeEleves.clear();
                File fileEleves = new File("eleves.txt");
                if (fileEleves.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(fileEleves));
                    String ligne;
                    while ((ligne = reader.readLine()) != null) {
                        String[] parts = ligne.split(";");
                        String nom = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        String classe = parts[2];
                        Eleve eleve = new Eleve(nom, age, classe);
                        if (parts.length > 3 && !parts[3].isEmpty()) {
                            String[] notesStr = parts[3].split(",");
                            for (String noteStr : notesStr) {
                                eleve.ajouterNote(Double.parseDouble(noteStr));
                            }
                        }
                        listeEleves.add(eleve);
                    }
                    reader.close();
                }

                // Charger les professeurs
                listeProfesseurs.clear();
                File fileProfs = new File("professeurs.txt");
                if (fileProfs.exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(fileProfs));
                    String ligne;
                    while ((ligne = reader.readLine()) != null) {
                        String[] parts = ligne.split(";");
                        String nom = parts[0];
                        String matiere = parts[1];
                        Professeur prof = new Professeur(nom, matiere);
                        listeProfesseurs.add(prof);
                    }
                    reader.close();
                }

                zoneResultat.setText("Données chargées avec succès !");
            } catch (IOException e) {
                zoneResultat.setText("Erreur lors du chargement : " + e.getMessage());
            }
        });

        // Mettre tout dans un grand sac vertical
        VBox boite = new VBox(10);
        boite.setPadding(new Insets(20));
        boite.setAlignment(Pos.CENTER);
        boite.getChildren().addAll(
            labelEleve,
            champNomEleve, champAgeEleve, champClasseEleve, boutonAjouterEleve,
            champNomNote, champNote, boutonAjouterNote,
            boutonVoirEleves,
            labelProf,
            champNomProf, champMatiereProf, boutonAjouterProf,
            boutonVoirProfs,
            boutonSauvegarder, boutonCharger,
            zoneResultat
        );

        // Créer la scène et l’afficher
        Scene scene = new Scene(boite, 500, 700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
