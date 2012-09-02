package org.iesparser.parser;

import org.iesparser.data.PhotometricData;

/**
 * Klasa bazowa parsera - beda ją rozszerzać poszczególne parsery, np. osobne
 * klasy do IES86, IES95 (czy jakos tak). Dla każdego pliku trzeba stworzyć nowy
 * parser - związane jest to z tym, że każdy parser jest przeznaczony tylko dla
 * jednego formatu, więc nie można go użyć do sparsowania czegoś, co może być w
 * innym standardzie. Użyć i wyrzucić.
 *
 * @author mateusz
 */
public interface Parser {

    /**
     * Metoda parsuje plik (podany gdzieś w konstruktorze) i zwraca obiekt
     * PhotometricData zawierający wszystkie dane wczytane z pliku. Może się
     * zmienić.
     *
     * @return wczytane do obiektu dane z pliku
     */
    public PhotometricData parse() throws ParseException;
}
