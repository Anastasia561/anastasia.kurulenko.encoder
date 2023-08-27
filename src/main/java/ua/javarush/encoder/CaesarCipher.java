package ua.javarush.encoder;

import static ua.javarush.encoder.FileService.result;
import static ua.javarush.encoder.FileService.source;

public class CaesarCipher {
    private static final char[] EN_UPPER = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final char[] EN_LOWER = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',',
            '«', '»', '"', '\'', ':', '!', '?', ' '};

    private static final char[] UA_UPPER = {'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж',
            'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н',
            'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц',
            'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'};

    private static final char[] UA_LOWER = {'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж',
            'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м', 'н',
            'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц',
            'ч', 'ш', 'щ', 'ь', 'ю', 'я'
    };

    public void code(int key) {
        result.clear();
        char symbol = 0;
        for (Character character : source) {
            char[] table = checkTable(character);
            if (table != null) {
                symbol = getSymbol(key, character, table);
            } else {
                symbol = character;
            }
            result.add(symbol);
        }
    }

    private static char getSymbol(int key, Character character, char[] table) {
        for (int j = 0; j < table.length; j++) {
            if (character == table[j]) {
                int resultKey = checkKey(key, table);
                if (resultKey >= 0) {
                    return table[(j + resultKey) % table.length];
                } else {
                    return table[(table.length + j + resultKey) % table.length];
                }
            }
        }
        return character;
    }

    public void bruteForce() {
        for (int i = 1; i < 35; i++) {
            code(-i);
            StringBuilder builder = new StringBuilder();
            StringBuilder decryptedText = null;
            for (char item : result) {
                decryptedText = builder.append(item);
            }
            assert decryptedText != null;
            if (checkSymbol(decryptedText.toString(), ".", " ") &&
                    checkSymbol(decryptedText.toString(), ",", " ")) {
                return;
            }
        }
    }
    private static boolean checkSymbol(String decryptedText, String firstSymbol, String secondSymbol) {
        int index = decryptedText.indexOf(firstSymbol);
        if (index > 0) {
            String nextSymbol = decryptedText.substring(index + 1, index + 2);
            return nextSymbol.equals(secondSymbol);
        }
        return false;
    }

    private static char[] checkTable(char symbol) {
        if (checkTable(symbol, EN_LOWER) != null) {
            return checkTable(symbol, EN_LOWER);
        } else if (checkTable(symbol, EN_UPPER) != null) {
            return checkTable(symbol, EN_UPPER);
        } else if (checkTable(symbol, UA_LOWER) != null) {
            return checkTable(symbol, UA_LOWER);
        } else if (checkTable(symbol, UA_UPPER) != null) {
            return checkTable(symbol, UA_UPPER);
        }
        return null;
    }

    private static char[] checkTable(char symbol, char[] table) {
        for (char c : table) {
            if (symbol == c) {
                return table;
            }
        }
        return null;
    }

    private static int checkKey(int key, char[] table) {
        if ((Math.abs(key) / table.length) > 0) {
            return key % table.length;
        } else {
            return key;
        }
    }
}
