    package jproksch;

    import java.net.MalformedURLException;
    import java.net.URL;
    import java.util.Objects;

    /**
     * Die Klasse {@code WortPair} repräsentiert ein Paar bestehend aus einem Namen
     * und einer URL.
     */
    public class WortPaar {

        private String name;
        private URL url;

        /**
         * Konstruktor für ein WortPair-Objekt.
         *
         * @param name Der Name für das WortPair.
         * @param url  Die URL für das WortPair.
         * @throws MalformedURLException Wenn die übergebene URL ungültig ist.
         * @throws IllegalArgumentException Wenn der Name leer ist oder die URL ungültig ist.
         */
        public WortPaar(String name, String url){
            // Stellen Sie sicher, dass weder Name noch URL null sind.
            Objects.requireNonNull(name, "Name darf nicht null sein.");
            Objects.requireNonNull(url, "URL darf nicht null sein.");

            // Überprüfen Sie, ob der Name nicht leer ist und die URL gültig ist.
            if (name.length() > 0 && isURLValid(url)) {
                this.name = name;
                try {
                    this.url = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Ungültige Parameter: name=" + name + ", url=" + url);
            }
        }

        /**
         * Überprüft die Gültigkeit einer URL.
         *
         * @param urlString Die zu überprüfende URL-Zeichenfolge.
         * @return {@code true}, wenn die URL gültig ist, andernfalls {@code false}.
         */
        public static boolean isURLValid(String urlString) {
            try {
                // Versuchen Sie, eine URL aus der Zeichenfolge zu erstellen.
                URL url = new URL(urlString);
                // Wenn keine Ausnahme ausgelöst wurde, ist die URL gültig.
                return true;
            } catch (MalformedURLException e) {
                // Wenn eine MalformedURLException ausgelöst wird, ist die URL ungültig.
                return false;
            }
        }

        /**
         * Gibt den Namen des WortPairs zurück.
         *
         * @return Der Name des WortPairs.
         */
        public String getName() {
            return name;
        }

        /**
         * Gibt die URL des WortPairs zurück.
         *
         * @return Die URL des WortPairs.
         */
        public URL getUrl() {
            return url;
        }
    }
