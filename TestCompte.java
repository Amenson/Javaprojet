/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amenson;

/**
 *
 * @author TK
 */import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import amenson.InterBancaire.Compte;

public class TestCompte extends JFrame implements ActionListener {
	

	    private JTextField txtMontant;
	    private JLabel lblSolde;
	    private JButton btnDeposer, btnAnnuler;
	    private Compte compte;
	    private double soldePrecedent; // Pour stocker le solde avant dépôt

	    public TestCompte() {
	        // Initialisation de la fenêtre
	        setTitle("Gestion Bancaire");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new FlowLayout());

	        // Initialisation du compte (exemple)
	        compte = new Compte; // Solde initial de 1000 FCFA
	        soldePrecedent = compte.solde;

	        // Composants
	        lblSolde = new JLabel("Solde actuel: " + compte.solde + " FCFA");
	        txtMontant = new JTextField(10);
	        btnDeposer = new JButton("Dépôt");
	        btnAnnuler = new JButton("Annuler");

	        // Ajout des composants à la fenêtre
	        add(new JLabel("Montant à déposer :"));
	        add(txtMontant);
	        add(btnDeposer);
	        add(btnAnnuler);
	        add(lblSolde);

	        // ActionListener pour le bouton Dépôt
	        btnDeposer.addActionListener(new ActionListener() {
	            private Component frame;
	            

				@Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    double montant = Double.parseDouble(txtMontant.getText());
	                    soldePrecedent = compte.getSolde(); // Sauvegarder le solde avant dépôt
	                    compte.deposer(montant);
	                    lblSolde.setText("Solde actuel: " + compte.solde + " FCFA");
	                    txtMontant.setText(""); // Réinitialiser le champ
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide !");
	                }
	            }
	        });

	        // ActionListener pour le bouton Annuler
	        btnAnnuler.addActionListener(new ActionListener() {
	            private Component frame;

				@Override
	            public void actionPerformed(ActionEvent e) {
	                if (soldePrecedent != compte.solde) { // Vérifier si un dépôt a été effectué
	                    compte.solde = soldePrecedent; // Restaurer le solde précédent
	                    lblSolde.setText("Solde actuel: " + compte.solde + " FCFA");
	                    JOptionPane.showMessageDialog(frame, "Dépôt annulé avec succès !");
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Aucun dépôt à annuler !");
	                }
	                txtMontant.setText(""); // Réinitialiser le champ
	            }
	        });

	        // Ajuster la taille de la fenêtre
	        pack();
	        setVisible(true);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}

