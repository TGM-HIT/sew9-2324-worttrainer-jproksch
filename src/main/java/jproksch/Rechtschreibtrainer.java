package jproksch;

import java.io.*;
import java.util.*;

/**
 * Die Klasse {@code Rechtschreibtrainer} ermöglicht das Üben der Rechtschreibung
 * anhand von Wort-Bild-Paaren.
 */
public class Rechtschreibtrainer {

    private final List<WortPaar> list; // Liste für Wort-Bild-Paare
    //private Set<Integer> usedIndex; // Ein Set, um bereits verwendete Indizes zu verfolgen
    private WortPaar aktivWortPaar; // Das aktuell angezeigte Wort-Bild-Paar
    private int richtig, versuche; // Zähler für richtige und insgesamt Versuche
    private Random random; // Ein Zufallszahlengenerator

    /**
     * Konstruktor für die Rechtschreibtrainer-Klasse.
     */
    public Rechtschreibtrainer() {
        list = new ArrayList<>(); // Initialisierung der Liste
        //usedIndex = new HashSet<>(); // Initialisierung des Sets
        aktivWortPaar = null; // Initialisierung des aktiven Wort-Bild-Paars als null
        richtig = 0; // Initialisierung des Zählers für "richtig"
        versuche = 0; // Initialisierung des Zählers für "versuche"
        random = new Random(); // Initialisierung des Zufallszahlengenerators
    }

    /**
     * Überprüft die eingegebene Antwort auf Korrektheit.
     *
     * @param eingabe Die eingegebene Antwort.
     * @return {@code true}, wenn die Antwort korrekt ist, andernfalls {@code false}.
     */
    public boolean abpruefen(String eingabe) {
        if (aktivWortPaar == null) {
            System.out.println("Es wurde keine aktives Bild gewählt"); // Fehlermeldung, wenn kein aktives Bild ausgewählt ist
            return false;
        }
        versuche++; // Erhöhe den Versuchs-Zähler

        if (eingabe.equals(aktivWortPaar.getName())) { // Vergleiche die Eingabe mit dem Namen des aktiven Wort-Bild-Paars
            richtig++; // Erhöhe den Zähler für "richtig"
            //System.out.println(aktivWortPaar.getName() + "," + aktivWortPaar.getUrl()); // Gib den Namen und die URL des Wort-Bild-Paars aus
            this.aktivWortPaar = getRandomPaar(); // Wähle ein zufälliges Wort-Bild-Paar aus
            //System.out.println(aktivWortPaar.getName() + "," + aktivWortPaar.getUrl()); // Gib den Namen und die URL des neuen aktiven Wort-Bild-Paars aus
            return true; // Die Antwort ist korrekt
        }
        return false; // Die Antwort ist falsch
    }

    /**
     * Gibt die Statistik der Versuche und richtigen Antworten zurück.
     *
     * @return Eine Zeichenfolge mit der Statistik (z. B. "3 Versuche 2 Richtige").
     */
    public String getStats() {
        return this.versuche + " Versuche " + this.richtig + " Richtige"; // Gibt die Statistik zurück
    }

    /**
     * Diese Methode liest Informationen aus einer Datei, in der Daten in Form von
     * Zeichenfolgenpaaren (getrennt durch Kommas) erwartet werden. Jedes Zeilenpaar
     * wird in ein WortPaar-Objekt umgewandelt und der Liste hinzugefügt.
     *
     * @param filePath Der Dateipfad zur Datei, die gelesen werden soll.
     */
    public void getInformation(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Erstelle einen BufferedReader zum Lesen der Datei
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Trenne jede Zeile anhand des Kommas
                if (parts[0].equals("score")){
                    this.richtig = Integer.parseInt(parts[1]);
                    this.versuche = Integer.parseInt(parts[2]);
                    //System.out.println(Arrays.toString(parts));
                }else{
                    //System.out.println(parts[0]+","+parts[1]);
                    list.add(new WortPaar(parts[0], parts[1])); // Erstelle ein WortPaar-Objekt und füge es der Liste hinzu
                }
            }
            aktivWortPaar = getRandomPaar(); // Wähle ein zufälliges aktives Wort-Bild-Paar aus
            reader.close(); // Schließe den BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Lesen der Datei."); // Fehlermeldung, wenn ein Fehler beim Lesen der Datei auftritt
        }
    }

    /**
     * Diese Methode speichert den aktuellen Punktestand und die Liste der Wortpaare in einer Datei.
     *
     * @param filePath Der Pfad zur Datei, in der die Daten gespeichert werden sollen.
     * @throws IOException Wenn ein Fehler beim Schreiben in die Datei auftritt.
     */
    public void setScore(String filePath) throws IOException {
        // Öffnen Sie die Datei zum Schreiben
        BufferedWriter bf = new BufferedWriter(new FileWriter(filePath));

        // Schreiben Sie den Punktestand und die Anzahl der Versuche und richtigen Antworten in die Datei
        bf.write("score," + versuche + "," + richtig + "\n");

        // Schreiben Sie die Daten für jedes Wortpaar in die Datei
        for (WortPaar wortPaar : list) {
            bf.write(wortPaar.getName() + "," + wortPaar.getUrl() + "\n");
        }

        // Schließen Sie die Datei
        bf.close();
    }

    /**
     * Diese Methode setzt den Punktestand zurück und speichert die Liste der Wortpaare in einer Datei.
     *
     * @param filePath Der Pfad zur Datei, in der die Daten gespeichert werden sollen.
     * @throws IOException Wenn ein Fehler beim Schreiben in die Datei auftritt.
     */
    public void resetScore(String filePath) throws IOException {
        // Öffnen Sie die Datei zum Schreiben
        BufferedWriter bf = new BufferedWriter(new FileWriter(filePath));

        this.richtig = 0;
        this.versuche = 0;
        // Setzen Sie den Punktestand auf 0 und schreiben Sie ihn in die Datei
        bf.write("score,0,0\n");

        // Schreiben Sie die Daten für jedes Wortpaar in die Datei
        for (WortPaar wortPaar : list) {
            bf.write(wortPaar.getName() + "," + wortPaar.getUrl() + "\n");
        }
        // Schließen Sie die Datei
        bf.close();
    }

    /**
     * Gibt das aktive Wort-Bild-Paar zurück.
     *
     * @return Das aktive Wort-Bild-Paar.
     */
    public WortPaar getAktivWortPaar() {
        return aktivWortPaar;
    }

    /**
     * Wählt zufällig ein Wort-Bild-Paar aus der Liste aus und gibt es zurück.
     *
     * @return Ein zufälliges Wort-Bild-Paar.
     */
    public WortPaar getRandomPaar() {
        int temp = random.nextInt(list.size()); // Erzeuge eine zufällige Zahl im Bereich der Liste
        return list.get(temp); // Gib das zufällige Wort-Bild-Paar zurück
    }

    /**
     * Fügt ein WortPaar in die Liste hinzu
     *
     */
    public void addWortPaar(WortPaar wortPaar){
        Objects.requireNonNull(wortPaar, "Name darf nicht null sein.");
        if (wortPaar.getName().length()>0){
            this.list.add(wortPaar);
        }
    }
}
