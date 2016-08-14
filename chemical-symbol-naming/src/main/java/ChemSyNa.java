import java.util.HashSet;
import java.util.Set;

/**
 * Created by jayanga on 8/15/16.
 */
public class ChemSyNa {
    public static void main(String[] args) {
        System.out.println(validate("Spenglerium", "Ee"));
        System.out.println(validate("Zeddemorium", "Zr"));
        System.out.println(validate("Venkmine", "Kn"));
        System.out.println(validate("Stantzon", "Zt"));
        System.out.println(validate("Melintzum", "Nn"));
        System.out.println(validate("Tullium", "Ty"));

        System.out.println(findFirstValidSymbol("Gozerium"));
        System.out.println(findFirstValidSymbol("Slimyrine"));

        System.out.println(calcNumOfDistinctSymbols("Zuulon"));
    }

    private static boolean validate(String element, String symbol) {
        if (!isValidParam(element)) {
            throw new RuntimeException("Invalid input for field 'element'");
        }

        if (!isValidParam(symbol) || symbol.length() != 2) {
            throw new RuntimeException("Invalid input for field 'symbol'");
        }

        char[] charElement = element.toLowerCase().toCharArray();
        char[] charSymbol = symbol.toLowerCase().toCharArray();

        for (int i = 0, j = 0; j < charElement.length; j++) {
            if (charSymbol[i] == charElement[j] && ++i > 1) {
                return true;
            }
        }
        return false;
    }

    private static String findFirstValidSymbol(String element) {
        if (!isValidParam(element)) {
            throw new RuntimeException("Invalid input for field 'element'");
        }

        char[] charElement = element.toLowerCase().toCharArray();

        int firstIndex = findFirstLowestCharIndex(charElement, 1, charElement.length - 1);
        int secondIndex = findFirstLowestCharIndex(charElement, firstIndex + 1,  charElement.length);

        return new String(new char[]{(char) (charElement[firstIndex] - 32), charElement[secondIndex]});
    }

    private static int calcNumOfDistinctSymbols(String element) {
        if (!isValidParam(element)) {
            throw new RuntimeException("Invalid input for field 'element'");
        }

        char[] charElement = element.toLowerCase().toCharArray();

        Set<String> symbols = new HashSet<String>();

        for (int i = 0; i < charElement.length; i++) {
            for (int j = i + 1; j < charElement.length; j++) {
                symbols.add(new String(new char[]{charElement[i], charElement[j]}));
            }
        }

        return symbols.size();
    }

    private static int findFirstLowestCharIndex(char[] array, int start, int end) {
        int lowest = start;
        for (int i = start; i < end - 1; i++) {
            if (array[i + 1] < array[lowest]) {
                lowest = i + 1;
            }
        }
        return lowest;
    }

    private static boolean isValidParam(String paramValue) {
        if (paramValue == null || paramValue.isEmpty() || paramValue.charAt(0) < 'A' || paramValue.charAt(0) > 'Z') {
            return false;
        }
        return true;
    }
}
