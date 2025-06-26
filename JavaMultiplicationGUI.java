/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudemultiplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JavaMultiplicationGUI extends JFrame {

    // Constantes du jeu
    private static final int NOMBRE_DE_QUESTIONS = 10;
    private static final int NIVEAU_MAX_NOMBRE = 12;

    // Composants GUI
    private JLabel lblQuestion;
    private JTextField txtReponse;
    private JButton btnSoumettre;
    private JLabel lblFeedback;
    private JLabel lblScore;
    private JLabel lblQuestionNumero;
    private JButton btnRejouer;

    // Variables de jeu
    private int num1, num2, reponseCorrecte;
    private int score;
    private int questionActuelle;
    private Random random;

    public JavaMultiplicationGUI() {
        super("Jeu de Multiplication"); // Titre de la fenêtre
        random = new Random();

        // Initialisation des composants
        lblQuestion = new JLabel("Combien font X x Y ?", SwingConstants.CENTER);
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 20));

        txtReponse = new JTextField(10);
        txtReponse.setFont(new Font("Arial", Font.PLAIN, 17));
        txtReponse.setHorizontalAlignment(JTextField.CENTER);

        btnSoumettre = new JButton("Soumettre");
        btnSoumettre.setFont(new Font("Arial", Font.PLAIN, 16));

        lblFeedback = new JLabel(" ", SwingConstants.CENTER); // Espace pour le feedback
        lblFeedback.setFont(new Font("Arial", Font.ITALIC, 14));

        lblScore = new JLabel("Score: 0/" + NOMBRE_DE_QUESTIONS, SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.PLAIN, 14));

        lblQuestionNumero = new JLabel("Question: 1/" + NOMBRE_DE_QUESTIONS, SwingConstants.CENTER);
        lblQuestionNumero.setFont(new Font("Arial", Font.PLAIN, 14));

        btnRejouer = new JButton("Rejouer");
        btnRejouer.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRejouer.setVisible(false); // Initialement caché

        // Layout
        JPanel panelPrincipal = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 lignes, 1 colonne, espacements
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Marge autour du panel

        panelPrincipal.add(lblQuestionNumero);
        panelPrincipal.add(lblQuestion);
        panelPrincipal.add(txtReponse);
        panelPrincipal.add(btnSoumettre);
        panelPrincipal.add(lblFeedback);
        panelPrincipal.add(lblScore);
        panelPrincipal.add(btnRejouer);

        add(panelPrincipal);

        // Action Listener pour le bouton Soumettre
        btnSoumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierReponse();
            }
        });

        // Permettre de soumettre avec la touche "Entrée" dans le champ de texte
        txtReponse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierReponse();
            }
        });

        // Action Listener pour le bouton Rejouer
        btnRejouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demarrerJeu();
            }
        });


        // Configuration de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajuste la taille de la fenêtre au contenu
        setMinimumSize(new Dimension(450, 600)); // Taille minimale
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);

        // Démarrer le jeu
        demarrerJeu();
    }

    private void demarrerJeu() {
        score = 0;
        questionActuelle = 0;
        lblFeedback.setText(" ");
        lblScore.setText("Score: 0/" + NOMBRE_DE_QUESTIONS);
        btnSoumettre.setEnabled(true);
        txtReponse.setEnabled(true);
        btnRejouer.setVisible(false);
        prochaineQuestion();
        txtReponse.requestFocusInWindow(); // Mettre le focus sur le champ de réponse
    }

    private void prochaineQuestion() {
        if (questionActuelle < NOMBRE_DE_QUESTIONS) {
            questionActuelle++;
            num1 = random.nextInt(NIVEAU_MAX_NOMBRE) + 1;
            num2 = random.nextInt(NIVEAU_MAX_NOMBRE) + 1;
            reponseCorrecte = num1 * num2;

            lblQuestion.setText("Combien font " + num1 + " x " + num2 + " ?");
            lblQuestionNumero.setText("Question: " + questionActuelle + "/" + NOMBRE_DE_QUESTIONS);
            txtReponse.setText(""); // Vider le champ de réponse
            lblFeedback.setText(" "); // Effacer le feedback précédent
        } else {
            terminerJeu();
        }
    }

    private void verifierReponse() {
        if (!txtReponse.isEnabled()) return; // Ne rien faire si le jeu est terminé

        String reponseUtilisateurStr = txtReponse.getText().trim();
        if (reponseUtilisateurStr.isEmpty()) {
            lblFeedback.setText("Veuillez entrer une réponse.");
            lblFeedback.setForeground(Color.ORANGE);
            return;
        }

        try {
            int reponseUtilisateur = Integer.parseInt(reponseUtilisateurStr);
            if (reponseUtilisateur == reponseCorrecte) {
                score++;
                lblFeedback.setText("Correct ! Bravo !");
                lblFeedback.setForeground(new Color(0, 128, 0)); // Vert foncé
            } else {
                lblFeedback.setText("Incorrect. La réponse était " + reponseCorrecte + ".");
                lblFeedback.setForeground(Color.RED);
            }
            lblScore.setText("Score: " + score + "/" + NOMBRE_DE_QUESTIONS);
            prochaineQuestion();

        } catch (NumberFormatException ex) {
            lblFeedback.setText("Entrée invalide. Veuillez entrer un nombre.");
            lblFeedback.setForeground(Color.ORANGE);
        }
        txtReponse.requestFocusInWindow(); // Remettre le focus
        txtReponse.selectAll(); // Sélectionner le texte pour faciliter la prochaine saisie
    }

    private void terminerJeu() {
        lblQuestion.setText("Jeu Terminé !");
        String messageFinal;
        if (score == NOMBRE_DE_QUESTIONS) {
            messageFinal = "Félicitations ! Score parfait !";
            lblFeedback.setForeground(new Color(0,100,0)); // Vert foncé
        } else if (score >= NOMBRE_DE_QUESTIONS * 0.7) {
            messageFinal = "Très bon score !";
            lblFeedback.setForeground(Color.BLUE);
        } else if (score >= NOMBRE_DE_QUESTIONS * 0.5) {
            messageFinal = "Pas mal, continuez !";
            lblFeedback.setForeground(Color.DARK_GRAY);
        } else {
            messageFinal = "Entraînez-vous encore !";
            lblFeedback.setForeground(Color.MAGENTA);
        }
        lblFeedback.setText(messageFinal);
        lblScore.setText("Score final: " + score + "/" + NOMBRE_DE_QUESTIONS);
        btnSoumettre.setEnabled(false);
        txtReponse.setEnabled(false);
        btnRejouer.setVisible(true);
        btnRejouer.requestFocusInWindow();
    }

    public static void main(String[] args) {
        // Il est recommandé de créer et afficher les GUI Swing sur l'Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JavaMultiplicationGUI();
            }
        });
    }
}