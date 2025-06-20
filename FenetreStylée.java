/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amenson;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;

/**
 *
 * @author TK
 */
public class FenetreStylée extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Fenetre Stylée");
        
        // Créer une étiquette
        Label etiquette = new Label("Entre ton nom :");
        etiquette.setStyle("-fx-font-size: 20; -fx-text-fill: blue;");
        
        // Créer une zone de texte
        TextField champTexte = new TextField();
        champTexte.setMaxWidth(200);
        
        // Créer un bouton
        Button bouton = new Button("Dis bonjour !");
        bouton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 16;");
        
        // Action du bouton
        bouton.setOnAction(event -> {
            String nom = champTexte.getText();
            if (nom.isEmpty()) {
                etiquette.setText("Entre un nom, s’il te plaît !");
            } else {
                etiquette.setText("Bonjour, " + nom + " !");
            }
        });
        
        // Mettre tout dans une boîte avec des espaces
        VBox boite = new VBox(15);
        boite.setPadding(new Insets(20));
        boite.getChildren().addAll(etiquette, champTexte, bouton);
        
        // Créer et afficher la scène
        Scene scene = new Scene(boite, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}