package org.iesparser.data;

/**
 * Klasa buildera, ułatwiająca tworzenie obiektu. To chyba nie jest taki
 * konkretny wzorzec projektowy 'builder', bardziej przypomina coś w rodzaju
 * monada. Jednorazowy obiekt.
 *
 * @author mateusz
 */
public class PhotometricDataBuilder {

    private PhotometricData data;

    /**
     * Konstruktor domyślny.
     */
    public PhotometricDataBuilder() {
        data = new PhotometricData();
    }

    /**
     * Metoda zwraca zbudowaną klasę z danymi.
     *
     * @return zbudowana klasa z danymi
     */
    public PhotometricData build() {
        return data;
    }

    // dodać te wszystkie metody 'withCośtam'
}
