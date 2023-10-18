package jproksch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

public class Gui {

    public static final String filepath = "C:\\Users\\julia\\IdeaProjects\\Worttrainer_Reloaded\\src\\main\\java\\jproksch\\info";
    /**
     * Erstellt und zeigt das GUI-Fenster für den WortTrainer.
     *
     * @param rechtschreibtrainer Der Rechtschreibtrainer, der für das GUI verwendet wird.
     */
    public void createAndShowGUI(Rechtschreibtrainer rechtschreibtrainer) {
        // Erstelle das Hauptfenster
        JFrame frame = new JFrame("WortTrainer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout()); // Erstelle das Hauptpanel mit BorderLayout
        JPanel unterhaelfte = new JPanel(new FlowLayout()); // Erstelle ein unteres Panel mit FlowLayout
        JLabel bildLabel = new JLabel(); // Erstelle ein Label für das Bild
        JTextField eingabe = new JTextField(10); // Erstelle ein Textfeld für die Eingabe
        JButton pButton = new JButton("Pruefen");// Erstelle einen Button für die Prüfung
        JButton addButton = new JButton("Sichern von Statistik");// Erstelle einen Button für die Sicherung der Statistik
        JButton resetButton = new JButton("Reset");
        JLabel textLabel = new JLabel(rechtschreibtrainer.getStats());// Erstelle ein Label für den Text
        
        // Füge einen ActionListener zum Button hinzu
        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = eingabe.getText();
                // Setze das Bild mithilfe der Methode setIconFertig
                if (rechtschreibtrainer.abpruefen(input)) {
                    System.out.println("Richtig :)");
                    bildLabel.setIcon(setIconFertig(rechtschreibtrainer.getAktivWortPaar().getUrl()));
                } else {
                    System.out.println("Falsch :(");
                }
                textLabel.setText(rechtschreibtrainer.getStats());
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rechtschreibtrainer.setScore(filepath);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rechtschreibtrainer.resetScore(filepath);
                    textLabel.setText(rechtschreibtrainer.getStats());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Setze das Bild mit der Methode setIconFertig
        bildLabel.setIcon(setIconFertig(rechtschreibtrainer.getAktivWortPaar().getUrl()));

        // Füge Komponenten zum unteren Panel hinzu
        unterhaelfte.add(eingabe);
        unterhaelfte.add(pButton);
        unterhaelfte.add(textLabel);
        unterhaelfte.add(addButton);
        unterhaelfte.add(resetButton);

        // Platzieren Sie das JLabel in der oberen Mitte des Hauptpanels
        panel.add(bildLabel, BorderLayout.NORTH);
        panel.add(unterhaelfte, BorderLayout.SOUTH);

        // Füge das Hauptpanel zum Frame hinzu und mache ihn sichtbar
        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Setzt das Bild anhand der gegebenen URL und skaliert es auf die gewünschte Auflösung.
     *
     * @param url Die URL des Bildes, das angezeigt werden soll.
     * @return Ein Icon-Objekt, das das skalierte Bild repräsentiert.
     */
    public Icon setIconFertig(URL url) {
        try {
            // Lade das Bild von der URL
            Image image = ImageIO.read(url);
            // Begrenze die Auflösung auf 800x400 Pixel
            int maxWidth = 800;
            int maxHeight = 400;

            // Skaliere das Bild auf die gewünschte Auflösung
            Image scaledImage = image.getScaledInstance(maxWidth, maxHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
