/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amenson;
    import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InterBancaire extends JFrame implements ActionListener {
    private JTextField txtMontant;
    private JLabel lblSolde;
    private JButton btnDeposer, btnRetirer, btnAnnuler;
    private Compte compte;
    private ArrayList<Double> historiqueTransactions; // Pour stocker les montants des transactions
	protected Component frame;

	
    public InterBancaire() {
        // Initialisation de la fenêtre
        setTitle("Gestion Bancaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialisation du compte et de l'historique
        compte = new Compte(1000.0); // Solde initial de 1000 FCFA
        historiqueTransactions = new ArrayList<>();

        // Composants
        lblSolde = new JLabel("Solde actuel: " + compte.getSolde() + " FCFA");
        txtMontant = new JTextField(10);
        btnDeposer = new JButton("Dépôt");
        btnRetirer = new JButton("Retrait");
        btnAnnuler = new JButton("Annuler");

        // Ajout des composants à la fenêtre
        add(new JLabel("Montant :"));
        add(txtMontant);
        add(btnDeposer);
        add(btnRetirer);
        add(btnAnnuler);
        add(lblSolde);

        // ActionListener pour le bouton Dépôt
        btnDeposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double montant = Double.parseDouble(txtMontant.getText());
                    if (montant <= 0) {
                        JOptionPane.showMessageDialog(frame, "Le montant doit être positif !");
                        return;
                    }
                    double ancienSolde = compte.getSolde();
                    compte.deposer(montant);
                    historiqueTransactions.add(montant); // Enregistrer le dépôt
                    lblSolde.setText("Solde actuel: " + compte.getSolde() + " FCFA");
                    txtMontant.setText("");
                    JOptionPane.showMessageDialog(frame, "Dépôt de " + montant + " FCFA effectué avec succès !");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide !");
                }
            }
        });

        // ActionListener pour le bouton Retrait
        btnRetirer.addActionListener(new ActionListener() {
            private Component frame;

			@Override
            public void actionPerformed(ActionEvent e) {
                
                
				try {
                    double montant = Double.parseDouble(txtMontant.getText());
                    if (montant <= 0) {
                        JOptionPane.showMessageDialog(frame, "Le montant doit être positif !");
                        return;
                    }
                    if (montant > compte.getSolde()) {
                        JOptionPane.showMessageDialog(frame, "Solde insuffisant !");
                        return;
                    }
                    double ancienSolde = compte.getSolde();
                    compte.retirer(montant);
                    historiqueTransactions.add(-montant); // Enregistrer le retrait (négatif)
                    lblSolde.setText("Solde actuel: " + compte.getSolde() + " FCFA");
                    txtMontant.setText("");
                    JOptionPane.showMessageDialog(frame, "Retrait de " + montant + " FCFA effectué avec succès !");
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
                if (historiqueTransactions.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Aucune transaction à annuler !");
                    return;
                }
                double derniereTransaction = historiqueTransactions.remove(historiqueTransactions.size() - 1);
                if (derniereTransaction > 0) {
                    compte.retirer(derniereTransaction); // Annuler un dépôt
                    JOptionPane.showMessageDialog(frame, "Dépôt de " + derniereTransaction + " FCFA annulé !");
                } else {
                    compte.deposer(-derniereTransaction); // Annuler un retrait
                    JOptionPane.showMessageDialog(frame, "Retrait de " + (-derniereTransaction) + " FCFA annulé !");
                }
                lblSolde.setText("Solde actuel: " + compte.getSolde() + " FCFA");
                txtMontant.setText("");
            }
        });

        // Ajuster la taille de la fenêtre
        pack();
        setVisible(true);
    }

    // Classe pour gérer le compte
    class Compte {
        double solde;

        public Compte(double soldeInitial) {
            this.solde = soldeInitial;
        }

        public double getSolde() {
            return solde;
        }

        public void deposer(double montant) {
            solde += montant;
        }

        public void retirer(double montant) {
            solde -= montant;
        }
    }

    public static void main(String[] args) {
        // Lancer l'interface
        SwingUtilities.invokeLater(() -> new InterBancaire());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        }
}


