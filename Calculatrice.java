/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcullatricemagique;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Calculatrice extends Application {
    private TextField ecran; // L'écran où on voit les nombres
    private double nombre1 = 0; // Le premier nombre
    private String operation = ""; // L'opération choisie (+, -, *, /)
    private boolean debut = true; // Pour savoir si on commence un nouveau calcul

    @Override
    public void start(Stage stage) {
        // Donner un nom à la fenêtre
        stage.setTitle("Ma Calculatrice Magique");

        // Créer l'écran (comme un tableau pour écrire les nombres)
        ecran = new TextField("0");
        ecran.setEditable(false); // On ne peut pas écrire directement dedans
        ecran.setAlignment(Pos.CENTER_RIGHT); // Les nombres s'affichent à droite
        ecran.setStyle("-fx-font-size: 20;");

        // Créer une grille pour placer les boutons, comme un plateau de jeu
        GridPane grille = new GridPane();
        grille.setAlignment(Pos.CENTER);
        grille.setHgap(10); // Espaces horizontaux entre les boutons
        grille.setVgap(10); // Espaces verticaux entre les boutons
        grille.setPadding(new Insets(20)); // Coussin autour de la grille

        // Créer les boutons (comme des touches magiques)
        String[] etiquettes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        int index = 0;
        for (int ligne = 1; ligne <= 4; ligne++) {
            for (int colonne = 0; colonne < 4; colonne++) {
                Button bouton = new Button(etiquettes[index]);
                bouton.setStyle("-fx-font-size: 16; -fx-background-color: lightblue;");
                bouton.setPrefSize(60, 60); // Taille des boutons
                bouton.setOnAction(event -> gererClic(bouton.getText()));
                grille.add(bouton, colonne, ligne);
                index++;
            }
        }

        // Ajouter l'écran en haut de la grille
        grille.add(ecran, 0, 0, 4, 1); // L'écran prend 4 colonnes

        // Créer la scène (le décor de la fenêtre)
        Scene scene = new Scene(grille, 300, 400);
        stage.setScene(scene);
        stage.show();
    }

    // Gérer ce qui se passe quand on clique sur un bouton
    private void gererClic(String valeur) {
        // Si c'est un nombre ou "0"
        if (valeur.matches("[0-9]")) {
            if (debut) {
                ecran.setText(valeur);
                debut = false;
            } else {
                ecran.setText(ecran.getText() + valeur);
            }
        }
        // Si c'est le bouton "C" pour effacer
        else if (valeur.equals("C")) {
            ecran.setText("0");
            debut = true;
            nombre1 = 0;
            operation = "";
        }
        // Si c'est une opération (+, -, *, /)
        else if (valeur.matches("[+\\-*/]")) {
            nombre1 = Double.parseDouble(ecran.getText());
            operation = valeur;
            ecran.setText("0");
            debut = true;
        }
        // Si c'est le bouton "=" pour calculer
        else if (valeur.equals("=")) {
            double nombre2 = Double.parseDouble(ecran.getText());
            double resultat = 0;
            switch (operation) {
                case "+":
                    resultat = nombre1 + nombre2;
                    break;
                case "-":
                    resultat = nombre1 - nombre2;
                    break;
                case "*":
                    resultat = nombre1 * nombre2;
                    break;
                case "/":
                    if (nombre2 != 0) {
                        resultat = nombre1 / nombre2;
                    } else {
                        ecran.setText("Erreur");
                        return;
                    }
                    break;
            }
            ecran.setText(String.valueOf(resultat));
            debut = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}