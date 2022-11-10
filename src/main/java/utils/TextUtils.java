package utils;

import java.util.List;
import java.util.stream.Collectors;

public class TextUtils {

    public static List<String> removeSpecialCharacters(List<String> list) {

        /*metoda dodana w celu udejnolicenia nazw produktu
         ponieważ np produkt "Asabi — Jeans" w koszyku widnieje pod nazwą "Asabi - Jeans"(różnica w myślniku),
        normalnie zostałoby to zgłoszone jako błąd aplikacji jednak na potrzeby prezentacji
        usuwam znaki specjalne */

        return list.stream()
                .map(item->item.replaceAll("[^a-zA-Z0-9]", " "))
                .collect(Collectors.toList());
    }

    public static String leftOnlyDigits(String text) {

        return text.replaceAll("[^0-9]", "");

    }
}
