package edu.nyu.cs9053.homework8;
import java.util.List;
import java.util.LinkedList;

public class TextingDictionary {

    private final static String[] mapping = {"abc", "def", "ghi","jkl", "mno", "pqrs", "tuv", "wxyz"};
    private final LinkedList<String> collection = new LinkedList<>();
    private final LinkedList<String> prefixCombination = new LinkedList<>();
    private final LinkedList<String> result = new LinkedList<>();

    public void insert(String word) {
        inputTest(word);
        collection.add(word);
    }

    private void inputTest(String word) {
        if (word == "" || word == null) {
            throw new IllegalArgumentException("Insert word cannot be empty or null!");
        }
        for (char element : word.toCharArray()) {
            boolean ifValid = (element >= 'a' && element <= 'z') || (element >= 'A' && element <= 'Z');
            if (ifValid == false) {
                throw new IllegalArgumentException("Inserted word illegal!");
            }
        }
    }

    public List<String> search(List<ValidTextKeyPress> prefixes) {
        prefixCombination.clear();
        result.clear();
        if (prefixes.isEmpty()) {
            return result;
        }
        computePrefixCombination(ValidTextKeyPress.getValidTextKeyPress(prefixes));
        matchPrefixCollection();
        return result;

    }

    private void computePrefixCombination(String presses) {
        prefixCombination.add("");
        for (int i = 0; i < presses.length(); i++) {
            int mappingIndex = Character.getNumericValue(presses.charAt(i));
            while (prefixCombination.peek().length() == i) {
                String origin = prefixCombination.remove();
                for (char addition : mapping[mappingIndex].toCharArray()) {
                    prefixCombination.add(origin + addition);
                }
            }
        }
    }

    private void matchPrefixCollection() {
        for (String prefix : prefixCombination) {
            for (String collectedElement : collection) {
                if (collectedElement.toLowerCase().startsWith(prefix)) {
                    result.add(collectedElement);
                }
            }
        }
    }
}