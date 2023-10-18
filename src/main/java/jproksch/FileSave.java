package jproksch;

import java.io.*;

public class FileSave implements SaveType{

    //private final static

    @Override
    public void save(String path, Rechtschreibtrainer rechtschreibtrainer) throws IOException {
        // Öffnen Sie die Datei zum Schreiben
        BufferedWriter bf = new BufferedWriter(new FileWriter(path));

        // Schreiben Sie den Punktestand und die Anzahl der Versuche und richtigen Antworten in die Datei
        bf.write("score," + rechtschreibtrainer.getVersuche() + "," + rechtschreibtrainer.getRichtig() + "\n");

        // Schreiben Sie die Daten für jedes Wortpaar in die Datei
        for (WortPaar wortPaar : rechtschreibtrainer.getList()) {
            bf.write(wortPaar.getName() + "," + wortPaar.getUrl() + "\n");
        }

        // Schließen Sie die Datei
        bf.close();
    }

    /**
     * Diese Methode liest Informationen aus einer Datei, in der Daten in Form von
     * Zeichenfolgenpaaren (getrennt durch Kommas) erwartet werden. Jedes Zeilenpaar
     * wird in ein WortPaar-Objekt umgewandelt und der Liste hinzugefügt.
     *
     * @param filePath Der Dateipfad zur Datei, die gelesen werden soll.
     */
    public void getInformation(Rechtschreibtrainer rechtschreibtrainer,String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Erstelle einen BufferedReader zum Lesen der Datei
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Trenne jede Zeile anhand des Kommas
                if (parts[0].equals("score")){
                    rechtschreibtrainer.setRichtig(Integer.parseInt(parts[1]));
                    rechtschreibtrainer.setVersuche(Integer.parseInt(parts[2]));
                    //System.out.println(Arrays.toString(parts));
                }else{
                    //System.out.println(parts[0]+","+parts[1]);
                    rechtschreibtrainer.addWortPaar(new WortPaar(parts[0], parts[1])); // Erstelle ein WortPaar-Objekt und füge es der Liste hinzu
                }
            }
            rechtschreibtrainer.setAktivWortPaar(rechtschreibtrainer.getRandomPaar()); // Wähle ein zufälliges aktives Wort-Bild-Paar aus
            reader.close(); // Schließe den BufferedReader
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Lesen der Datei."); // Fehlermeldung, wenn ein Fehler beim Lesen der Datei auftritt
        }
    }

    @Override
    public void reset(String path, Rechtschreibtrainer rechtschreibtrainer)throws IOException {
        {
            // Öffnen Sie die Datei zum Schreiben
            BufferedWriter bf = new BufferedWriter(new FileWriter(path));

            rechtschreibtrainer.setVersuche(0);
            rechtschreibtrainer.setRichtig(0);
            // Setzen Sie den Punktestand auf 0 und schreiben Sie ihn in die Datei
            bf.write("score,0,0\n");

            // Schreiben Sie die Daten für jedes Wortpaar in die Datei
            for (WortPaar wortPaar : rechtschreibtrainer.getList()) {
                bf.write(wortPaar.getName() + "," + wortPaar.getUrl() + "\n");
            }
            // Schließen Sie die Datei
            bf.close();
        }
    }
}
